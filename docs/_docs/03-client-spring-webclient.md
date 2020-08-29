---
title: "Spring Web Client"
permalink: /docs/client-spring-webclient/
excerpt: "Spring Web Client."
last_modified_at: 2020-08-29
toc: true
---

WebClient is a reactive client that provides an alternative to the RestTemplate, see the [documentation](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-client "Spring WebClient")

## Using Spring Boot Starter

You can use the starter that will provide an implementation of `ClientProvider` for `WebClient` and some basics configurations.

```yaml
...

generator:
  webclient:
    clients:
      client-one: # client name 
        base-url: http://localhost:8383
        connect-timeout: 1s
        read-timeout: 5s
        write-timeout: 3s
        authentication:
          basic-auth:
            username: name
            password: 123456
        default-headers:
          x-api-key: foo
          User-Agent: my-app
      client-two: # client name 
          base-url: http://localhost:8383
      ...    
```


### Gradle
For Gradle, you need something along the following lines:

```groovy

    dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-webflux' // Spring WebClient
        implementation 'io.github.raphasil.rest-client-generator:webclient-generator-spring-boot-starter:0.0.1'
        annotationProcessor 'io.github.raphasil:webclient-processor:0.0.1'
    }

```

### Maven
For Maven, you need something along the following lines:


```xml
<?xml version="1.0" encoding="UTF-8"?>

<project>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>io.github.raphasil.rest-client-generator</groupId>
            <artifactId>webclient-generator-spring-boot-starter</artifactId>
            <version>0.0.1</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>                    
                    <annotationProcessorPaths>
                        <path>
                            <groupId>io.github.raphasil.rest-client-generator</groupId>
                            <artifactId>webclient-processor</artifactId>
                            <version>0.0.1</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

