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
import static io.github.raphasil.test.it.config.helper.ClientFourHelper.*;

import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.github.tomakehurst.wiremock.client.WireMock;

/**
 * @author Raphael Nascimento
 */
@TestComponent
public class ClientFiveHelper {

	public static final String CLIENT_ONE_PATH = "/client-five";

	public void get(final Object response) {
		stubFor(WireMock.get(urlPathEqualTo(CLIENT_ONE_PATH))
				.willReturn(aResponse().withStatus(HttpStatus.OK.value())
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(toJSON(response))));
	}

}
