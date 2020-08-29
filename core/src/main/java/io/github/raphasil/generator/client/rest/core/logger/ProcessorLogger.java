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

package io.github.raphasil.generator.client.rest.core.logger;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;

import io.github.raphasil.generator.client.rest.core.helper.EnumHelper;

/**
 * @author Raphael Nascimento
 */
public final class ProcessorLogger {

	private final int logLevel;

	private final Messager messager;

	private ProcessorLogger(final Messager messager, final int logLevel) {
		this.messager = messager;
		this.logLevel = logLevel;
	}

	public static ProcessorLogger create(final ProcessingEnvironment env, final String level) {
		final var m = env.getMessager();
		final var logLevel = EnumHelper.getEnumIgnoreCase(Level.class, level, Level.WARN).ordinal();
		return new ProcessorLogger(m, logLevel);
	}

	public void trace(final String message, final Object... arguments) {
		log(Level.TRACE, message, arguments);
	}

	public void warn(final String message, final Object... arguments) {
		log(Level.WARN, message, arguments);
	}

	public void error(final String message, final Object... arguments) {
		log(Level.ERROR, message, arguments);
	}

	private void log(Level level, final String message, final Object... arguments) {
		if (logLevel <= level.ordinal()) {
			messager.printMessage(level.getDiagnosticKind(), String.format(message, arguments));
		}
	}

	private enum Level {
		TRACE(Diagnostic.Kind.NOTE), DEBUG(Diagnostic.Kind.NOTE), INFO(Diagnostic.Kind.NOTE), WARN(Diagnostic.Kind.MANDATORY_WARNING),
		ERROR(Diagnostic.Kind.ERROR);

		private final Diagnostic.Kind diagnosticKind;

		Level(final Diagnostic.Kind diagnosticKind) {
			this.diagnosticKind = diagnosticKind;
		}

		public Diagnostic.Kind getDiagnosticKind() {
			return diagnosticKind;
		}
	}

}
