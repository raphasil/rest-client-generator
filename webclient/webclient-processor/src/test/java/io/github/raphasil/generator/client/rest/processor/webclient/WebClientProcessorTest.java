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

package io.github.raphasil.generator.client.rest.processor.webclient;

import static com.google.testing.compile.CompilationSubject.*;
import static com.google.testing.compile.Compiler.*;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.google.testing.compile.JavaFileObjects;

import io.github.raphasil.generator.client.rest.core.model.Options;

/**
 * @author Raphael Nascimento
 */
class WebClientProcessorTest {

	private static Stream<String> listSuccessTemplates() {
		final var classLoader = WebClientProcessorTest.class.getClassLoader();
		final var resource = classLoader.getResource("templates/success/source");
		final var file = Objects.requireNonNull(resource).getFile();
		final var directory = new File(file);

		return Arrays.stream(Objects.requireNonNull(directory.list())).map(s -> s.replace(".java", ""));
	}

	@DisplayName("Validate Success Templates")
	@ParameterizedTest(name = "{0}.java should generate Generated{0}.java")
	@MethodSource("listSuccessTemplates")
	void validateSuccessTemplates(final String templateName) {
		final var options = Options.builder()
				.suppressGeneratorComment(true)
				.suppressGeneratorTimestamp(true)
				.logLevel("trace")
				.build()
				.toCompileArgs();

		final var compilation = javac().withOptions(options)
				.withProcessors(new WebClientProcessor())
				.compile(JavaFileObjects.forResource("templates/success/source/" + templateName + ".java"));

		assertThat(compilation).succeeded();
		assertThat(compilation).generatedSourceFile("Generated" + templateName)
				.hasSourceEquivalentTo(JavaFileObjects.forResource("templates/success/expected/Generated" + templateName + ".java"));
	}

	@Test
	void whenDoesNotReturnMonoOrFlux() {

		final var options = Options.builder()
				.suppressGeneratorComment(true)
				.suppressGeneratorTimestamp(true)
				.logLevel("trace")
				.build()
				.toCompileArgs();

		final var compilation = javac().withOptions(options)
				.withProcessors(new WebClientProcessor())
				.compile(JavaFileObjects.forResource("templates/error/InvalidReturnInterface.java"));

		assertThat(compilation).failed();
		assertThat(compilation).hadErrorContaining("This method is not returning a valid type (Mono or Flux)");

	}

	@Test
	void whenNoReturn() {

		final var options = Options.builder()
				.suppressGeneratorComment(true)
				.suppressGeneratorTimestamp(true)
				.logLevel("trace")
				.build()
				.toCompileArgs();

		final var compilation = javac().withOptions(options)
				.withProcessors(new WebClientProcessor())
				.compile(JavaFileObjects.forResource("templates/error/InvalidWhenNoReturnInterface.java"));

		assertThat(compilation).failed();
		assertThat(compilation).hadErrorContaining("This method is not returning a valid type (Mono or Flux)");

	}

	@Test
	void whenReturnFluxClientResponse() {

		final var options = Options.builder()
				.suppressGeneratorComment(true)
				.suppressGeneratorTimestamp(true)
				.logLevel("trace")
				.build()
				.toCompileArgs();

		final var compilation = javac().withOptions(options)
				.withProcessors(new WebClientProcessor())
				.compile(JavaFileObjects.forResource("templates/error/InvalidReturnForFluxClientResponseInterface.java"));

		assertThat(compilation).failed();
		assertThat(compilation).hadErrorContaining("Webclient can only produce a Mono of ClientResponse");

	}

	@Test
	void whenReturnFluxResponseEntity() {

		final var options = Options.builder()
				.suppressGeneratorComment(true)
				.suppressGeneratorTimestamp(true)
				.logLevel("trace")
				.build().toCompileArgs();

		final var compilation = javac().withOptions(options)
				.withProcessors(new WebClientProcessor())
				.compile(JavaFileObjects.forResource("templates/error/InvalidReturnForFluxResponseEntityInterface.java"));

		assertThat(compilation).failed();
		assertThat(compilation).hadErrorContaining("Webclient can only produce a Mono of ResponseEntity");

	}

	@Test
	void whenIsAbstractClass() {

		final var options = Options.builder()
				.suppressGeneratorComment(true)
				.suppressGeneratorTimestamp(true)
				.logLevel("trace")
				.build()
				.toCompileArgs();

		final var compilation = javac().withOptions(options)
				.withProcessors(new WebClientProcessor())
				.compile(JavaFileObjects.forResource("templates/error/InvalidWhenItIsAClass.java"));

		assertThat(compilation).succeeded();
		assertThat(compilation).hadWarningContaining(
				"This element InvalidWhenItIsAClass can not be processed, because it is not an interface");

	}

}