import io.github.raphasil.generator.client.rest.api.provider.ClientProvider;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Lazy
@Generated("io.github.raphasil.generator.client.rest.processor.resttemplate.RestTemplateProcessor")
public class GeneratedInterfaceAllHttpMethod implements InterfaceAllHttpMethod {
	private final RestTemplate restTemplate;

	GeneratedInterfaceAllHttpMethod(final ClientProvider<RestTemplate> clientProvider) {
		this.restTemplate = clientProvider.getClient("client-test");
	}

	@Override
	public String delete(final String headerDelete, final String userId, final Integer id,
			final String name, final String typeName) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").path("{type-id}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-delete", "test-delete");
		headers.add("x-header-class", "test-class");
		headers.add("x-header-delete-param", headerDelete);
		final var request = new HttpEntity<>(headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.DELETE, request, String.class);
		return responseEntity.getBody();
	}

	@Override
	public String get(final String headerGet, final String userId, final Integer id,
			final String name, final String typeName) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").path("/{type-name}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-name", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-class", "test-class");
		headers.add("x-header-get", "test-get");
		headers.add("x-header-get-param", headerGet);
		final var request = new HttpEntity<>(headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
		return responseEntity.getBody();
	}

	@Override
	public String head(final String headerHead, final String userId, final Integer id,
			final String name, final String typeName) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-head", "test-head");
		headers.add("x-header-class", "test-class");
		headers.add("x-header-head-param", headerHead);
		final var request = new HttpEntity<>(headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.HEAD, request, String.class);
		return responseEntity.getBody();
	}

	@Override
	public String options(final String headerOptions, final String userId, final Integer id,
			final String name, final String typeName) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-options", "test-options");
		headers.add("x-header-class", "test-class");
		headers.add("x-header-options-param", headerOptions);
		final var request = new HttpEntity<>(headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.OPTIONS, request, String.class);
		return responseEntity.getBody();
	}

	@Override
	public String patch(final String headerPatch, final String userId, final Integer id,
			final String name, final String typeName, final String bodyValue) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-class", "test-class");
		headers.add("x-header-patch", "test-patch");
		headers.add("x-header-patch-param", headerPatch);
		final var request = new HttpEntity<>(bodyValue, headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.PATCH, request, String.class);
		return responseEntity.getBody();
	}

	@Override
	public String post(final String headerPost, final String userId, final Integer id,
			final String name, final String typeName, final String bodyPost) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-post", "test-post");
		headers.add("x-header-class", "test-class");
		headers.add("x-header-post-param", headerPost);
		final var request = new HttpEntity<>(bodyPost, headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
		return responseEntity.getBody();
	}

	@Override
	public String put(final String headerPut, final String userId, final Integer id,
			final String name, final String typeName, final Object bodyAny) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-put", "test-put");
		headers.add("x-header-class", "test-class");
		headers.add("x-header-put-param", headerPut);
		final var request = new HttpEntity<>(bodyAny, headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
		return responseEntity.getBody();
	}

	@Override
	public List<String> deleteFlux(final String headerDelete, final String userId, final Integer id,
			final String name, final String typeName) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").path("{type-id}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-delete", "test-delete");
		headers.add("x-header-class", "test-class");
		headers.add("x-header-delete-param", headerDelete);
		final var request = new HttpEntity<>(headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.DELETE, request, new ParameterizedTypeReference<List<String>>(){});
		return responseEntity.getBody();
	}

	@Override
	public List<String> getFlux(final String headerGet, final String userId, final Integer id,
			final String name, final String typeName) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").path("/{type-name}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-name", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-class", "test-class");
		headers.add("x-header-get", "test-get");
		headers.add("x-header-get-param", headerGet);
		final var request = new HttpEntity<>(headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.GET, request, new ParameterizedTypeReference<List<String>>(){});
		return responseEntity.getBody();
	}

	@Override
	public List<String> headFlux(final String headerHead, final String userId, final Integer id,
			final String name, final String typeName) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-head", "test-head");
		headers.add("x-header-class", "test-class");
		headers.add("x-header-head-param", headerHead);
		final var request = new HttpEntity<>(headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.HEAD, request, new ParameterizedTypeReference<List<String>>(){});
		return responseEntity.getBody();
	}

	@Override
	public List<String> optionsFlux(final String headerOptions, final String userId, final Integer id,
			final String name, final String typeName) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-options", "test-options");
		headers.add("x-header-class", "test-class");
		headers.add("x-header-options-param", headerOptions);
		final var request = new HttpEntity<>(headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.OPTIONS, request, new ParameterizedTypeReference<List<String>>(){});
		return responseEntity.getBody();
	}

	@Override
	public List<String> patchFlux(final String headerPatch, final String userId, final Integer id,
			final String name, final String typeName, final String bodyValue) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-class", "test-class");
		headers.add("x-header-patch", "test-patch");
		headers.add("x-header-patch-param", headerPatch);
		final var request = new HttpEntity<>(bodyValue, headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.PATCH, request, new ParameterizedTypeReference<List<String>>(){});
		return responseEntity.getBody();
	}

	@Override
	public List<String> postFlux(final String headerPost, final String userId, final Integer id,
			final String name, final String typeName, final String bodyPost) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-post", "test-post");
		headers.add("x-header-class", "test-class");
		headers.add("x-header-post-param", headerPost);
		final var request = new HttpEntity<>(bodyPost, headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.POST, request, new ParameterizedTypeReference<List<String>>(){});
		return responseEntity.getBody();
	}

	@Override
	public List<String> putFlux(final String headerPut, final String userId, final Integer id,
			final String name, final String typeName, final Object bodyAny) {
		final var uri = UriComponentsBuilder.newInstance().path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId)).toString();
		final var headers = new HttpHeaders();
		headers.add("x-header-put", "test-put");
		headers.add("x-header-class", "test-class");
		headers.add("x-header-put-param", headerPut);
		final var request = new HttpEntity<>(bodyAny, headers);
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.PUT, request, new ParameterizedTypeReference<List<String>>(){});
		return responseEntity.getBody();
	}
}