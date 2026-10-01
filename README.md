# officehq-htmx-spring — base repository (server-rendered HTML + htmx + plain Spring Boot)

A **base repository** for the `ui-long-degradation-test` harness — **one technology stack**: the
UI is **server-rendered Thymeleaf HTML driven by htmx**, backend **plain Spring Boot** on in-memory
H2. No OfficeFloor: pages and fragments are `@Controller` methods returning a template name.

This arm exists to **falsify or confirm the inheritance claim**. The `~/officehq-htmx-officefloor`
arm's front-end additivity was argued to be *inherited* from its additive backend rather than
engineered into the client. Against that arm only the backend differs — the templates and the
vendored htmx are byte-identical, because this repo was cloned from it:

* if `htmx-officefloor` holds its structure and this one does not, **inheritance is demonstrated**;
* if **both** hold, hypermedia is additive on its own — the most practically useful result in the
  set, since the pattern would then work without adopting OfficeFloor.

The structural difference is exactly this: OfficeFloor requires one wired file per URL, while
Spring MVC lets handlers accumulate as *methods on a controller*. Whether the agent takes that
option is the measurement.

This arm tests the strongest form of the thesis: rather than making the *client* additive, it
removes the client. There is no JavaScript application, no build step and no Node toolchain — an
OfficeFloor procedure renders a Thymeleaf template, and htmx (50 KB, vendored, never from a CDN)
lets any element issue any HTTP request and swap the returned HTML into any part of the page. So
the front end has no state container, no router table, no API client and no second copy of the
domain model, and its additivity is *inherited* from the backend.

| what is added         | the files that are added                                      | what is edited |
| --------------------- | ------------------------------------------------------------- | -------------- |
| a page                | `officefloor/rest/<path>.GET.yml` + a `...View` class + `templates/<name>.html` | nothing |
| its nav link          | a `NavEntry` `@Component`                                     | nothing (Spring collects the beans) |
| a partial update      | another route + class + `templates/fragments/<name>.html`, reached by `hx-get`/`hx-target` | nothing |
| a filter / sort / tab | a `@RequestParam` on the page's procedure                     | nothing (state is the URL) |

Shape verified against the OfficeFloor tutorial **SpringRestThymeleafHttpServer**. See `CLAUDE.md`
for the five rules the agent works to.

**Measurement caveat:** Lizard cannot parse HTML, so every parser-derived front-end column
(erosion, impact, WMC) is blank for this arm by construction — see `stack.yaml`, which says so at
length. The git-derived measures (`hot_surface`, `reedit_line_stats`, `dup_*`, `cumulative_impact`,
`class_shape`) are what make it comparable with the React arms. It is the
near-empty starting point (base shell + Spring/OfficeFloor + empty H2, no tables) that the harness
**evolves** into a full application over ~60 English change requests, one full-stack change per
checkpoint.

- Base repos are **home-level sibling directories**, one per stack, named
  `~/officehq-<frontend>-<backend>` so both layers are visible (`~/officehq-react-officefloor`,
  `~/officehq-<frontend>-<backend>`, …) — the **front-end and the backend may both vary** between
  stacks. The study compares stacks by running the harness against each in turn — which stack best
  resists erosion.
- The harness (`~/ui-long-degradation-test`, `config.yaml → app.repo`) reads this folder at branch
  **`base-empty`**, worktrees it onto `evolve/<run_id>/<condition>/chain<n>`, and commits each
  checkpoint there. This branch is only ever read.
- It honours the **App contract** — see `~/ui-long-degradation-test/docs/SUT_CONTRACT.md`.
- **Try another stack:** create a new sibling `~/officehq-<frontend>-<backend>` (different
  front-end, different backend, or both), satisfy the same `BASE_CHECKLIST.md`, and point
  `app.repo` at it. Each is its own run.

**Status: green.** Verified through `bin/e2e` against the real jar: pages are real URLs rendered by
Spring MVC, the nav is built from the bean registry, a deep link survives a refresh, and the
vendored htmx is served from the jar. The packaged jar contains **no OfficeFloor libraries**, and
every template is byte-identical to `~/officehq-htmx-officefloor`.
