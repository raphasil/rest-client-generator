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
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
	void getList() {
		helper.get(Arrays.asList(1, 2, 3));
		final var result = client.getList();
		assertThat(result).hasSize(3).containsExactlyInAnyOrder(1, 2, 3);
	}

	@Test
	void getMap() {
		helper.get(Map.of(1, 2, 3, 4));
		final var result = client.getMap();
		assertThat(result).hasSize(2).containsAllEntriesOf(Map.of("1", 2, "3", 4));
	}

	@Test
	void getResponseEntity() {
		helper.get(Map.of("obj", "obj-value"));
		final var result = client.getResponseEntity();
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).isNotNull().isEqualTo(Map.of("obj", "obj-value"));
	}

	@Test
	void getResponseEntityList() {
		helper.get(List.of("a", "b"));
		final var result = client.getResponseEntityList();
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).isNotNull().isEqualTo(List.of("a", "b"));
	}

	@Test
	void getResponseEntityMap() {
		helper.get(Map.of("obj", "obj-value"));
		final var result = client.getResponseEntityMap();
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).isNotNull().containsAllEntriesOf(Map.of("obj", "obj-value"));
	}

	@Test
	void getVoid() {
		helper.get(Map.of("obj", "obj-value"));
		final var result = client.getVoid();
		assertThat(result).isNull();
	}

	@Test
	void getResponseEntityVoid() {
		helper.get(Map.of("obj", "obj-value"));
		final var result = client.getResponseEntityVoid();
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).isNull();
	}
}