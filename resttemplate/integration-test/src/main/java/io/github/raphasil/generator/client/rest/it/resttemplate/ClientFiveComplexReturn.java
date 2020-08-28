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

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.github.raphasil.generator.client.rest.api.RestClient;
import io.github.raphasil.generator.client.rest.api.http.method.GET;

/**
 * @author Raphael Nascimento
 */
@RestClient(value = "client-five", annotations = Component.class)
public interface ClientFiveComplexReturn {

	@GET
	List<Object> getList();

	@GET
	Map<Object, Object> getMap();

	@GET
	ResponseEntity<Object> getResponseEntity();

	@GET
	ResponseEntity<List<Object>> getResponseEntityList();

	@GET
	ResponseEntity<Map<Object, Object>> getResponseEntityMap();

	@GET
	Void getVoid();

	@GET
	ResponseEntity<Void> getResponseEntityVoid();

}
