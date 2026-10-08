# OfficeHQ

A full-stack office-management web application.

- **UI:** server-rendered HTML (Thymeleaf templates) with [htmx](https://htmx.org) for partial
  updates; htmx is vendored under `src/main/resources/static`.
- **Back end:** Spring Boot with Spring MVC `@Controller` methods rendering the pages, on an
  in-memory H2 database with Flyway migrations run on boot.

## Build and run

```
bin/build              # package one runnable jar
PORT=3000 bin/start    # start the app on $PORT
bin/stop               # stop it
```

Readiness: `GET /actuator/health`.

## Tests

End-to-end tests use Playwright (in `e2e/`) and bind to `data-testid`:

```
bin/e2e                # build, start, run the specs in e2e/specs, then stop
```
