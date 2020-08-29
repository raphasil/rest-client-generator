import io.github.raphasil.generator.client.rest.api.provider.ClientProvider;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Generated("io.github.raphasil.generator.client.rest.processor.webclient.WebClientProcessor")
public class GeneratedInterfaceWithHEADER implements InterfaceWithHEADER {
	private final WebClient webClient;

	GeneratedInterfaceWithHEADER(final ClientProvider<WebClient> clientProvider) {
		this.webClient = clientProvider.getClient("client-test");
	}

	@Override
	public Mono<String> get(final String header5) {
		return webClient.get().header("x-header-4", "test-4").header("x-header-3", "test-3").header("x-header-2", "test-2").header("x-header-1", "test-1").header("x-header-5", header5).retrieve().bodyToMono(String.class);
	}
}