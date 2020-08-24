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

package io.github.raphasil.generator.client.rest.resttemplate.spring;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.github.raphasil.generator.client.rest.api.provider.ClientProvider;

@Configuration
@EnableConfigurationProperties(RestTemplateGeneratorProperties.class)
public class RestTemplateGeneratorAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	ClientProvider<RestTemplate> webClientProvider(final RestTemplateBuilder restTemplateBuilder,
			final RestTemplateGeneratorProperties properties) {
		return new RestTemplateProvider(restTemplateBuilder, properties.getClients(), new ConcurrentHashMap<>());
	}

}
