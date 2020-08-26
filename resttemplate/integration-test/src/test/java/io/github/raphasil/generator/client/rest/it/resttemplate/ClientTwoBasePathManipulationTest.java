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
import io.github.raphasil.test.it.config.helper.ClientTwoHelper;

/**
 * @author Raphael Nascimento
 */
@IntegrationTest
class ClientTwoBasePathManipulationTest {

	@Autowired
	private ClientTwoHelper clientTwoHelper;

	@Autowired
	private ClientTwoBasePathManipulation client;

	@Test
	void getBasePathManipulation() {
		clientTwoHelper.getBasePathManipulation(20, "ppp", 55L, "___", "okay-base-path");
		final var result = client.getBasePathManipulation(20, "ppp", 55L, "___");
		assertThat(result).isEqualTo("okay-base-path");
	}
}