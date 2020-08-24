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

package io.github.raphasil.generator.client.rest.resttemplate.spring;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import io.github.raphasil.generator.client.rest.api.provider.ClientProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestTemplateProvider implements ClientProvider<RestTemplate> {

	private final RestTemplateBuilder restTemplateBuilder;

	private final Map<String, RestTemplateGeneratorProperties.HttpClientProperties> properties;

	private final Map<String, RestTemplate> clients;

	@Override
	public RestTemplate getClient(final String clientName) {
		return clients.computeIfAbsent(clientName, this::buildClient);
	}

	private RestTemplate buildClient(final String clientName) {

		final var restTemplate = restTemplateBuilder.build();

		if (!properties.containsKey(clientName)) {
			return restTemplate;
		}

		final var httpProperties = properties.get(clientName);

		configureBaseUrl(restTemplate, httpProperties.getBaseUrl());

		configureDefaultHeaders(restTemplate, httpProperties.getDefaultHeaders());

		configureAuthentications(restTemplate, httpProperties.getAuthentication());

		return restTemplate;
	}

	private void configureBaseUrl(final RestTemplate restTemplate, final String baseUrl) {
		Optional.ofNullable(baseUrl)
				.filter(url -> !url.isBlank())
				.ifPresent(url -> restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(url)));
	}

	private void configureDefaultHeaders(final RestTemplate restTemplate, final Map<String, String> defaultHeaders) {
		Optional.ofNullable(defaultHeaders)
				.filter(h -> !h.isEmpty())
				.ifPresent(headers -> restTemplate.setInterceptors(Collections.singletonList(new DefaultHeadersClientRequestInterceptor(
						defaultHeaders))));
	}

	private void configureAuthentications(final RestTemplate restTemplate,
			final RestTemplateGeneratorProperties.Authentication authentication) {

		Optional.ofNullable(authentication)
				.map(RestTemplateGeneratorProperties.Authentication::getBasicAuth)
				.ifPresent(auth -> restTemplate.setInterceptors(Collections.singletonList(new BasicAuthenticationInterceptor(auth.getUsername(),
						auth.getPassword()))));
	}

	private static final class DefaultHeadersClientRequestInterceptor implements ClientHttpRequestInterceptor {
		private final Map<String, String> defaultHeaders;

		private DefaultHeadersClientRequestInterceptor(final Map<String, String> defaultHeaders) {
			this.defaultHeaders = defaultHeaders;
		}

		@Override
		public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution)
				throws IOException {
			final var requestHeaders = request.getHeaders();
			defaultHeaders.forEach((key, value) -> {
				if (requestHeaders.containsKey(key)) {
					requestHeaders.get(key).add(value);
				} else {
					requestHeaders.add(key, value);
				}
			});
			return execution.execute(request, body);
		}
	}
}
