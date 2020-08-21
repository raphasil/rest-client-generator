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

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.raphasil.test.it.IntegrationTest;
import io.github.raphasil.test.it.config.helper.ClientFourHelper;

/**
 * @author Raphael Nascimento
 */
@IntegrationTest
class ClientFourFluxTest {

	@Autowired
	private ClientFourHelper clientFourHelper;

	@Autowired
	private ClientFourFlux client;

	@Test
	void delete() {
		clientFourHelper.deleteFlux(Set.of("1", "2", "3"));
		final var list = client.delete().collectList().block();
		assertThat(list).hasSize(3).containsExactlyInAnyOrder("1", "2", "3");
	}

	@Test
	void get() {
		clientFourHelper.getFlux(Set.of("1", "2", "3"));
		final var list = client.get().collectList().block();
		assertThat(list).hasSize(3).containsExactlyInAnyOrder("1", "2", "3");
	}

	@Test
	void options() {
		clientFourHelper.optionsFlux(Set.of("1", "2", "3"));
		final var list = client.options().collectList().block();
		assertThat(list).hasSize(3).containsExactlyInAnyOrder("1", "2", "3");
	}

	@Test
	void patch() {
		clientFourHelper.patchFlux(Set.of("1", "2", "3"));
		final var list = client.patch().collectList().block();
		assertThat(list).hasSize(3).containsExactlyInAnyOrder("1", "2", "3");
	}

	@Test
	void post() {
		clientFourHelper.postFlux(Set.of("1", "2", "3"));
		final var list = client.post().collectList().block();
		assertThat(list).hasSize(3).containsExactlyInAnyOrder("1", "2", "3");
	}

	@Test
	void put() {
		clientFourHelper.putFlux(Set.of("1", "2", "3"));
		final var list = client.put().collectList().block();
		assertThat(list).hasSize(3).containsExactlyInAnyOrder("1", "2", "3");
	}
}