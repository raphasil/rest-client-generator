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
 * Query parameter appended to the URL.
 *
 * <p>Example:
 *
 * <pre><code>
 * &#64;GET("/students")
 * Object students(@Query("country-id") int country);
 * </code></pre>
 *
 * Calling with {@code service.students(1)} yields {@code /students?country-id=1}.
 *
 *
 * <pre><code>
 * &#64;GET("/students")
 * Object students(@Query String group);
 * </code></pre>
 *
 * Calling with {@code service.students("test"))} yields {@code /students?group=test}.
 *
 * @author Raphael Nascimento (@raphasil)
 */
@Documented
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.SOURCE)
public @interface Query {
	/**
	 * The query parameter name. If it is not defined, it will be the variable name.
	 */
	String value() default "";
}
