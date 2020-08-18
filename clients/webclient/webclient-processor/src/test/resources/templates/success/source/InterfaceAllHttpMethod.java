


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import io.raphasil.generator.client.rest.api.RestClient;
import io.raphasil.generator.client.rest.api.http.HEADERS;
import io.raphasil.generator.client.rest.api.http.Header;
import io.raphasil.generator.client.rest.api.http.Path;
import io.raphasil.generator.client.rest.api.http.Body;
import io.raphasil.generator.client.rest.api.http.Query;
import io.raphasil.generator.client.rest.api.http.method.DELETE;
import io.raphasil.generator.client.rest.api.http.method.GET;
import io.raphasil.generator.client.rest.api.http.method.HEAD;
import io.raphasil.generator.client.rest.api.http.method.OPTIONS;
import io.raphasil.generator.client.rest.api.http.method.PATCH;
import io.raphasil.generator.client.rest.api.http.method.POST;
import io.raphasil.generator.client.rest.api.http.method.PUT;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@HEADERS({
		"x-header-class: test-class"
})
@RestClient(value = "client-test", path = "api/v1/users/{userId}", annotations = {Component.class, Lazy.class})
public interface InterfaceAllHttpMethod {

	@HEADERS({
			"x-header-delete: test-delete"
	})
	@DELETE("{type-id}")
	Mono<String> delete(@Header("x-header-delete-param") String headerDelete, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@HEADERS({
			"x-header-get: test-get"
	})
	@GET("/{type-name}")
	Mono<String> get(@Header("x-header-get-param") String headerGet, @Path String userId, @Path("type-name") Integer id, @Query String name, @Query("type-name") String typeName);

	@HEADERS({
			"x-header-head: test-head"
	})
	@HEAD
	Mono<String> head(@Header("x-header-head-param") String headerHead, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@HEADERS({
			"x-header-options: test-options"
	})
	@OPTIONS
	Mono<String> options(@Header("x-header-options-param") String headerOptions, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@HEADERS({
			"x-header-patch: test-patch"
	})
	@PATCH
	Mono<String> patch(@Header("x-header-patch-param") String headerPatch, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body String bodyValue);

	@HEADERS({
			"x-header-post: test-post"
	})
	@POST
	Mono<String> post(@Header("x-header-post-param") String headerPost, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body String bodyPost);

	@HEADERS({
			"x-header-put: test-put"
	})
	@PUT
	Mono<String> put(@Header("x-header-put-param") String headerPut, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body Object bodyAny);

	@HEADERS({
			"x-header-delete: test-delete"
	})
	@DELETE("{type-id}")
	Flux<String> deleteFlux(@Header("x-header-delete-param") String headerDelete, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@HEADERS({
			"x-header-get: test-get"
	})
	@GET("/{type-name}")
	Flux<String> getFlux(@Header("x-header-get-param") String headerGet, @Path String userId, @Path("type-name") Integer id, @Query String name, @Query("type-name") String typeName);

	@HEADERS({
			"x-header-head: test-head"
	})
	@HEAD
	Flux<String> headFlux(@Header("x-header-head-param") String headerHead, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@HEADERS({
			"x-header-options: test-options"
	})
	@OPTIONS
	Flux<String> optionsFlux(@Header("x-header-options-param") String headerOptions, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@HEADERS({
			"x-header-patch: test-patch"
	})
	@PATCH
	Flux<String> patchFlux(@Header("x-header-patch-param") String headerPatch, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body String bodyValue);

	@HEADERS({
			"x-header-post: test-post"
	})
	@POST
	Flux<String> postFlux(@Header("x-header-post-param") String headerPost, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body String bodyPost);

	@HEADERS({
			"x-header-put: test-put"
	})
	@PUT
	Flux<String> putFlux(@Header("x-header-put-param") String headerPut, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body Object bodyAny);

}