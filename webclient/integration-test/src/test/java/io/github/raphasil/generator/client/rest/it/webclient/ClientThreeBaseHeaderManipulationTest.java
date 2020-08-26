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
import io.github.raphasil.test.it.config.helper.ClientThreeHelper;
import reactor.test.StepVerifier;

/**
 * @author Raphael Nascimento
 */
@IntegrationTest
class ClientThreeBaseHeaderManipulationTest {

	@Autowired
	private ClientThreeHelper clientThreeHelper;

	@Autowired
	private ClientThreeBaseHeaderManipulation client;

	@Test
	void getWithAllPossibleHeaders() {

		clientThreeHelper.getBaseWithAllPossibleHeaders("155", "ok-base-header-all");
		StepVerifier.create(client.getWithAllPossibleHeaders("155")).expectNext("ok-base-header-all").verifyComplete();
	}

	@Test
	void getWithInterfaceAndMethod() {
		clientThreeHelper.getBaseWithInterfaceAndMethod("ok-base-header-interface-method");
		StepVerifier.create(client.getWithInterfaceAndMethod()).expectNext("ok-base-header-interface-method").verifyComplete();
	}

	@Test
	void getWithParam() {
		clientThreeHelper.getBaseWithParam("665", "ok-base-header-interface-method");
		StepVerifier.create(client.getWithParam("665")).expectNext("ok-base-header-interface-method").verifyComplete();
	}
}