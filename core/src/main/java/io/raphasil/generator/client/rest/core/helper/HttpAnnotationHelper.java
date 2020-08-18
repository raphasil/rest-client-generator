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

package io.raphasil.generator.client.rest.core.helper;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

import io.raphasil.generator.client.rest.api.http.method.DELETE;
import io.raphasil.generator.client.rest.api.http.method.GET;
import io.raphasil.generator.client.rest.api.http.method.HEAD;
import io.raphasil.generator.client.rest.api.http.method.OPTIONS;
import io.raphasil.generator.client.rest.api.http.method.PATCH;
import io.raphasil.generator.client.rest.api.http.method.POST;
import io.raphasil.generator.client.rest.api.http.method.PUT;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Raphael Nascimento
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpAnnotationHelper {

	private static final List<Class<? extends Annotation>> httpMethodAnnotations = Arrays.asList(DELETE.class,
			GET.class,
			HEAD.class,
			OPTIONS.class,
			PATCH.class,
			POST.class,
			PUT.class);

	public static boolean isMethodAnnotedWithHttp(final ExecutableElement element) {
		return isAnnotatedWithAny(element, httpMethodAnnotations);
	}

	public static Annotation findFirstHttpAnnotation(final ExecutableElement element) {
		return findFirstAnnotation(element, httpMethodAnnotations);
	}

	private static Annotation findFirstAnnotation(final Element element, final List<Class<? extends Annotation>> annotations) {
		return annotations.stream()
				.map(a -> Optional.ofNullable(element.getAnnotation(a)))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.findAny()
				.orElse(null);
	}

	private static boolean isAnnotatedWithAny(final Element element, final List<Class<? extends Annotation>> annotations) {
		return findFirstAnnotation(element, annotations) != null;
	}

	public static String getPath(final Annotation annotation) {

		if (annotation instanceof DELETE) {
			return ((DELETE) annotation).value();
		}

		if (annotation instanceof GET) {
			return ((GET) annotation).value();
		}

		if (annotation instanceof HEAD) {
			return ((HEAD) annotation).value();
		}

		if (annotation instanceof OPTIONS) {
			return ((OPTIONS) annotation).value();
		}

		if (annotation instanceof PATCH) {
			return ((PATCH) annotation).value();
		}

		if (annotation instanceof POST) {
			return ((POST) annotation).value();
		}

		if (annotation instanceof PUT) {
			return ((PUT) annotation).value();
		}

		return "";
	}

}
