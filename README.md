# Reach Spring

Standard configuration and common functionality for Spring Boot based Reach modules via a single library.

## reach-spring-parent

Parent POM extending `spring-boot-starter-parent`.

Standard configuration for maven plugins including:

* Checkstyle
* Sonarqube
* OWASP dependency scanning (within profile 'dependencycheck')
* Application Insights Agent JAR

## reach-spring-support

Provides Spring auto-configuration for common functionality shared across Reach modules including:

* Security via JWT authentication
* Logging
* Distributed tracing

Includes standard imports for

* spring-boot-starter-web (with Jetty)
* reach-health
* Application Insights

## Usage

```xml

<parent>
  <groupId>uk.gov.defra.reach.spring</groupId>
  <artifactId>reach-spring-parent</artifactId>
  <version>{version}</version>
  <relativePath/>
</parent>
```

## Overridable properties

| Property    | Default     | Description |
| ----------- | ----------- | ----------- |
| `reach.web.security.unsecured-endpoints` | `/,/healthcheck` | Comma separated list of endpoints which should not be secured via the JWT authentication. |

## Environment variables

The following environments variables will need to be configured for any application using this library

```
JWT_SECRET_KEY
APPLICATION_INSIGHTS_IKEY
```
