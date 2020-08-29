
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.ClientResponse;

import io.github.raphasil.generator.client.rest.api.RestClient;
import io.github.raphasil.generator.client.rest.api.http.method.GET;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestClient("client-test")
public interface ComplexReturnInterface {

	@GET
	Mono<List<Object>> getMonoList();

	@GET
	Mono<Map<Object, Object>> getMonoMap();

	@GET
	Mono<ClientResponse> getMonoClientResponse();

	@GET
	Mono<ResponseEntity<Object>> getMonoResponseEntity();

	@GET
	Mono<ResponseEntity<List<Object>>> getMonoResponseEntityList();

	@GET
	Mono<ResponseEntity<Map<Object, Object>>> getMonoResponseEntityMap();

	@GET
	Mono<Void> getMonoVoid();

	@GET
	Mono getMono();

	@GET
	Flux<List<Object>> getFluxList();

	@GET
	Flux<Map<Object, Object>> getFluxMap();

	@GET
	Flux<Void> getFluxVoid();

	@GET
	Flux getFlux();

}