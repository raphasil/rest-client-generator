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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

/**
 * @author Raphael Nascimento
 */
@TestComponent
public class ClientFourHelper {

	public static final String CLIENT_FOUR_PATH = "/client-four";

	public static final String USERNAME = "Jon Snow";

	public static final String PASSWORD = "Daenerys Targaryen";

	private final static ObjectMapper mapper = new ObjectMapper();

	public void deleteFlux(final Object response) {
		stubFor(WireMock.delete(urlPathEqualTo(CLIENT_FOUR_PATH))
				.withBasicAuth(USERNAME, PASSWORD)
				.willReturn(aResponse().withStatus(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(toJSON(response))));
	}

	public void getFlux(final Object response) {
		stubFor(WireMock.get(urlPathEqualTo(CLIENT_FOUR_PATH))
				.withBasicAuth(USERNAME, PASSWORD)
				.willReturn(aResponse().withStatus(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(toJSON(response))));
	}

	public void headFlux(final Object response) {
		stubFor(WireMock.head(urlPathEqualTo(CLIENT_FOUR_PATH))
				.withBasicAuth(USERNAME, PASSWORD)
				.willReturn(aResponse().withStatus(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(toJSON(response))));
	}

	public void optionsFlux(final Object response) {
		stubFor(WireMock.options(urlPathEqualTo(CLIENT_FOUR_PATH))
				.withBasicAuth(USERNAME, PASSWORD)
				.willReturn(aResponse().withStatus(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(toJSON(response))));
	}

	public void patchFlux(final Object response) {
		stubFor(WireMock.patch(urlPathEqualTo(CLIENT_FOUR_PATH))
				.withBasicAuth(USERNAME, PASSWORD)
				.willReturn(aResponse().withStatus(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(toJSON(response))));
	}

	public void postFlux(final Object response) {
		stubFor(WireMock.post(urlPathEqualTo(CLIENT_FOUR_PATH))
				.withBasicAuth(USERNAME, PASSWORD)
				.willReturn(aResponse().withStatus(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(toJSON(response))));
	}

	public void putFlux(final Object response) {
		stubFor(WireMock.put(urlPathEqualTo(CLIENT_FOUR_PATH))
				.withBasicAuth(USERNAME, PASSWORD)
				.willReturn(aResponse().withStatus(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(toJSON(response))));
	}

	public void patchBody(final Object body, final Object response) {
		stubFor(WireMock.patch(urlPathEqualTo(CLIENT_FOUR_PATH))
				.withRequestBody(equalToJson(toJSON(body)))
				.withBasicAuth(USERNAME, PASSWORD)
				.willReturn(aResponse().withStatus(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(String.valueOf(response))));
	}

	public void postBody(final Object body, final Object response) {
		stubFor(WireMock.post(urlPathEqualTo(CLIENT_FOUR_PATH))
				.withRequestBody(equalToJson(toJSON(body)))
				.withBasicAuth(USERNAME, PASSWORD)
				.willReturn(aResponse().withStatus(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(String.valueOf(response))));
	}

	public void putBody(final Object body, final Object response) {
		stubFor(WireMock.put(urlPathEqualTo(CLIENT_FOUR_PATH))
				.withRequestBody(equalToJson(toJSON(body)))
				.withBasicAuth(USERNAME, PASSWORD)
				.willReturn(aResponse().withStatus(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(String.valueOf(response))));
	}

	static String toJSON(final Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Could not convert to json, " + object, e);
		}
	}

}
