# Tutorial: Easy Session Sharing in Spring Boot with Spring Session

This repository contains all the code for testing Spring Session in a multi-node Spring Boot application with Okta authentication and HAProxy load balancer.

Please read [Easy Session Sharing in Spring Boot with Spring Session and MySQL](https://developer.okta.com/blog/2020/10/02/spring-session-mysql) to read about how this app was created.

* [Getting Started](#getting-started)
* [Links](#links)
* [Help](#help)
* [License](#license)

**Prerequisites:**
- [Java 8+](https://adoptopenjdk.net/)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)


## Getting Started

To install this example, run the following commands:

```bash
git clone https://github.com/oktadeveloper/okta-spring-session-mysql-example.git
```

## Create the OIDC Application in Okta

Log in to your Okta Developer account (or [sign up](https://developer.okta.com/signup/) if you don’t have an account).
Setup the client application:

From the **Applications** page, choose **Add Application**. On the Create New Application page, select **Web**. Set the following values:
- Name: webapp
- Base URIs: `http://localhost/`
- Login redirect URIs: `http://localhost/login/oauth2/code/okta`
- Logout redirect URIs: `http://localhost`
- Grant type allowed: Authorization Code

Copy the **Client ID** and **Client Secret**. Go to the Dashboard home and copy the **Org URL** from the top right corner.

## Run with Docker Compose

Build the `webapp` container image with the following maven command:

```shell
./mvnw compile jib:dockerBuild
```

Create the file `docker/.env` with the following content:

```shell
OKTA_OAUTH2_ISSUER={yourOrgUrl}/oauth2/default
OKTA_OAUTH2_CLIENT_ID={clientId}
OKTA_OAUTH2_CLIENT_SECRET={clientSecret}
```

Start all the services with docker-compose:

```shell
cd docker
docker-compose up
```

HAProxy will be ready after you see the following lines in the logs:

```
haproxy_1  | [WARNING] 253/130140 (6) : Server servers/webapp2 is UP, reason: Layer7 check passed, code: 302, check duration: 5ms. 1 active and 0 backup servers online. 0 sessions requeued, 0 total in queue.
haproxy_1  | [WARNING] 253/130141 (6) : Server servers/webapp3 is UP, reason: Layer7 check passed, code: 302, check duration: 4ms. 2 active and 0 backup servers online. 0 sessions requeued, 0 total in queue.
haproxy_1  | [WARNING] 253/130143 (6) : Server servers/webapp1 is UP, reason: Layer7 check passed, code: 302, check duration: 7ms. 3 active and 0 backup servers online. 0 sessions requeued, 0 total in queue.
```

Got to http://localhost/greeting and login with Okta.

## Links

This example uses the following open source libraries:

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Session](https://spring.io/projects/spring-session)
* [Okta Spring Boot Starter](https://github.com/okta/okta-spring-boot)

## Help

Please post any questions as issues in this forum, as questions on the [blog post](https://developer.okta.com/blog/2020/10/02/spring-session-mysql), or on [Stack Overflow](https://www.stackoverflow.com) with an "okta" tag.

## License

Apache 2.0, see [LICENSE](LICENSE).
