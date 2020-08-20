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

package io.github.raphasil.generator.client.rest.core.helper;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.element.Element;

import io.github.raphasil.generator.client.rest.api.http.Headers;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Raphael Nascimento
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ElementHelper {

	public static Map<String, String> getHeaders(final Element element) {
		final var annotations = element.getAnnotationsByType(Headers.class);
		if (Objects.isNull(annotations)) {
			return Collections.emptyMap();
		}

		return Stream.of(annotations)
				.map(Headers::value)
				.flatMap(Stream::of)
				.map(s -> s.split("\\s*:\\s*"))
				.filter(a -> a.length == 2)
				.collect(Collectors.toMap(a -> a[0], a -> a[1]));
	}

	public static String getSimpleName(final Element element) {
		return element.getSimpleName().toString();
	}

}
