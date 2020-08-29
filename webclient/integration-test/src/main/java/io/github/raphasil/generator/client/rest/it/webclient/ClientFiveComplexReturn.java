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

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;

import io.github.raphasil.generator.client.rest.api.RestClient;
import io.github.raphasil.generator.client.rest.api.http.method.GET;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Raphael Nascimento
 */
@RestClient(value = "client-five", annotations = Component.class)
public interface ClientFiveComplexReturn {

	@GET
	Mono<List<Object>> getMonoList();

	@GET
	Mono<Map<Object, Object>> getMonoMap();

	@GET
	Mono<ClientResponse> getMonoClientResponse();

	@GET
	Mono<ResponseEntity<Object>> getMonoResponseEntity();

	@GET
	Mono<ResponseEntity<List<Object>>> getMonoResponseEntityList();

	@GET
	Mono<ResponseEntity<Map<Object, Object>>> getMonoResponseEntityMap();

	@GET
	Mono<Void> getMonoVoid();

	@GET
	Mono getMono();

	@GET
	Flux<List<Object>> getFluxList();

	@GET
	Flux<Map<Object, Object>> getFluxMap();

	@GET
	Flux<Void> getFluxVoid();

	@GET
	Flux getFlux();

}
