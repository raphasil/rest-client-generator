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
import org.springframework.web.util.UriComponentsBuilder;

import com.github.tomakehurst.wiremock.client.WireMock;

/**
 * @author Raphael Nascimento
 */
@TestComponent
public class ClientTwoHelper {

	public static final String CLIENT_TWO_PATH = "/client-two";

	public void get(final String size, String customName, final Object response) {
		stubFor(WireMock.get(urlPathEqualTo(CLIENT_TWO_PATH)).withQueryParam("size", equalTo(size)).withQueryParam("custom-name", equalTo(customName)).willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

	public void getPathManipulation(final int id, String custom, final Object response) {
		final var uri = UriComponentsBuilder.fromPath(CLIENT_TWO_PATH).path("/test/{id}/another/{custom-path}").build(id, custom).toString();
		stubFor(WireMock.get(urlPathEqualTo(uri)).willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

	public void getBasePathManipulation(final int id, final String custom, final long userId, final String customName, final Object response) {
		final var uri = UriComponentsBuilder.fromPath(CLIENT_TWO_PATH).path("/base/{id}/another/{custom-path}").path("/test/{userId}/example/{custom-name}").build(id, custom, userId, customName).toString();
		stubFor(WireMock.get(urlPathEqualTo(uri)).willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

}
