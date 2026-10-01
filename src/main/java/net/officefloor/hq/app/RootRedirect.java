package net.officefloor.hq.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Sends {@code /} to the home page. Base infrastructure, like {@code TestSupportController} — the
 * app's own pages are OfficeFloor routes; this exists only so the bare root resolves to one.
 *
 * <p>There is no SPA fallback in this stack (the React arms' {@code SpaConfig}): every URL is a
 * real server route, so a deep link and a refresh work without one.
 */
@Controller
public class RootRedirect {

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }
}
