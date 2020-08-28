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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;

import io.github.raphasil.test.it.IntegrationTest;
import io.github.raphasil.test.it.config.helper.ClientFiveHelper;

/**
 * @author Raphael Nascimento
 */
@IntegrationTest
class ClientFiveComplexReturnTest {

	@Autowired
	private ClientFiveHelper helper;

	@Autowired
	private ClientFiveComplexReturn client;

	@Test
	void getMonoList() {
		helper.get(Arrays.asList(1, 2, 3));
		final var result = client.getMonoList().block();
		assertThat(result).hasSize(3).containsExactlyInAnyOrder(1, 2, 3);
	}

	@Test
	void getMonoMap() {
		helper.get(Map.of(1, 2, 3, 4));
		final var result = client.getMonoMap().block();
		assertThat(result).hasSize(2).containsAllEntriesOf(Map.of("1", 2, "3", 4));
	}

	@Test
	void getMonoClientResponse() {
		helper.get(Map.of("obj", "obj-value"));
		final var result = client.getMonoClientResponse().flatMap(r -> r.bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
		})).block();
		assertThat(result).hasSize(1).containsAllEntriesOf(Map.of("obj", "obj-value"));
	}

	@Test
	void getMonoResponseEntity() {
		helper.get(List.of(1, 2, 3, 4));
		final var result = client.getMonoResponseEntity().block();
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).isEqualTo(List.of(1, 2, 3, 4));
	}

	@Test
	void getMonoResponseEntityList() {
		helper.get(List.of(1, 2, 3, 4));
		final var result = client.getMonoResponseEntityList().block();
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).hasSize(4).isEqualTo(List.of(1, 2, 3, 4));
	}

	@Test
	void getMonoResponseEntityMap() {
		helper.get(Map.of(1, 2, 3, 4));
		final var result = client.getMonoResponseEntityMap().block();
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).hasSize(2).containsAllEntriesOf(Map.of("1", 2, "3", 4));
	}

	@Test
	void getMonoVoid() {
		helper.get(Map.of(1, 2, 3, 4));
		final var result = client.getMonoVoid().block();
		assertThat(result).isNull();
	}

	@Test
	@Disabled("Apparently WebClient can not process Flux<List<Object>> with ParameterizedTypeReference")
	void getFluxList() {
		helper.get(List.of(List.of(1, 2, 3, 4)));
		final var result = client.getFluxList().collectList().block();
		assertThat(result).hasSize(1).isNotNull();
	}

	@Test
	void getFluxMap() {
		helper.get(List.of(Map.of("obj", "obj-value")));
		final var result = client.getFluxMap().collectList().block();
		assertThat(result).hasSize(1).isNotNull();
		assertThat(result.get(0)).hasSize(1).containsAllEntriesOf(Map.of("obj", "obj-value"));
	}

	@Test
	void getFluxVoid() {
		helper.get(List.of(Map.of("obj", "obj-value")));
		final var result = client.getFluxVoid().collectList().block();
		assertThat(result).isNotNull().isEmpty();
	}

}