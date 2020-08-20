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

package io.raphasil.generator.client.rest.spring.webclient;

import java.time.Duration;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "generator.webclient")
public class WebClientGeneratorProperties {

	@Valid
	private final Map<String, HttpClientProperties> clients;

	@Data
	@Validated
	@ConstructorBinding
	public static class HttpClientProperties {

		private final String baseUrl;

		private final Duration connectTimeout;

		private final Duration readTimeout;

		private final Duration writeTimeout;

		private final Map<String, String> defaultHeaders;

		@Valid
		private final Authentication authentication;

	}

	@Data
	@Validated
	@ConstructorBinding
	public static class Authentication {

		@Valid
		private final BasicAuthAuthentication basicAuth;

	}

	@Data
	@Validated
	@ConstructorBinding
	public static class BasicAuthAuthentication {

		@NotBlank
		private final String username;

		@NotBlank
		private final String password;

	}

}
