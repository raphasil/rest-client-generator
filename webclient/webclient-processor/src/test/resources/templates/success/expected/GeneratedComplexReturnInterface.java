import io.github.raphasil.generator.client.rest.api.provider.ClientProvider;
import java.lang.Object;
import java.lang.Override;
import java.lang.Void;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Generated("io.github.raphasil.generator.client.rest.processor.webclient.WebClientProcessor")
public class GeneratedComplexReturnInterface implements ComplexReturnInterface {
	private final WebClient webClient;

	GeneratedComplexReturnInterface(final ClientProvider<WebClient> clientProvider) {
		this.webClient = clientProvider.getClient("client-test");
	}

	@Override
	public Mono<List<Object>> getMonoList() {
		return webClient.get().retrieve().bodyToMono(new ParameterizedTypeReference<List<Object>>(){});
	}

	@Override
	public Mono<Map<Object, Object>> getMonoMap() {
		return webClient.get().retrieve().bodyToMono(new ParameterizedTypeReference<Map<Object, Object>>(){});
	}

	@Override
	public Mono<ClientResponse> getMonoClientResponse() {
		return webClient.get().exchange();
	}

	@Override
	public Mono<ResponseEntity<Object>> getMonoResponseEntity() {
		return webClient.get().retrieve().toEntity(Object.class);
	}

	@Override
	public Mono<ResponseEntity<List<Object>>> getMonoResponseEntityList() {
		return webClient.get().retrieve().toEntity(new ParameterizedTypeReference<List<Object>>(){});
	}

	@Override
	public Mono<ResponseEntity<Map<Object, Object>>> getMonoResponseEntityMap() {
		return webClient.get().retrieve().toEntity(new ParameterizedTypeReference<Map<Object, Object>>(){});
	}

	@Override
	public Mono<Void> getMonoVoid() {
		return webClient.get().retrieve().bodyToMono(Void.class);
	}

	@Override
	public Mono getMono() {
		return webClient.get().retrieve().bodyToMono(Object.class);
	}

	@Override
	public Flux<List<Object>> getFluxList() {
		return webClient.get().retrieve().bodyToFlux(new ParameterizedTypeReference<List<Object>>(){});
	}

	@Override
	public Flux<Map<Object, Object>> getFluxMap() {
		return webClient.get().retrieve().bodyToFlux(new ParameterizedTypeReference<Map<Object, Object>>(){});
	}

	@Override
	public Flux<Void> getFluxVoid() {
		return webClient.get().retrieve().bodyToFlux(Void.class);
	}

	@Override
	public Flux getFlux() {
		return webClient.get().retrieve().bodyToFlux(Object.class);
	}
}