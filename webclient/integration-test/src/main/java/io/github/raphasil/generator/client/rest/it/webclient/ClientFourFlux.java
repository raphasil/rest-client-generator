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

import org.springframework.stereotype.Component;

import io.github.raphasil.generator.client.rest.api.RestClient;
import io.github.raphasil.generator.client.rest.api.http.method.DELETE;
import io.github.raphasil.generator.client.rest.api.http.method.GET;
import io.github.raphasil.generator.client.rest.api.http.method.OPTIONS;
import io.github.raphasil.generator.client.rest.api.http.method.PATCH;
import io.github.raphasil.generator.client.rest.api.http.method.POST;
import io.github.raphasil.generator.client.rest.api.http.method.PUT;
import reactor.core.publisher.Flux;

/**
 * @author Raphael Nascimento
 */
@RestClient(value = "client-four", annotations = Component.class)
public interface ClientFourFlux {

	@DELETE
	Flux<Object> delete();

	@GET
	Flux<Object> get();

	@OPTIONS
	Flux<Object> options();

	@PATCH
	Flux<Object> patch();

	@POST
	Flux<Object> post();

	@PUT
	Flux<Object> put();

}
