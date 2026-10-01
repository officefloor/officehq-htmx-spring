package net.officefloor.hq.app.web;

/**
 * One link in the shell's nav bar.
 *
 * <p>A page contributes its own entry by adding a {@code @Component} that implements this — Spring
 * collects every bean of the type, so the shell never holds a list of pages and
 * {@code layout.html} is not edited when one is added. This is the server-side equivalent of a
 * slot registry: the mechanism, written once, and a new page is a new file.
 */
public interface NavEntry {

    /** Section id; the link carries {@code data-testid="nav-<section>"}. */
    String section();

    /** Link text. */
    String label();

    /** URL the link goes to. */
    String href();

    /** Lower sorts first; ties break on section so the order is always stable. */
    default int order() {
        return 0;
    }
}
