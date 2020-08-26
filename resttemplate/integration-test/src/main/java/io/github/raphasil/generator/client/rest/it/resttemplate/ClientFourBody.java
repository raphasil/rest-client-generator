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
import io.github.raphasil.generator.client.rest.api.http.Body;
import io.github.raphasil.generator.client.rest.api.http.method.PATCH;
import io.github.raphasil.generator.client.rest.api.http.method.POST;
import io.github.raphasil.generator.client.rest.api.http.method.PUT;

/**
 * @author Raphael Nascimento
 */
@RestClient(value = "client-four", annotations = Component.class)
public interface ClientFourBody {

	@PATCH
	String patchBody(@Body Object any);

	@POST
	String postBody(@Body Object object);

	@PUT
	String putBody(@Body Object value);

}
