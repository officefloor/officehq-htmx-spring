# Working in this app

Implement the change request you have been given. A single change may span the
database schema, the server, and the front end.

- **`data-testid` is an immutable public API.** Expose a stable `data-testid` on every
  element and value a feature surfaces, and **never rename or remove a `data-testid`
  that already exists** — it is how the app is tested. Match exactly the `data-testid`
  values your task's test expects.
- **Run `bin/e2e`** to build the app, start it, run your test, and stop — use it to
  check your work. The `bin/` scripts and these two instruction files are fixed; do
  not edit them.

---

*htmx's official documentation is the reference for the front end: <https://htmx.org/docs/>.
htmx itself is vendored under `src/main/resources/static` and served from the app (no CDN).*

---

## Spring

This is a standard Spring Boot application, built with conventional Spring MVC — here
`@Controller` request handling that renders server-side views, plus a service layer, Spring Data /
JPA persistence, and Bean Validation. Follow ordinary Spring conventions and idioms throughout.
Spring's reference documentation is the guidance for the server: Spring Boot
<https://docs.spring.io/spring-boot/4.1/reference/> and Spring Framework
<https://docs.spring.io/spring-framework/reference/>.
