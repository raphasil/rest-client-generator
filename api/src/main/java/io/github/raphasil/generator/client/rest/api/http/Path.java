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

package io.github.raphasil.generator.client.rest.api.http;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Named replacement in a URL path segment.
 *
 * <p>Example:
 *
 * <pre><code>
 * &#64;GET("resource/{id}")
 * Object getResource(@Path Long id);
 * </code></pre>
 *
 * Calling with {@code service.getResource(10L)} yields {@code /resource/10}.
 *
 * <pre><code>
 * &#64;GET("/user/{cool-nickname}")
 * Object getUserByName(@Path("cool-nickname") String name);
 * </code></pre>
 *
 * Calling <code>service.getUserByName("JonSnow")</code> yields <code>/user/JonSnow</code>
 *
 * @author Raphael Nascimento (@raphasil)
 */
@Documented
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.SOURCE)
public @interface Path {
	/**
	 * @return The path name. If it is not defined, it will be the variable name.
	 */
	String value() default "";
}
