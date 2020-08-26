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
import io.github.raphasil.test.it.config.helper.ClientThreeHelper;

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
		final var result = client.getWithMethodAndParam("355");
		assertThat(result).isEqualTo("ok-header-method-param");
	}

	@Test
	void getWithMethod() {
		clientThreeHelper.getWithMethod("ok-header-method");
		final var result = client.getWithMethod();
		assertThat(result).isEqualTo("ok-header-method");
	}

	@Test
	void getWithParam() {
		clientThreeHelper.getWithParam("455", "ok-header-param");
		final var result = client.getWithParam("455");
		assertThat(result).isEqualTo("ok-header-param");
	}
}