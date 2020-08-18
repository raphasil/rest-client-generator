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

package io.raphasil.generator.client.rest.core.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import io.raphasil.generator.client.rest.api.http.Body;
import io.raphasil.generator.client.rest.api.http.Header;
import io.raphasil.generator.client.rest.api.http.Path;
import io.raphasil.generator.client.rest.api.http.Query;
import lombok.Data;

/**
 * @author Raphael Nascimento
 */
@Data
public class CodeScope {
	private final TypeElement interfaceAnnotated;

	private final ExecutableElement methodAnnoted;

	private final String basePath;

	private final String path;

	private final String httpMethod;

	private final Map<String, String> headers;

	private final TypeMirror returnType;

	private final Map<String, VariableElement> params;

	private final Map<String, Path> pathParams;

	private final Map<String, Query> queryParams;

	private final Map<String, Header> headerParams;

	private final VariableElement bodyParam;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private final Map<String, VariableElement> params = new HashMap<>();

		private final Map<String, Path> pathParams = new HashMap<>();

		private final Map<String, Query> queryParams = new HashMap<>();

		private final Map<String, Header> headerParams = new HashMap<>();

		private TypeElement interfaceAnnotated;

		private ExecutableElement methodAnnoted;

		private String basePath;

		private String path;

		private String httpMethod;

		private Map<String, String> headers;

		private TypeMirror returnType;

		private VariableElement bodyParam = null;

		public Builder interfaceAnnotated(final TypeElement interfaceAnnotated) {
			this.interfaceAnnotated = interfaceAnnotated;
			return this;
		}

		public Builder methodAnnoted(final ExecutableElement methodAnnoted) {
			this.methodAnnoted = methodAnnoted;
			return this;
		}

		public Builder basePath(final String basePath) {
			this.basePath = basePath;
			return this;
		}

		public Builder path(final String path) {
			this.path = path;
			return this;
		}

		public Builder httpMethod(final String httpMethod) {
			this.httpMethod = httpMethod;
			return this;
		}

		public Builder headers(final Map<String, String> headers) {
			this.headers = headers;
			return this;
		}

		public Builder returnType(final TypeMirror returnType) {
			this.returnType = returnType;
			return this;
		}

		public Builder param(final VariableElement param) {
			final var name = param.getSimpleName().toString();
			params.put(name, param);
			Optional.ofNullable(param.getAnnotation(Path.class)).ifPresent(a -> pathParams.put(name, a));
			Optional.ofNullable(param.getAnnotation(Query.class)).ifPresent(a -> queryParams.put(name, a));
			Optional.ofNullable(param.getAnnotation(Header.class)).ifPresent(a -> headerParams.put(name, a));
			Optional.ofNullable(param.getAnnotation(Body.class)).ifPresent(a -> bodyParam = param);
			return this;
		}

		public CodeScope build() {
			return new CodeScope(interfaceAnnotated,
					methodAnnoted,
					basePath,
					path,
					httpMethod,
					headers,
					returnType,
					params,
					pathParams,
					queryParams,
					headerParams,
					bodyParam);
		}
	}
}
