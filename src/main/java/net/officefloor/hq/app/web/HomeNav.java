package net.officefloor.hq.app.web;

import org.springframework.stereotype.Component;

/**
 * WORKED EXAMPLE of the one mechanism every page uses to appear in the nav bar. This class is the
 * whole of Home's presence there; the layout was not touched to put it in. A new page adds its own
 * file exactly like this one.
 */
@Component
public class HomeNav implements NavEntry {

    @Override
    public String section() {
        return "home";
    }

    @Override
    public String label() {
        return "Home";
    }

    @Override
    public String href() {
        return "/home";
    }

    @Override
    public int order() {
        return 0;
    }
}
