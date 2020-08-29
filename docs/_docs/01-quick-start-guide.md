---
title: "Quick-Start Guide"
permalink: /docs/quick-start-guide/
excerpt: "How to quickly install and setup Rest Client Generator for use with your java application."
last_modified_at: 2020-08-29
toc: false
---

Rest Client Generator is a Java annotation processor for the generation of Java rest clients. It saves you from writing the http requests by hand.

To create a definition of your api client, declare an interface like this:

```java
@RestClient(value = "client-one", path = "/api/v1/users")
interface UserService {
    @GET
    List getUsers();

    @GET("{id}")
    List getUser(@Path Long id);
}
```

At compile time RestClientGenerator will generate an implementation of this interface.
The generated implementation uses plain Java method invocations for mapping between source and target objects, i.e. no reflection is involved.

The purpose of the annotations in the code above is the following:
- `@RestClient` Identify this interface to be available as a REST Client
- `@GET` define the http method
- `@Path` replace the `{id}` with the parameter `Long id`

## Clients Implemented

Choose you favorite client to setup the configurations

| Clients             	|
|---------------------	|
| Spring WebClient    	|
| Spring RestTemplate 	|


