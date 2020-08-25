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

package io.github.raphasil.test.it.config.helper;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.HttpStatus;

import com.github.tomakehurst.wiremock.client.WireMock;

/**
 * @author Raphael Nascimento
 */
@TestComponent
public class ClientOneHelper {

	public static final String CLIENT_ONE_PATH = "/client-one";

	public void delete(final Object response) {
		stubFor(WireMock.delete(urlPathEqualTo(CLIENT_ONE_PATH))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

	public void get(final Object response) {
		stubFor(WireMock.get(urlPathEqualTo(CLIENT_ONE_PATH))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

	public void head(final Object response) {
		stubFor(WireMock.head(urlPathEqualTo(CLIENT_ONE_PATH))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

	public void options(final Object response) {
		stubFor(WireMock.options(urlPathEqualTo(CLIENT_ONE_PATH))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

	public void patch(final Object response) {
		stubFor(WireMock.patch(urlPathEqualTo(CLIENT_ONE_PATH))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

	public void post(final Object response) {
		stubFor(WireMock.post(urlPathEqualTo(CLIENT_ONE_PATH))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

	public void put(final Object response) {
		stubFor(WireMock.put(urlPathEqualTo(CLIENT_ONE_PATH))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

}
