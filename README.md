# RestClientGenerator
[![License](https://img.shields.io/badge/License-Apache%202.0-yellowgreen.svg)](https://github.com/raphasil/rest-client-generator/blob/master/LICENSE.txt)

[![Build Status](https://github.com/raphasil/rest-client-generator/workflows/CI/badge.svg?branch=master)](https://github.com/raphasil/rest-client-generator/actions?query=branch%3Amaster+workflow%3ACI)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=raphasil_rest-client-generator&metric=coverage)](https://sonarcloud.io/dashboard?id=raphasil_rest-client-generator)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=raphasil_rest-client-generator&metric=alert_status)](https://sonarcloud.io/dashboard?id=raphasil_rest-client-generator)
[![Code Quality: Java](https://img.shields.io/lgtm/grade/java/g/raphasil/rest-client-generator.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/raphasil/rest-client-generator/context:java)
[![Total Alerts](https://img.shields.io/lgtm/alerts/g/raphasil/rest-client-generator.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/raphasil/rest-client-generator/alerts)

- [RestClientGenerator](#restclientgenerator)
  * [Introduction](#introduction)
  * [Getting Started](#getting-started)
    + [Spring Webclient](#spring-webclient)
    + [Spring RestTemplate](#spring-resttemplate)
    + [OKHttp](#okhttp)
  * [API Declaration](#api-declaration)
    + [Request Method](#request-method)
    + [URL Manipulation](#url-manipulation)
    + [Request Body](#request-body)
    + [Header Manipulation](#header-manipulation)

## Introduction

RestClientGenerator is a Java annotation processor for the implementation of rest calls

## Getting Started

We have some annotation's processor implemented to these clients:

### Spring Webclient
Take a look in this documentation.

### Spring RestTemplate
Not Implemented yet.

### OKHttp
Not Implemented yet.

## API Declaration
Annotations on the interface methods and its parameters indicate how a request will be handled.

### Request Method
Every method must have an HTTP annotation that provides the request method and relative path. 
There are eight built-in annotations: HTTP, GET, POST, PUT, PATCH, DELETE, OPTIONS and HEAD. 
The relative path of the resource can be defined in the annotation.

```java
@RestClient("client-one")
interface UserService {
    @GET("api/v1/users")
    List getUsers();
}
```

### URL Manipulation
A request URL can be updated dynamically using replacement blocks and parameters on the method. 
A replacement block is an alphanumeric string surrounded by `{` and `}`. 
A corresponding parameter must be annotated with @Path using the same string or variable name.

```java
@RestClient(value = "client-one", path = "api/v1/users")
interface UserService {
    
    @GET("{id}")
    User getUserById(@Path UUID id);
    
    @GET("api/v1/users/{id}/profiles/{profile-id}")
    User getUserProfile(@Path UUID id, @Path("profile-id") String profileId);

}
```

A query parameter can also be added:

```java
@RestClient("client-one")
interface UserService {
    
    // result: api/v1/users?sort=""&nickname=""
    @GET("api/v1/users")
    List<User> getAll(@Query String sort, @Query("nickname") String name);

}
```

### Request Body
An object can be specified for use as an HTTP request body with the `@Body` annotation.

```java
@RestClient("client-one")
interface UserService {
        
    @POST("api/v1/users")
    User create(@Body User user);

}
```

### Header Manipulation
You can set static headers for a method using the `@Headers` annotation.
```java
@RestClient("client-one")
interface UserService {
    
    @Headers("x-ping: pong")    
    @POST("api/v1/users")
    User create(@Body User user);
    
    @Headers({
                 "Accept: application/txt",
                 "x-api-key: secret"
             })    
    @PUT("api/v1/users")
    User create(@Body User user);
    
    @GET("api/v1/users")
    User getUser(@Header("Authorization") String admin);

}
```


