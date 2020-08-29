
import java.util.List;

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

@Headers({
		"x-header-class: test-class"
})
@RestClient(value = "client-test", path = "api/v1/users/{userId}", annotations = {Component.class, Lazy.class})
public interface InterfaceAllHttpMethod {

	@Headers({
			"x-header-delete: test-delete"
	})
	@DELETE("{type-id}")
	String delete(@Header("x-header-delete-param") String headerDelete, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-get: test-get"
	})
	@GET("/{type-name}")
	String get(@Header("x-header-get-param") String headerGet, @Path String userId, @Path("type-name") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-head: test-head"
	})
	@HEAD
	String head(@Header("x-header-head-param") String headerHead, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-options: test-options"
	})
	@OPTIONS
	String options(@Header("x-header-options-param") String headerOptions, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-patch: test-patch"
	})
	@PATCH
	String patch(@Header("x-header-patch-param") String headerPatch, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body String bodyValue);

	@Headers({
			"x-header-post: test-post"
	})
	@POST
	String post(@Header("x-header-post-param") String headerPost, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body String bodyPost);

	@Headers({
			"x-header-put: test-put"
	})
	@PUT
	String put(@Header("x-header-put-param") String headerPut, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body Object bodyAny);

	@Headers({
			"x-header-delete: test-delete"
	})
	@DELETE("{type-id}")
	List<String> deleteFlux(@Header("x-header-delete-param") String headerDelete, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-get: test-get"
	})
	@GET("/{type-name}")
	List<String> getFlux(@Header("x-header-get-param") String headerGet, @Path String userId, @Path("type-name") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-head: test-head"
	})
	@HEAD
	List<String> headFlux(@Header("x-header-head-param") String headerHead, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-options: test-options"
	})
	@OPTIONS
	List<String> optionsFlux(@Header("x-header-options-param") String headerOptions, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName);

	@Headers({
			"x-header-patch: test-patch"
	})
	@PATCH
	List<String> patchFlux(@Header("x-header-patch-param") String headerPatch, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body String bodyValue);

	@Headers({
			"x-header-post: test-post"
	})
	@POST
	List<String> postFlux(@Header("x-header-post-param") String headerPost, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body String bodyPost);

	@Headers({
			"x-header-put: test-put"
	})
	@PUT
	List<String> putFlux(@Header("x-header-put-param") String headerPut, @Path String userId, @Path("type-id") Integer id, @Query String name, @Query("type-name") String typeName, @Body Object bodyAny);

}