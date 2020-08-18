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

package io.raphasil.generator.client.rest.core.logger;

import java.util.Map;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Raphael Nascimento
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProcessorLogger {

	public static Logger createLoggerFor(final Class<?> clazz, final ProcessingEnvironment env, final String level) {
		final var lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		final var ple = new PatternLayoutEncoder();
		ple.setPattern("%level [%thread] %logger{10} %msg%n");
		ple.setContext(lc);
		ple.start();

		final var messageAppender = new MessageAppender(env);
		messageAppender.setContext(lc);
		messageAppender.start();

		final var logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(clazz);
		logger.detachAppender("console");
		logger.addAppender(messageAppender);
		logger.setLevel(Level.toLevel(level));

		return logger;
	}

	private static class MessageAppender extends AppenderBase<ILoggingEvent> {

		private static final Map<Level, Diagnostic.Kind> levelMap = Map.of(Level.WARN,
				Diagnostic.Kind.MANDATORY_WARNING,
				Level.ERROR,
				Diagnostic.Kind.ERROR);

		private final Messager messager;

		public MessageAppender(final ProcessingEnvironment env) {
			messager = env.getMessager();
		}

		@Override
		public String getName() {
			return "processing-message-appender";
		}

		@Override
		protected void append(final ILoggingEvent event) {
			final var kind = levelMap.getOrDefault(event.getLevel(), Diagnostic.Kind.NOTE);
			messager.printMessage(kind, event.getFormattedMessage());
		}
	}
}
