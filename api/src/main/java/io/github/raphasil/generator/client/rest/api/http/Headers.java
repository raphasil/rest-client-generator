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
 * Adds headers literally supplied in the {@code value}.
 *
 * <pre><code>
 * &#64;Headers("Accept-Charset: utf-8")
 * &#64;PUT("/api/users")
 * ...
 *
 * &#64;Headers({
 *   "x-custom-request: Ping",
 *   "x-custom-response: Pong"
 * })
 * &#64;DELETE("/api/users/{id}")
 * ...
 * </code></pre>
 *
 * @author Raphael Nascimento (@raphasil)
 * @see Header
 */
@Documented
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
public @interface Headers {
	/**
	 * A array of HTTP headers that consists of its name followed by a colon (:), then by its value
	 */
	String[] value();
}
