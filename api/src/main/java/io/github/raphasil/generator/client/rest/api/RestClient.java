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

package io.github.raphasil.generator.client.rest.api;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Identify an interface to be auto generated
 *
 * @author Raphael Nascimento (@raphasil)
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface RestClient {

	/**
	 * @return The client name to identify a client to be built
	 * @see io.github.raphasil.generator.client.rest.api.provider.ClientProvider
	 */
	String value();

	/**
	 * A relative path of the endpoint. This value is optional.
	 *
	 * <p>Example:
	 *
	 * <pre><code>
	 * &#64;RestClient(value = "client-name", path = "api/user/{id}")
	 * public interface UserService {
	 * 	&#64;GET
	 * 	Object get(@Path Long id);
	 *
	 * 	&#64;DELETE("profile/{profileId}")
	 * 	Object deleteProfile(@Path Integer id, @Path Integer profileId);
	 * }
	 * </code></pre>
	 *
	 * Calling with <code>userService.get(10L)</code> yields <code> api/user/10</code>.
	 * Calling with <code>userService.deleteProfile(10,20)</code> yields <code>api/user/10/profile/20</code>.
	 *
	 * @return The base path for this interface
	 */
	String path() default "";

	/**
	 * A list of annotation to be added to the generated class
	 *
	 * <p>Example:
	 *
	 * <pre><code>
	 *  &#64;RestClient(value = "client-name", annotations = {Lazy.class, Component.class})
	 *  public interface UserService {
	 *  	&#64;GET
	 *  	Object get();
	 *
	 *  	&#64;DELETE
	 *  	Object delete();
	 *  }
	 *  </code></pre>
	 *
	 * <p>It will generate this class:
	 *
	 * <pre><code>
	 *  &#64;Lazy
	 *  &#64;Component
	 *  &#64;Generated( ... )
	 *  public interface GeneratedUser implements UserService {
	 *
	 *  	&#64;Override
	 *  	&#64;GET
	 *  	Object get() {
	 *  	 ...
	 *    }
	 *
	 *      &#64;Override
	 *  	&#64;DELETE
	 *  	Object delete() {
	 *  	 ...
	 *    }
	 *  }
	 *  </code></pre>
	 *
	 *  @return A list of annotations
	 */
	Class<? extends Annotation>[] annotations() default {};
}
