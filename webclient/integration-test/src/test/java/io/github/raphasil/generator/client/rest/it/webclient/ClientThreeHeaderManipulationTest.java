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
class ClientThreeHeaderManipulationTest {

	@Autowired
	private ClientThreeHelper clientThreeHelper;

	@Autowired
	private ClientThreeHeaderManipulation client;

	@Test
	void getWithMethodAndParam() {
		clientThreeHelper.getWithMethodAndParam("355", "ok-header-method-param");
		StepVerifier.create(client.getWithMethodAndParam("355")).expectNext("ok-header-method-param").verifyComplete();
	}

	@Test
	void getWithMethod() {
		clientThreeHelper.getWithMethod("ok-header-method");
		StepVerifier.create(client.getWithMethod()).expectNext("ok-header-method").verifyComplete();
	}

	@Test
	void getWithParam() {
		clientThreeHelper.getWithParam("455", "ok-header-param");
		StepVerifier.create(client.getWithParam("455")).expectNext("ok-header-param").verifyComplete();
	}
}