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
import io.github.raphasil.test.it.config.helper.ClientOneHelper;
import reactor.test.StepVerifier;

/**
 * @author Raphael Nascimento
 */
@IntegrationTest
class ClientOneHttpMethodsTest {

	@Autowired
	private ClientOneHelper clientOneHelper;

	@Autowired
	private ClientOneHttpMethods client;

	@Test
	void delete() {
		clientOneHelper.delete("ok");
		StepVerifier.create(client.delete()).expectNext("ok").verifyComplete();
	}

	@Test
	void get() {
		clientOneHelper.get("ok");
		StepVerifier.create(client.get()).expectNext("ok").verifyComplete();
	}

	@Test
	void head() {
		clientOneHelper.head("ok");
		StepVerifier.create(client.head()).verifyComplete();
	}

	@Test
	void options() {
		clientOneHelper.options("ok");
		StepVerifier.create(client.options()).expectNext("ok").verifyComplete();
	}

	@Test
	void patch() {
		clientOneHelper.patch("ok");
		StepVerifier.create(client.patch()).expectNext("ok").verifyComplete();
	}

	@Test
	void post() {
		clientOneHelper.post("ok");
		StepVerifier.create(client.post()).expectNext("ok").verifyComplete();
	}

	@Test
	void put() {
		clientOneHelper.put("ok");
		StepVerifier.create(client.put()).expectNext("ok").verifyComplete();
	}
}