


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

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import io.github.raphasil.generator.client.rest.api.RestClient;
import io.github.raphasil.generator.client.rest.api.http.Headers;
import io.github.raphasil.generator.client.rest.api.http.Header;
import io.github.raphasil.generator.client.rest.api.http.Path;
import io.github.raphasil.generator.client.rest.api.http.Body;
import io.github.raphasil.generator.client.rest.api.http.Query;
import io.github.raphasil.generator.client.rest.api.http.method.DELETE;
import io.github.raphasil.generator.client.rest.api.http.method.GET;
import io.github.raphasil.generator.client.rest.api.http.method.HEAD;
import io.github.raphasil.generator.client.rest.api.http.method.OPTIONS;
import io.github.raphasil.generator.client.rest.api.http.method.PATCH;
import io.github.raphasil.generator.client.rest.api.http.method.POST;
import io.github.raphasil.generator.client.rest.api.http.method.PUT;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Headers({
		"x-header-class: test-class"
})
@RestClient(value = "client-test", path = "api/v1/users/{userId}", annotations = {Component.class, Lazy.class})
public interface InterfaceAllHttpMethod {

	@Headers({
			"x-header-delete: test-delete"
	})
	@DELETE("{type-id}")
	Mono<String> delete(@Header("x-header-delete-param") String headerDelete, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-get: test-get"
	})
	@GET("/{type-name}")
	Mono<String> get(@Header("x-header-get-param") String headerGet, @Path String userId, @Path("type-name") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-head: test-head"
	})
	@HEAD
	Mono<String> head(@Header("x-header-head-param") String headerHead, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-options: test-options"
	})
	@OPTIONS
	Mono<String> options(@Header("x-header-options-param") String headerOptions, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-patch: test-patch"
	})
	@PATCH
	Mono<String> patch(@Header("x-header-patch-param") String headerPatch, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body String bodyValue);

	@Headers({
			"x-header-post: test-post"
	})
	@POST
	Mono<String> post(@Header("x-header-post-param") String headerPost, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body String bodyPost);

	@Headers({
			"x-header-put: test-put"
	})
	@PUT
	Mono<String> put(@Header("x-header-put-param") String headerPut, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body Object bodyAny);

	@Headers({
			"x-header-delete: test-delete"
	})
	@DELETE("{type-id}")
	Flux<String> deleteFlux(@Header("x-header-delete-param") String headerDelete, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-get: test-get"
	})
	@GET("/{type-name}")
	Flux<String> getFlux(@Header("x-header-get-param") String headerGet, @Path String userId, @Path("type-name") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-head: test-head"
	})
	@HEAD
	Flux<String> headFlux(@Header("x-header-head-param") String headerHead, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-options: test-options"
	})
	@OPTIONS
	Flux<String> optionsFlux(@Header("x-header-options-param") String headerOptions, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-patch: test-patch"
	})
	@PATCH
	Flux<String> patchFlux(@Header("x-header-patch-param") String headerPatch, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body String bodyValue);

	@Headers({
			"x-header-post: test-post"
	})
	@POST
	Flux<String> postFlux(@Header("x-header-post-param") String headerPost, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body String bodyPost);

	@Headers({
			"x-header-put: test-put"
	})
	@PUT
	Flux<String> putFlux(@Header("x-header-put-param") String headerPut, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body Object bodyAny);

}