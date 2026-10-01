# Working in this app

You are making ONE change to this application in response to the change request you were given.
Implement it as a **full-stack change**: whatever the request needs across the database schema, the
server (Spring MVC on a Spring Boot host), and the pages — as a small, additive, local change.

## Rules

- **`data-testid` is immutable public API.** Expose a stable `data-testid` on every element and
  value the feature surfaces. **Never rename or remove a `data-testid` that already exists** — it
  is how the app is tested. Match exactly the `data-testid` values your task's test expects.
- **Schema changes are Flyway migrations.** Add a new versioned migration under
  `src/main/resources/db/migration/`; never edit an applied migration.
- **Data is seeded through the app's own API in tests**, not committed as fixtures. If your feature
  needs new seed capability, extend the `/__test__` seed support. Seed with a `JdbcTemplate` using
  the **explicit ids from the fixture** (JPA `save()` with an IDENTITY id ignores a supplied id and
  generates its own — the spec asserts rows by the fixture's ids, so they must match). `reset`
  should `TRUNCATE ... RESTART IDENTITY` the tables it clears.
- **Audit / side-effect records go through the `Audit` service** (inject `Audit`, call
  `record(...)`). It appends one record per line to the known audit file that tests read — that is
  how audited behaviour is verified (the UI can't show it). Use the exact record text the task's
  test expects; don't invent separate logging for audited behaviour.
- **Do not edit** the build/run scripts (`bin/build`, `bin/start`, `bin/stop`, `bin/e2e`) or this
  file. Use `bin/e2e` to run your test as you work.

## The UI is server-rendered HTML: your change is NEW FILES

There is no JavaScript application and no client-side copy of the domain. The server renders HTML;
[htmx](https://htmx.org) lets any element issue any HTTP request and swap the returned HTML into
any part of the page. Follow these five rules.

1. **A page is a `@GetMapping` handler plus a template.**
   - a `@Controller` method that takes its injected dependencies plus Spring's `Model`, puts data
     on the model and returns the template name (`return "clients";`)
   - `src/main/resources/templates/clients.html`, which starts
     `<html th:replace="~{layout :: page(~{::content})}">` and puts its markup in
     `<main data-testid="app-home" th:fragment="content">`
   Group handlers in a controller per area the way Spring MVC normally does. See
   `HomeController.java` and `templates/home.html` for the worked example.

2. **Its nav link is a fourth new file**: a `@Component` implementing
   `net.officefloor.hq.app.web.NavEntry`, with `section()` giving `data-testid="nav-<section>"`.
   Spring collects every such bean, so the layout never lists the pages. See `web/HomeNav.java`.

3. **An htmx fragment is another handler returning a PARTIAL.** To update part of a page without
   a full reload, put `hx-get`/`hx-post`, `hx-target` and `hx-swap` on the element, point them at
   a handler, and have that handler return a template under `templates/fragments/` which renders
   only the fragment — not a `layout` replacement. The element being replaced keeps its own
   `data-testid`.

4. **State that outlives a click lives in the URL**, because every URL here is a real server
   route. A filter, a sort, a tab, which row is open: read it with `@RequestParam` (or a path
   parameter) and render accordingly. Never hold UI state in JavaScript. A deep link and a browser
   refresh must show the same thing — there is no SPA fallback and none is needed.

5. **Never return JSON for the UI, and never add a client-side model of the domain.** The page IS
   the response. (`/__test__` stays JSON: it is test support, not UI.)

**Pages never reach into each other.** They share exactly two things: a URL, and a Thymeleaf
fragment under `templates/fragments/` when the same markup is genuinely needed twice.

**Do not edit these** (they are the mechanism, complete as-is): `templates/layout.html`,
`web/NavEntry.java`, `web/NavRegistry.java`, `RootRedirect.java`, `static/vendor/**`, `pom.xml`.
ADDING files under `templates/`, `templates/fragments/`, `web/` and `src/main/java/**` is exactly
how you work.

## Layout

- `src/main/resources/templates/**` — the pages (Thymeleaf). `layout.html` is the shell;
  `fragments/**` are partials htmx swaps in.
- `src/main/java/**` — `@Controller` classes whose methods render the pages and fragments, plus
  `@Service`/`@Repository` beans for business logic and data access. **No `/api/` prefix is needed**
  — there is no SPA to get out of the way of. `Application`, `RootRedirect`,
  `TestSupportController`, `Audit` and `web/**` are base infrastructure.
- `src/main/resources/db/migration/**` — Flyway migrations (new `V<n>__*.sql` per schema change).
- `src/main/resources/static/vendor/**` — vendored htmx. Never fetched from a CDN: the build and
  the gate run with no network egress.
- `bin/e2e` — build, start the app, run your test, stop. Run it to check your work.
