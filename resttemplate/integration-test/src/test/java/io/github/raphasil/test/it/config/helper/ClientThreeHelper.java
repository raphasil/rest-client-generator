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
public class ClientThreeHelper {

	public static final String CLIENT_THREE_PATH = "/client-three";

	public void getBaseWithAllPossibleHeaders(final String headerCustom, final Object response) {
		stubFor(WireMock.get(urlPathEqualTo(CLIENT_THREE_PATH))
				.withHeader("x-default-header", equalTo("default"))
				.withHeader("x-default-header", equalTo("default"))
				.withHeader("x-interface-1", equalTo("interface-1"))
				.withHeader("x-interface-2", equalTo("interface-2"))
				.withHeader("x-method-1", equalTo("method-1"))
				.withHeader("x-method-2", equalTo("method-2"))
				.withHeader("x-custom", equalTo(headerCustom))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

	public void getBaseWithInterfaceAndMethod(final Object response) {
		stubFor(WireMock.get(urlPathEqualTo(CLIENT_THREE_PATH))
				.withHeader("x-default-header", equalTo("default"))
				.withHeader("x-default-header", equalTo("default"))
				.withHeader("x-interface-1", equalTo("interface-1"))
				.withHeader("x-interface-2", equalTo("interface-2"))
				.withHeader("x-method-1", equalTo("method-1"))
				.withHeader("x-method-2", equalTo("method-2"))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

	public void getBaseWithParam(final String headerCustom, final Object response) {
		stubFor(WireMock.get(urlPathEqualTo(CLIENT_THREE_PATH))
				.withHeader("x-default-header", equalTo("default"))
				.withHeader("x-default-header", equalTo("default"))
				.withHeader("x-interface-1", equalTo("interface-1"))
				.withHeader("x-interface-2", equalTo("interface-2"))
				.withHeader("x-custom", equalTo(headerCustom))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

	public void getWithMethodAndParam(final String headerCustom, final Object response) {
		stubFor(WireMock.get(urlPathEqualTo(CLIENT_THREE_PATH))
				.withHeader("x-default-header", equalTo("default"))
				.withHeader("x-default-header", equalTo("default"))
				.withHeader("x-method-1", equalTo("method-1"))
				.withHeader("x-method-2", equalTo("method-2"))
				.withHeader("x-custom", equalTo(headerCustom))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

	public void getWithMethod(final Object response) {
		stubFor(WireMock.get(urlPathEqualTo(CLIENT_THREE_PATH))
				.withHeader("x-default-header", equalTo("default"))
				.withHeader("x-default-header", equalTo("default"))
				.withHeader("x-method-1", equalTo("method-1"))
				.withHeader("x-method-2", equalTo("method-2"))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}

	public void getWithParam(final String headerCustom, final Object response) {
		stubFor(WireMock.get(urlPathEqualTo(CLIENT_THREE_PATH))
				.withHeader("x-default-header", equalTo("default"))
				.withHeader("x-default-header", equalTo("default"))
				.withHeader("x-custom", equalTo(headerCustom))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody(String.valueOf(response))));
	}
}
