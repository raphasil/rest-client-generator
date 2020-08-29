import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.github.raphasil.generator.client.rest.api.RestClient;
import io.github.raphasil.generator.client.rest.api.http.method.GET;

@RestClient(value = "client-five", annotations = Component.class)
public interface ComplexReturnInterface {

	@GET
	List<Object> getList();

	@GET
	Map<Object, Object> getMap();

	@GET
	ResponseEntity<Object> getResponseEntity();

	@GET
	ResponseEntity<List<Object>> getResponseEntityList();

	@GET
	ResponseEntity<Map<Object, Object>> getResponseEntityMap();

	@GET
	Void getVoid();

	@GET
	ResponseEntity<Void> getResponseEntityVoid();

	@GET
	ResponseEntity getResponseEntityNoGenericType();

}