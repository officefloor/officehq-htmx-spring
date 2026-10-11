# Design of this implementation

Stack-specific guidance for this implementation of the app: its stack, layout and conventions.
Read it with `AGENTS.md`, which has the working rules and how the app is tested.

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
