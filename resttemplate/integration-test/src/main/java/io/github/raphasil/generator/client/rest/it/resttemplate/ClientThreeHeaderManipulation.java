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

import org.springframework.stereotype.Component;

import io.github.raphasil.generator.client.rest.api.RestClient;
import io.github.raphasil.generator.client.rest.api.http.Header;
import io.github.raphasil.generator.client.rest.api.http.Headers;
import io.github.raphasil.generator.client.rest.api.http.method.GET;

/**
 * @author Raphael Nascimento
 */
@RestClient(value = "client-three", annotations = Component.class)
public interface ClientThreeHeaderManipulation {

	@GET
	@Headers({ "x-method-1: method-1", "x-method-2: method-2" })
	String getWithMethodAndParam(@Header("x-custom") String value);

	@GET
	@Headers({ "x-method-1: method-1", "x-method-2: method-2" })
	String getWithMethod();

	@GET
	String getWithParam(@Header("x-custom") String value);

}
