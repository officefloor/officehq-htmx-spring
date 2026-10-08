package net.officefloor.hq.app.web;

import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Every {@link NavEntry} bean in the application, in display order.
 *
 * <p>Spring injects the whole collection, so discovery needs no list and no configuration. The
 * layout template reads it as {@code ${@navRegistry.entries()}} — a bean reference resolved at
 * render time. A nav link is a {@code NavEntry} {@code @Component}.
 */
@Component
public class NavRegistry {

    private final List<NavEntry> entries;

    public NavRegistry(List<NavEntry> entries) {
        this.entries = entries.stream()
                .sorted(Comparator.comparingInt(NavEntry::order).thenComparing(NavEntry::section))
                .toList();
    }

    public List<NavEntry> entries() {
        return this.entries;
    }
}
