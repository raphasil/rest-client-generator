
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