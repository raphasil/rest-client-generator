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

package io.github.raphasil.generator.client.rest.it.resttemplate;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.raphasil.test.it.IntegrationTest;
import io.github.raphasil.test.it.config.helper.ClientOneHelper;

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
		final var result = client.delete();
		assertThat(result).isEqualTo("ok");
	}

	@Test
	void get() {
		clientOneHelper.get("ok");
		final var result = client.get();
		assertThat(result).isEqualTo("ok");
	}

	@Test
	void head() {
		clientOneHelper.head("ok");
		final var result = client.head();
		assertThat(result).isNull();
	}

	@Test
	void options() {
		clientOneHelper.options("ok");
		final var result = client.options();
		assertThat(result).isEqualTo("ok");
	}

	@Test
	void patch() {
		clientOneHelper.patch("ok");
		final var result = client.patch();
		assertThat(result).isEqualTo("ok");
	}

	@Test
	void post() {
		clientOneHelper.post("ok");
		final var result = client.post();
		assertThat(result).isEqualTo("ok");
	}

	@Test
	void put() {
		clientOneHelper.put("ok");
		final var result = client.put();
		assertThat(result).isEqualTo("ok");
	}
}