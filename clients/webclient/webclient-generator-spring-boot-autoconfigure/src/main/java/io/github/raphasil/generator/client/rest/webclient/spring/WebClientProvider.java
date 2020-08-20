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

package io.github.raphasil.generator.client.rest.webclient.spring;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.*;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.raphasil.generator.client.rest.api.provider.ClientProvider;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import reactor.netty.http.client.HttpClient;

@RequiredArgsConstructor
public class WebClientProvider implements ClientProvider<WebClient> {

	private final WebClient.Builder webClientBuilder;

	private final Map<String, WebClientGeneratorProperties.HttpClientProperties> properties;

	private final Map<String, WebClient> webClients;

	@Override
	public WebClient getClient(final String clientName) {
		return webClients.computeIfAbsent(clientName, this::buildClient);
	}

	private WebClient buildClient(final String clientName) {

		final var builder = webClientBuilder.clone();

		if (!properties.containsKey(clientName)) {
			return builder.build();
		}

		final var httpProperties = properties.get(clientName);

		configureBaseUrl(builder, httpProperties.getBaseUrl());

		configureDefaultHeaders(builder, httpProperties.getDefaultHeaders());

		configureAuthentications(builder, httpProperties.getAuthentication());

		configureClientConnector(builder, httpProperties);

		return builder.build();
	}

	private void configureBaseUrl(final WebClient.Builder builder, final String baseUrl) {
		Optional.ofNullable(baseUrl).filter(url -> !url.isBlank()).ifPresent(builder::baseUrl);
	}

	private void configureClientConnector(final WebClient.Builder builder,
			final WebClientGeneratorProperties.HttpClientProperties httpProperties) {

		final var connectTimeout = Optional.ofNullable(httpProperties.getConnectTimeout()).orElse(Duration.ofSeconds(5));
		final var readTimeout = Optional.ofNullable(httpProperties.getReadTimeout()).orElse(Duration.ofSeconds(5));
		final var writeTimeout = Optional.ofNullable(httpProperties.getWriteTimeout()).orElse(Duration.ofSeconds(5));

		final var httpClient = HttpClient.create()
				.followRedirect(true)
				.tcpConfiguration(client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) connectTimeout.toMillis())
						.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler((int) readTimeout.toSeconds()))
								.addHandlerLast(new WriteTimeoutHandler((int) writeTimeout.toSeconds()))));

		builder.clientConnector(new ReactorClientHttpConnector(httpClient));
	}

	private void configureDefaultHeaders(final WebClient.Builder builder, final Map<String, String> defaultHeaders) {
		Optional.ofNullable(defaultHeaders).ifPresent(headers -> headers.forEach(builder::defaultHeader));
	}

	private void configureAuthentications(final WebClient.Builder builder,
			final WebClientGeneratorProperties.Authentication authentication) {
		Optional.ofNullable(authentication)
				.map(WebClientGeneratorProperties.Authentication::getBasicAuth)
				.ifPresent(auth -> builder.filter(basicAuthentication(auth.getUsername(), auth.getPassword())));
	}
}
