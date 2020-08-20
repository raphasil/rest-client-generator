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

package io.github.raphasil.generator.client.rest.processor.webclient;

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
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.auto.common.MoreTypes;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;

import io.github.raphasil.generator.client.rest.api.RestClient;
import io.github.raphasil.generator.client.rest.api.provider.ClientProvider;
import io.github.raphasil.generator.client.rest.core.BaseRestClientProcessor;
import io.github.raphasil.generator.client.rest.core.model.CodeScope;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Raphael Nascimento
 */
public class RestClientProcessor extends BaseRestClientProcessor {

	@Override
	protected List<FieldSpec> buildFields() {
		return Collections.singletonList(FieldSpec.builder(WebClient.class, "webClient", Modifier.PRIVATE, Modifier.FINAL).build());
	}

	@Override
	protected MethodSpec buildConstructor(final RestClient restClient) {
		return MethodSpec.constructorBuilder()
				.addParameter(ParameterizedTypeName.get(ClientProvider.class, WebClient.class), "clientProvider", Modifier.FINAL)
				.addStatement("this.webClient = clientProvider.getClient($S)", restClient.value())
				.build();
	}

	@Override
	protected CodeBlock buildCode(final CodeScope scope) {

		validScope(scope);

		final var builder = CodeBlock.builder();

		builder.add("return webClient.$L()", scope.getHttpMethod().toLowerCase());

		// build .uri()
		buildURI(scope, builder);

		// build .header()
		scope.getHeaders().forEach((key, value) -> builder.add(".header($S, $S)", key, value));
		scope.getHeaderParams().forEach((paramName, header) -> builder.add(".header($S, $L)", header.value(), paramName));

		// build .body(from())
		Optional.ofNullable(scope.getBodyParam())
				.map(VariableElement::getSimpleName)
				.map(Objects::toString)
				.ifPresent(param -> builder.add(".body($T.fromValue($L))", BodyInserters.class, param));

		builder.add(".retrieve()");

		final var returnType = (DeclaredType) scope.getReturnType();

		final var isMono = MoreTypes.isTypeOf(Mono.class, returnType);

		final var returnQualifiedType = (DeclaredType) (returnType.getTypeArguments().size() == 1
				? returnType.getTypeArguments().get(0)
				: getTypeElement(String.class));

		builder.add(".$L($T.class)", isMono ? "bodyToMono" : "bodyToFlux", returnQualifiedType);

		return builder.build();
	}

	private void validScope(final CodeScope scope) {
		final var returnType = scope.getReturnType();

		final var isMono = MoreTypes.isTypeOf(Mono.class, returnType);
		final var isFlux = MoreTypes.isTypeOf(Flux.class, returnType);

		if (returnType.getKind() != TypeKind.DECLARED || (!isMono && !isFlux)) {
			throw new IllegalArgumentException(String.format(
					"This method is not returning a valid type (Mono or Flux), interface: %s method: %s return: %s",
					scope.getInterfaceAnnotated(),
					scope.getMethodAnnoted(),
					returnType));
		}
	}

	private void buildURI(final CodeScope scope, final CodeBlock.Builder builder) {
		final var sbUriBuilder = new StringBuilder();

		Optional.ofNullable(scope.getBasePath())
				.filter(StringUtils::isNoneBlank)
				.map(path -> String.format(".path(\"%s\")", path))
				.ifPresent(sbUriBuilder::append);
		Optional.ofNullable(scope.getPath())
				.filter(StringUtils::isNoneBlank)
				.map(path -> String.format(".path(\"%s\")", path))
				.ifPresent(sbUriBuilder::append);

		scope.getQueryParams()
				.forEach((q, query) -> sbUriBuilder.append(String.format(".queryParam(\"%s\", %s)",
						StringUtils.defaultIfBlank(query.value(), q),
						q)));

		final var paths = scope.getPathParams();

		if (sbUriBuilder.length() > 0 || !paths.isEmpty()) {
			builder.add(".uri(uriBuilder -> uriBuilder");
			builder.add("$L.build(", sbUriBuilder);

			if (!paths.isEmpty()) {
				builder.add("$T.of($L)",
						Map.class,
						paths.entrySet()
								.stream()
								.map(entry -> String.format("\"%s\", %s",
										StringUtils.defaultIfBlank(entry.getValue().value(), entry.getKey()),
										entry.getKey()))
								.collect(Collectors.joining(",")));
			}

			builder.add("))");
		}
	}
}
