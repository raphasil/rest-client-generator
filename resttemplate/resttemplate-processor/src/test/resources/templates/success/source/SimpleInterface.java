
import org.springframework.http.ResponseEntity;

import io.github.raphasil.generator.client.rest.api.RestClient;
import io.github.raphasil.generator.client.rest.api.http.method.GET;

@RestClient("client-test")
public interface SimpleInterface {

	@GET
	ResponseEntity<String> test();

	@GET
	String test2();

}