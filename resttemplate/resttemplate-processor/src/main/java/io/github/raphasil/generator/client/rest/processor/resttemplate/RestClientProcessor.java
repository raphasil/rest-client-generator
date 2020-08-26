/*
 * Copyright RestClientGenerator
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.github.raphasil.generator.client.rest.processor.resttemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.auto.common.MoreTypes;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;

import io.github.raphasil.generator.client.rest.api.RestClient;
import io.github.raphasil.generator.client.rest.api.provider.ClientProvider;
import io.github.raphasil.generator.client.rest.core.BaseRestClientProcessor;
import io.github.raphasil.generator.client.rest.core.model.CodeScope;

/**
 * @author Raphael Nascimento
 */
public class RestClientProcessor extends BaseRestClientProcessor {
	private static final String HTTP_ENTITY_VAR = "request";

	private static final String HEADERS_VAR = "headers";

	private static final String REST_TEMPLATE_VAR = "restTemplate";

	private static final String URI_VAR = "uri";

	private static final String RESPONSE_ENTITY_VAR = "responseEntity";

	@Override
	protected List<FieldSpec> buildFields() {
		return Collections.singletonList(FieldSpec.builder(RestTemplate.class, REST_TEMPLATE_VAR, Modifier.PRIVATE, Modifier.FINAL)
				.build());
	}

	@Override
	protected MethodSpec buildConstructor(final RestClient restClient) {
		return MethodSpec.constructorBuilder()
				.addParameter(ParameterizedTypeName.get(ClientProvider.class, RestTemplate.class), "clientProvider", Modifier.FINAL)
				.addStatement("this.$L = clientProvider.getClient($S)", REST_TEMPLATE_VAR, restClient.value())
				.build();
	}

	@Override
	protected CodeBlock buildCode(final CodeScope scope) {

		validScope(scope);
		final var returnType = (DeclaredType) scope.getReturnType();

		final var uriCode = buildUriCode(scope);
		final var headerCode = buildHeaderCode(scope);
		final var httpEntityCode = buildHttpEntity(!headerCode.isEmpty(), scope.getBodyParam());
		final var exchangeCode = buildExchange(!httpEntityCode.isEmpty(), returnType, scope.getHttpMethod());

		final var builder = CodeBlock.builder().addStatement(uriCode).add(headerCode).add(httpEntityCode);

		if (isResponseEntity(returnType)) {
			builder.addStatement("return $L", exchangeCode);
		} else {
			builder.addStatement("final var $L = $L", RESPONSE_ENTITY_VAR, exchangeCode);
			builder.addStatement("return $L.getBody()", RESPONSE_ENTITY_VAR);
		}

		return builder.build();
	}

	private CodeBlock buildExchange(final boolean hasHttpEntity, final DeclaredType returnType, final String httpMethod) {
		return CodeBlock.builder()
				.add("restTemplate.exchange($L, $T.$L, $L, $L)",
						URI_VAR,
						HttpMethod.class,
						httpMethod.toUpperCase(),
						hasHttpEntity ? HTTP_ENTITY_VAR : "null",
						buildReturnType(returnType))
				.build();
	}

	private CodeBlock buildReturnType(final DeclaredType returnType) {
		final var builder = CodeBlock.builder();
		var type = returnType;
		if (isResponseEntity(returnType)) {
			if (returnType.getTypeArguments().isEmpty()) {
				type = (DeclaredType) getTypeElement(String.class);
			} else {
				type = (DeclaredType) returnType.getTypeArguments().get(0);
			}
		}

		if (type.getTypeArguments().isEmpty()) {
			builder.add("$T.class", type);
		} else {
			builder.add("new $T<$T>(){}", ParameterizedTypeReference.class, type);
		}

		return builder.build();
	}

	private boolean isResponseEntity(final DeclaredType returnType) {
		return MoreTypes.isTypeOf(ResponseEntity.class, returnType);
	}

	private CodeBlock buildHttpEntity(final boolean hasHeaders, final VariableElement bodyParam) {
		final var builder = CodeBlock.builder();
		if (hasHeaders && Objects.nonNull(bodyParam)) {
			builder.addStatement("final var $L = new $T<>($L, $L)",
					HTTP_ENTITY_VAR,
					HttpEntity.class,
					bodyParam.getSimpleName().toString(),
					HEADERS_VAR);
		} else if (hasHeaders) {
			builder.addStatement("final var $L = new $T<>($L)", HTTP_ENTITY_VAR, HttpEntity.class, HEADERS_VAR);
		} else if (Objects.nonNull(bodyParam)) {
			builder.addStatement("final var $L = new $T<>($L)", HTTP_ENTITY_VAR, HttpEntity.class, bodyParam.getSimpleName().toString());
		}
		return builder.build();
	}

	private void validScope(final CodeScope scope) {
		final var returnType = scope.getReturnType();

		if (returnType.getKind() != TypeKind.DECLARED) {
			throw new IllegalArgumentException(String.format(
					"This method is not returning a valid type, interface: %s method: %s return: %s",
					scope.getInterfaceAnnotated(),
					scope.getMethodAnnoted(),
					returnType));
		}
	}

	private CodeBlock buildUriCode(final CodeScope scope) {

		final var builder = CodeBlock.builder();

		final var basePath = scope.getBasePath();
		final var path = scope.getPath();
		final var queries = scope.getQueryParams();
		final var paths = scope.getPathParams();

		builder.add("final var $L = $T.newInstance()", URI_VAR, UriComponentsBuilder.class);

		Optional.ofNullable(basePath).filter(StringUtils::isNoneBlank).ifPresent(p -> builder.add(".path($S)", p));

		Optional.ofNullable(path).filter(StringUtils::isNoneBlank).ifPresent(p -> builder.add(".path($S)", p));

		queries.forEach((q, query) -> builder.add(".queryParam($S, $L)", StringUtils.defaultIfBlank(query.value(), q), q));

		if (paths.isEmpty()) {
			builder.add(".build().toUri()");
		} else {
			builder.add(".build(");
			builder.add("$T.of($L)",
					Map.class,
					paths.entrySet()
							.stream()
							.map(entry -> String.format("\"%s\", %s",
									StringUtils.defaultIfBlank(entry.getValue().value(), entry.getKey()),
									entry.getKey()))
							.collect(Collectors.joining(",")));
			builder.add(")");
		}

		return builder.add(".toString()").build();
	}

	private CodeBlock buildHeaderCode(final CodeScope scope) {
		final var builder = CodeBlock.builder();

		final var headers = scope.getHeaders();
		final var headerParams = scope.getHeaderParams();

		if (!headers.isEmpty() || !headerParams.isEmpty()) {
			builder.addStatement("final var $L = new $T()", HEADERS_VAR, HttpHeaders.class);
			headers.forEach((key, value) -> builder.addStatement("$L.add($S, $S)", HEADERS_VAR, key, value));
			headerParams.forEach((paramName, header) -> builder.addStatement("$L.add($S, $L)", HEADERS_VAR, header.value(), paramName));
		}

		return builder.build();
	}
}
