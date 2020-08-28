import io.github.raphasil.generator.client.rest.api.provider.ClientProvider;
import javax.annotation.processing.Generated;
import org.springframework.web.reactive.function.client.WebClient;

@Generated("io.github.raphasil.generator.client.rest.processor.webclient.WebClientProcessor")
public class GeneratedEmptyInterface implements EmptyInterface {
	private final WebClient webClient;

	GeneratedEmptyInterface(final ClientProvider<WebClient> clientProvider) {
		this.webClient = clientProvider.getClient("client-test");
	}
}