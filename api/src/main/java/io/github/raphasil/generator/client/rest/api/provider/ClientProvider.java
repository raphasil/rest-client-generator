/*
 * Copyright RestClientGenerator
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.github.raphasil.generator.client.rest.api.provider;

/**
 * Provider for obtaining client instances configured
 *
 * By convention, the generated classes will expect in the constructor an instance of this provider, example:
 *
 * <pre><code>
 *
 *  &#64;RestClient("custom-client")
 *  interface CustomClient {
 *      &#64;GET("resource/{id}")
 *      Object getResource(@Path Long id);
 *  }
 *
 * 	&#64;Generated
 * 	class GeneratedCustomClient {
 * 		private final RestTemplate restTemplate;
 *
 * 		GeneratedCustomClient(final ClientProvider&#x3C;RestTemplate&#x3E; provider) {
 * 			restTemplate = 	provider.getClient("custom-client");
 *        }
 *
 * 	    &#64;Override
 * 	    public Object getResource(final Long id) {
 * 	        ...
 *        }
 *    }
 *
 * </code></pre>
 *
 * @author Raphael Nascimento (@raphasil)
 */
public interface ClientProvider<T> {

	/**
	 * @param clientName
	 * 		A client name
	 * @return A client configured for this client name
	 * @see io.github.raphasil.generator.client.rest.api.RestClient
	 */
	T getClient(String clientName);
}
