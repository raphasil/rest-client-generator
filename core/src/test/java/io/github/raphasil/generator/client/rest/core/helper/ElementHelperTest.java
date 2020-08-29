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

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import javax.lang.model.element.Element;

import org.junit.jupiter.api.Test;

import io.github.raphasil.generator.client.rest.api.http.Headers;

/**
 * @author Raphael Nascimento
 */
class ElementHelperTest {

	@Test
	void getHeadersWithNoHerdersAnnotation() {
		final var el = mock(Element.class);
		final var headers = ElementHelper.getHeaders(el);
		assertThat(headers).isEmpty();
	}

	@Test
	void getHeadersWhenDoesNotHaveAValidHeader() {
		final var el = mock(Element.class);
		final var annotation = mock(Headers.class);
		doReturn(new String[] { "a", "b=b", "c:", "d: ", ":e", " :f" }).when(annotation).value();
		doReturn(new Headers[] { annotation }).when(el).getAnnotationsByType(Headers.class);
		final var headers = ElementHelper.getHeaders(el);
		assertThat(headers).isEmpty();
	}

	@Test
	void getHeadersWhenHasValidHeader() {
		final var el = mock(Element.class);
		final var annotation = mock(Headers.class);
		doReturn(new String[] { "a:1", " b : 2 ", "c: 3", " d:4 " }).when(annotation).value();
		doReturn(new Headers[] { annotation }).when(el).getAnnotationsByType(Headers.class);
		final var headers = ElementHelper.getHeaders(el);
		assertThat(headers).hasSize(4).containsAllEntriesOf(Map.of("a", "1", "b", "2", "c", "3", "d", "4"));
	}
}