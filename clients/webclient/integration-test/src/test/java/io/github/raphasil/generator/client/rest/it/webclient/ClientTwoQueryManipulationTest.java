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

package io.github.raphasil.generator.client.rest.it.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.raphasil.test.it.IntegrationTest;
import io.github.raphasil.test.it.config.helper.ClientTwoHelper;
import reactor.test.StepVerifier;

/**
 * @author Raphael Nascimento
 */
@IntegrationTest
class ClientTwoQueryManipulationTest {

	@Autowired
	private ClientTwoHelper clientTwoHelper;

	@Autowired
	private ClientTwoQueryManipulation client;



	@Test
	void get() {
		clientTwoHelper.get("10", "abc", "query-ok");
		StepVerifier.create(client.get(10, "abc")).expectNext("query-ok").verifyComplete();
	}


}