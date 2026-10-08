package net.officefloor.hq.app.web;

/**
 * One link in the shell's nav bar.
 *
 * <p>A page contributes its own entry with a {@code @Component} that implements this; Spring
 * collects every bean of the type, so the shell reads the collection rather than a hard-coded list
 * of pages ({@code layout.html} renders whatever is present).
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
