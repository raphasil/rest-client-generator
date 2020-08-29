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

import org.junit.jupiter.api.Test;

/**
 * @author Raphael Nascimento
 */
class EnumHelperTest {

	@Test
	void getEnumIgnoreCaseWhenEnumNameIsNullReturnDefaultValue() {
		final var result = EnumHelper.getEnumIgnoreCase(TEST.class, null, TEST.A);
		assertThat(result).isEqualTo(TEST.A);
	}

	@Test
	void getEnumIgnoreCaseWhenEnumClassIsNullReturnDefaultValue() {
		final var result = EnumHelper.getEnumIgnoreCase(null, "b", TEST.A);
		assertThat(result).isEqualTo(TEST.A);
	}

	@Test
	void getEnumIgnoreCaseWhenNotFound() {
		assertThat(EnumHelper.getEnumIgnoreCase(TEST.class, "c", TEST.A)).isEqualTo(TEST.A);
	}

	@Test
	void getEnumIgnoreCase() {
		assertThat(EnumHelper.getEnumIgnoreCase(TEST.class, "a", null)).isEqualTo(TEST.A);
		assertThat(EnumHelper.getEnumIgnoreCase(TEST.class, "A", null)).isEqualTo(TEST.A);
		assertThat(EnumHelper.getEnumIgnoreCase(TEST.class, "b", null)).isEqualTo(TEST.B);
		assertThat(EnumHelper.getEnumIgnoreCase(TEST.class, "B", null)).isEqualTo(TEST.B);
		assertThat(EnumHelper.getEnumIgnoreCase(TEST.class, "c", TEST.A)).isEqualTo(TEST.A);
	}

	enum TEST {
		A, B
	}
}