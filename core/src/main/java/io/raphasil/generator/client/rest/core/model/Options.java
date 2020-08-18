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

package io.raphasil.generator.client.rest.core.model;

import java.util.Map;
import java.util.Set;

import lombok.Data;

/**
 * @author Raphael Nascimento
 */
@Data
public class Options {
	private static final String SUPPRESS_GENERATOR_TIMESTAMP = "restClientGenerator.suppressGeneratorTimestamp";

	private static final String SUPPRESS_GENERATOR_COMMENT = "restClientGenerator.suppressGeneratorComment";

	private static final String COMPILE_ARG_FORMAT = "-A%s=%s";

	private final Boolean suppressGeneratorTimestamp;

	private final Boolean suppressGeneratorComment;

	public static Options of(final Map<String, String> options) {
		return new Options(Boolean.valueOf(options.get(SUPPRESS_GENERATOR_TIMESTAMP)),
				Boolean.valueOf(options.get(SUPPRESS_GENERATOR_COMMENT)));
	}

	public static Set<String> supportedOptions() {
		return Set.of(SUPPRESS_GENERATOR_TIMESTAMP, SUPPRESS_GENERATOR_COMMENT);
	}

	public Iterable<String> toCompileArgs() {
		return Set.of(String.format(COMPILE_ARG_FORMAT, SUPPRESS_GENERATOR_TIMESTAMP, suppressGeneratorTimestamp),
				String.format(COMPILE_ARG_FORMAT, SUPPRESS_GENERATOR_COMMENT, suppressGeneratorComment));
	}
}
