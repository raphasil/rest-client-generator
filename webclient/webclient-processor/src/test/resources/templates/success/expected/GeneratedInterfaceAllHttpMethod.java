import io.github.raphasil.generator.client.rest.api.provider.ClientProvider;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Lazy
@Generated("io.github.raphasil.generator.client.rest.processor.webclient.WebClientProcessor")
public class GeneratedInterfaceAllHttpMethod implements InterfaceAllHttpMethod {
	private final WebClient webClient;

	GeneratedInterfaceAllHttpMethod(final ClientProvider<WebClient> clientProvider) {
		this.webClient = clientProvider.getClient("client-test");
	}

	@Override
	public Mono<String> delete(final String headerDelete, final String userId, final Integer id,
			final String name, final String typeName) {
		return webClient.delete().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").path("{type-id}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId))).header("x-header-delete", "test-delete").header("x-header-class", "test-class").header("x-header-delete-param", headerDelete).retrieve().bodyToMono(String.class);
	}

	@Override
	public Mono<String> get(final String headerGet, final String userId, final Integer id,
			final String name, final String typeName) {
		return webClient.get().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").path("/{type-name}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-name", id,"userId", userId))).header("x-header-class", "test-class").header("x-header-get", "test-get").header("x-header-get-param", headerGet).retrieve().bodyToMono(String.class);
	}

	@Override
	public Mono<String> head(final String headerHead, final String userId, final Integer id,
			final String name, final String typeName) {
		return webClient.head().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId))).header("x-header-head", "test-head").header("x-header-class", "test-class").header("x-header-head-param", headerHead).retrieve().bodyToMono(String.class);
	}

	@Override
	public Mono<String> options(final String headerOptions, final String userId, final Integer id,
			final String name, final String typeName) {
		return webClient.options().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId))).header("x-header-options", "test-options").header("x-header-class", "test-class").header("x-header-options-param", headerOptions).retrieve().bodyToMono(String.class);
	}

	@Override
	public Mono<String> patch(final String headerPatch, final String userId, final Integer id,
			final String name, final String typeName, final String bodyValue) {
		return webClient.patch().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId))).header("x-header-class", "test-class").header("x-header-patch", "test-patch").header("x-header-patch-param", headerPatch).bodyValue(bodyValue).retrieve().bodyToMono(String.class);
	}

	@Override
	public Mono<String> post(final String headerPost, final String userId, final Integer id,
			final String name, final String typeName, final String bodyPost) {
		return webClient.post().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId))).header("x-header-post", "test-post").header("x-header-class", "test-class").header("x-header-post-param", headerPost).bodyValue(bodyPost).retrieve().bodyToMono(String.class);
	}

	@Override
	public Mono<String> put(final String headerPut, final String userId, final Integer id,
			final String name, final String typeName, final Object bodyAny) {
		return webClient.put().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId))).header("x-header-put", "test-put").header("x-header-class", "test-class").header("x-header-put-param", headerPut).bodyValue(bodyAny).retrieve().bodyToMono(String.class);
	}

	@Override
	public Flux<String> deleteFlux(final String headerDelete, final String userId, final Integer id,
			final String name, final String typeName) {
		return webClient.delete().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").path("{type-id}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId))).header("x-header-delete", "test-delete").header("x-header-class", "test-class").header("x-header-delete-param", headerDelete).retrieve().bodyToFlux(String.class);
	}

	@Override
	public Flux<String> getFlux(final String headerGet, final String userId, final Integer id,
			final String name, final String typeName) {
		return webClient.get().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").path("/{type-name}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-name", id,"userId", userId))).header("x-header-class", "test-class").header("x-header-get", "test-get").header("x-header-get-param", headerGet).retrieve().bodyToFlux(String.class);
	}

	@Override
	public Flux<String> headFlux(final String headerHead, final String userId, final Integer id,
			final String name, final String typeName) {
		return webClient.head().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId))).header("x-header-head", "test-head").header("x-header-class", "test-class").header("x-header-head-param", headerHead).retrieve().bodyToFlux(String.class);
	}

	@Override
	public Flux<String> optionsFlux(final String headerOptions, final String userId, final Integer id,
			final String name, final String typeName) {
		return webClient.options().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId))).header("x-header-options", "test-options").header("x-header-class", "test-class").header("x-header-options-param", headerOptions).retrieve().bodyToFlux(String.class);
	}

	@Override
	public Flux<String> patchFlux(final String headerPatch, final String userId, final Integer id,
			final String name, final String typeName, final String bodyValue) {
		return webClient.patch().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId))).header("x-header-class", "test-class").header("x-header-patch", "test-patch").header("x-header-patch-param", headerPatch).bodyValue(bodyValue).retrieve().bodyToFlux(String.class);
	}

	@Override
	public Flux<String> postFlux(final String headerPost, final String userId, final Integer id,
			final String name, final String typeName, final String bodyPost) {
		return webClient.post().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId))).header("x-header-post", "test-post").header("x-header-class", "test-class").header("x-header-post-param", headerPost).bodyValue(bodyPost).retrieve().bodyToFlux(String.class);
	}

	@Override
	public Flux<String> putFlux(final String headerPut, final String userId, final Integer id,
			final String name, final String typeName, final Object bodyAny) {
		return webClient.put().uri(uriBuilder -> uriBuilder.path("api/v1/users/{userId}").queryParam("name", name).queryParam("type-name", typeName).build(Map.of("type-id", id,"userId", userId))).header("x-header-put", "test-put").header("x-header-class", "test-class").header("x-header-put-param", headerPut).bodyValue(bodyAny).retrieve().bodyToFlux(String.class);
	}
}