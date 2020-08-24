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

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.raphasil.test.it.IntegrationTest;
import io.github.raphasil.test.it.config.helper.ClientFourHelper;

/**
 * @author Raphael Nascimento
 */
@IntegrationTest
class ClientFourBodyTest {

	@Autowired
	private ClientFourHelper clientFourHelper;

	@Autowired
	private ClientFourBody client;

	@Test
	void patch() {
		clientFourHelper.patchBody(Arrays.asList("abc", "cba"), "response-patch");
		final var result = client.patchBody(Arrays.asList("abc", "cba"));
		assertThat(result).isEqualTo("response-patch");
	}

	@Test
	void post() {
		clientFourHelper.postBody(Map.of("a", "b"), "response-post");
		final var result = client.postBody(Map.of("a", "b"));
		assertThat(result).isEqualTo("response-post");
	}

	@Test
	void put() {
		clientFourHelper.putBody(Set.of("x", "z"), "response-put");
		final var result = client.putBody(Set.of("x", "z"));
		assertThat(result).isEqualTo("response-put");
	}
}