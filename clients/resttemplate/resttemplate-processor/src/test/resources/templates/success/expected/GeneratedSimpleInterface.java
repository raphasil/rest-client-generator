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

import javax.annotation.processing.Generated;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.raphasil.generator.client.rest.api.provider.ClientProvider;

@Generated("io.github.raphasil.generator.client.rest.processor.resttemplate.RestClientProcessor")
public class GeneratedSimpleInterface implements SimpleInterface {
	private final RestTemplate restTemplate;

	GeneratedSimpleInterface(final ClientProvider<RestTemplate> clientProvider) {
		this.restTemplate = clientProvider.getClient("client-test");
	}

	@Override
	public ResponseEntity<String> test() {
		final var uri = UriComponentsBuilder.newInstance().build().toUri().toString();
		return restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
	}

	@Override
	public String test2() {
		final var uri = UriComponentsBuilder.newInstance().build().toUri().toString();
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
		return responseEntity.getBody();
	}
}