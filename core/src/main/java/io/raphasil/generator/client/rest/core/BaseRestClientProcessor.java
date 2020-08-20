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

package io.raphasil.generator.client.rest.core;

import static java.util.stream.Collectors.*;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Generated;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.google.auto.common.MoreTypes;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import io.raphasil.generator.client.rest.api.RestClient;
import io.raphasil.generator.client.rest.core.helper.ElementHelper;
import io.raphasil.generator.client.rest.core.helper.HttpAnnotationHelper;
import io.raphasil.generator.client.rest.core.logger.ProcessorLogger;
import io.raphasil.generator.client.rest.core.model.CodeScope;
import io.raphasil.generator.client.rest.core.model.Options;

/**
 * @author Raphael Nascimento
 */
public abstract class BaseRestClientProcessor extends AbstractProcessor {

	private ProcessorLogger logger;

	private Elements elementUtils;

	private Filer filer;

	private Options options;

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		elementUtils = processingEnv.getElementUtils();
		filer = processingEnv.getFiler();
		options = Options.of(processingEnv.getOptions());
		logger = ProcessorLogger.create(processingEnv, options.getVerbose());
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		return Set.of(RestClient.class.getCanonicalName());
	}

	@Override
	public Set<String> getSupportedOptions() {
		return Options.supportedOptions();
	}

	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {

		annotations.parallelStream()
				.flatMap(annotation -> roundEnv.getElementsAnnotatedWith(annotation).stream())
				.filter(this::isInterface)
				.filter(TypeElement.class::isInstance)
				.map(TypeElement.class::cast)
				.forEach(this::processTypeElement);

		return true;
	}

	private boolean isInterface(Element el) {
		final var isInterface = el.getKind() == ElementKind.INTERFACE;

		if (!isInterface) {
			logger.warn("This element %s can not be processed, because it is not an interface", el);
		}

		return isInterface;
	}

	private void processTypeElement(final TypeElement typeElement) {
		try {
			final var javaFile = generateCode(typeElement);
			logger.trace("file generated for %s \n %s", typeElement, javaFile);
			javaFile.writeTo(filer);
		} catch (Exception e) {
			logger.error("An error happening while generate the code for %s, %s \n%s", typeElement, e.getMessage(), e);
		}
	}

	private JavaFile generateCode(final TypeElement interfaceAnnotated) {

		logger.trace("Generating code for interface %s", interfaceAnnotated);

		final var restClient = interfaceAnnotated.getAnnotation(RestClient.class);

		final var methods = generateMethods(interfaceAnnotated);

		final var clazz = TypeSpec.classBuilder("Generated" + ElementHelper.getSimpleName(interfaceAnnotated))
				.addModifiers(Modifier.PUBLIC)
				.addFields(buildFields())
				.addSuperinterface(interfaceAnnotated.asType())
				.addAnnotations(buildAnnotations(interfaceAnnotated))
				.addAnnotation(buildAnnotationGenerated())
				.addMethod(buildConstructor(restClient))
				.addMethods(methods)
				.build();

		return JavaFile.builder(getPackageName(interfaceAnnotated), clazz).build();
	}

	private List<AnnotationSpec> buildAnnotations(final TypeElement interfaceAnnotated) {

		return interfaceAnnotated.getAnnotationMirrors()
				.stream()
				.filter(a -> MoreTypes.isTypeOf(RestClient.class, a.getAnnotationType()))
				.flatMap(a -> a.getElementValues().entrySet().stream())
				.filter(entry -> entry.getKey().getSimpleName().toString().equalsIgnoreCase("annotations"))
				.map(entry -> entry.getValue().getValue())
				.filter(List.class::isInstance)
				.map(List.class::cast)
				.map(list -> (List<Object>) list)
				.flatMap(List::stream)
				.map(Object::toString)
				.filter(str -> str.endsWith(".class"))
				.map(str -> str.substring(0, str.lastIndexOf(".class")))
				.map(str -> AnnotationSpec.builder(ClassName.bestGuess(str)).build())
				.collect(Collectors.toList());
	}

	private List<MethodSpec> generateMethods(final TypeElement interfaceAnnotated) {

		final var restClient = interfaceAnnotated.getAnnotation(RestClient.class);
		final var basePath = restClient.path();
		final var baseHeaders = ElementHelper.getHeaders(interfaceAnnotated);

		return elementUtils.getAllMembers(interfaceAnnotated)
				.stream()
				.filter(el -> el.getKind() == ElementKind.METHOD)
				.filter(ExecutableElement.class::isInstance)
				.map(ExecutableElement.class::cast)
				.filter(HttpAnnotationHelper::isMethodAnnotedWithHttp)
				.map(methodAnnoted -> generateMethod(interfaceAnnotated, basePath, baseHeaders, methodAnnoted))
				.collect(toList());
	}

	private MethodSpec generateMethod(final TypeElement interfaceAnnotated, final String basePath, final Map<String, String> baseHeaders,
			final ExecutableElement methodAnnoted) {
		logger.trace("Generating code for method %s#%s", interfaceAnnotated, methodAnnoted);
		final var httpAnnotation = HttpAnnotationHelper.findFirstHttpAnnotation(methodAnnoted);
		final var path = HttpAnnotationHelper.getPath(httpAnnotation);
		final var httpMethod = httpAnnotation.annotationType().getSimpleName();
		final var headers = Stream.of(baseHeaders, ElementHelper.getHeaders(methodAnnoted))
				.map(Map::entrySet)
				.flatMap(Collection::stream)
				.collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
		final var returnType = methodAnnoted.getReturnType();

		final var codeScopeBuilder = CodeScope.builder()
				.interfaceAnnotated(interfaceAnnotated)
				.methodAnnoted(methodAnnoted)
				.basePath(basePath)
				.path(path)
				.httpMethod(httpMethod)
				.headers(headers)
				.returnType(returnType);

		final var params = generateParameters(methodAnnoted, codeScopeBuilder);

		return MethodSpec.methodBuilder(ElementHelper.getSimpleName(methodAnnoted))
				.addModifiers(Modifier.PUBLIC)
				.returns(TypeName.get(returnType))
				.addParameters(params)
				.addAnnotation(Override.class)
				.addStatement(buildCode(codeScopeBuilder.build()))
				.build();
	}

	private List<ParameterSpec> generateParameters(final ExecutableElement methodAnnoted, final CodeScope.Builder builder) {
		return methodAnnoted.getParameters().stream().map(param -> {
			builder.param(param);
			return generateParameter(param);
		}).collect(toList());
	}

	private ParameterSpec generateParameter(final VariableElement param) {
		final var name = ElementHelper.getSimpleName(param);
		return ParameterSpec.builder(TypeName.get(param.asType()), name, Modifier.FINAL).build();
	}

	protected abstract List<FieldSpec> buildFields();

	protected abstract MethodSpec buildConstructor(final RestClient restClient);

	protected abstract CodeBlock buildCode(final CodeScope scope);

	private String getPackageName(final TypeElement typeElement) {
		return ((PackageElement) typeElement.getEnclosingElement()).getQualifiedName().toString();
	}

	protected TypeMirror getTypeElement(final Class<?> clazz) {
		return elementUtils.getTypeElement(clazz.getCanonicalName()).asType();
	}

	private AnnotationSpec buildAnnotationGenerated() {
		final var builder = AnnotationSpec.builder(Generated.class).addMember("value", "$S", this.getClass().getCanonicalName());

		if (!options.isSuppressGeneratorTimestamp()) {
			builder.addMember("date", "$S", Instant.now());
		}

		if (!options.isSuppressGeneratorComment()) {
			builder.addMember("comments", "$S", "version: todo, compiler: todo, environment: todo");
		}

		return builder.build();
	}

}
