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

import java.util.Arrays;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Raphael Nascimento
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EnumHelper {
	public static <E extends Enum<E>> E getEnumIgnoreCase(final Class<E> enumClass, final String enumName, final E defaultEnum) {
		if (Objects.isNull(enumName) || !enumClass.isEnum()) {
			return defaultEnum;
		}

		return Arrays.stream(enumClass.getEnumConstants()).filter(e -> e.name().equalsIgnoreCase(enumName)).findAny().orElse(defaultEnum);
	}
}
