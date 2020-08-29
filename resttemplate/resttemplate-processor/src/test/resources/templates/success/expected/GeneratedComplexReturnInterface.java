import io.github.raphasil.generator.client.rest.api.provider.ClientProvider;
import java.lang.Object;
import java.lang.Override;
import java.lang.Void;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Generated("io.github.raphasil.generator.client.rest.processor.resttemplate.RestTemplateProcessor")
public class GeneratedComplexReturnInterface implements ComplexReturnInterface {
	private final RestTemplate restTemplate;

	GeneratedComplexReturnInterface(final ClientProvider<RestTemplate> clientProvider) {
		this.restTemplate = clientProvider.getClient("client-five");
	}

	@Override
	public List<Object> getList() {
		final var uri = UriComponentsBuilder.newInstance().build().toUri().toString();
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Object>>(){});
		return responseEntity.getBody();
	}

	@Override
	public Map<Object, Object> getMap() {
		final var uri = UriComponentsBuilder.newInstance().build().toUri().toString();
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Map<Object, Object>>(){});
		return responseEntity.getBody();
	}

	@Override
	public ResponseEntity<Object> getResponseEntity() {
		final var uri = UriComponentsBuilder.newInstance().build().toUri().toString();
		return restTemplate.exchange(uri, HttpMethod.GET, null, Object.class);
	}

	@Override
	public ResponseEntity<List<Object>> getResponseEntityList() {
		final var uri = UriComponentsBuilder.newInstance().build().toUri().toString();
		return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Object>>(){});
	}

	@Override
	public ResponseEntity<Map<Object, Object>> getResponseEntityMap() {
		final var uri = UriComponentsBuilder.newInstance().build().toUri().toString();
		return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Map<Object, Object>>(){});
	}

	@Override
	public Void getVoid() {
		final var uri = UriComponentsBuilder.newInstance().build().toUri().toString();
		final var responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, Void.class);
		return responseEntity.getBody();
	}

	@Override
	public ResponseEntity<Void> getResponseEntityVoid() {
		final var uri = UriComponentsBuilder.newInstance().build().toUri().toString();
		return restTemplate.exchange(uri, HttpMethod.GET, null, Void.class);
	}

	@Override
	public ResponseEntity getResponseEntityNoGenericType() {
		final var uri = UriComponentsBuilder.newInstance().build().toUri().toString();
		return restTemplate.exchange(uri, HttpMethod.GET, null, Object.class);
	}
}