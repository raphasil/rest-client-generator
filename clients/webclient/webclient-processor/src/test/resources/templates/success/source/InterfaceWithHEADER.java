


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

import io.github.raphasil.generator.client.rest.api.RestClient;
import io.github.raphasil.generator.client.rest.api.http.Headers;
import io.github.raphasil.generator.client.rest.api.http.Header;
import io.github.raphasil.generator.client.rest.api.http.method.GET;
import reactor.core.publisher.Mono;

@Headers({
		"x-header-1: test-1",
		"x-header-2: test-2"
})
@RestClient("client-test")
public interface InterfaceWithHEADER {

	@Headers({
			"x-header-3: test-3",
			"x-header-4: test-4"
	})
	@GET
	Mono<String> get(@Header("x-header-5") String header5);
}