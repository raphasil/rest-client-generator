import io.github.raphasil.generator.client.rest.api.provider.ClientProvider;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Generated("io.github.raphasil.generator.client.rest.processor.resttemplate.RestClientProcessor")
public class GeneratedSimpleInterface implements SimpleInterface {
	private final RestTemplate restTemplate;

	GeneratedSimpleInterface(final ClientProvider<RestTemplate> clientProvider) {
		this.restTemplate = clientProvider.getClient("client-test");
	}

	@Override
	public ResponseEntity<String> test() {
		final var uri = UriComponentsBuilder.newInstance().build().toUri().toString();
		return restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
	}

	@Override
	public String test2() {
		final var uri = UriComponentsBuilder.newInstance().build().toUri().toString();
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
		return responseEntity.getBody();
	}
}