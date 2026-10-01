package net.officefloor.hq.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The base home page, and the worked example of how every page in this app is rendered: a
 * {@code @Controller} method puts its data on the {@link Model} and returns the name of a
 * Thymeleaf template under {@code src/main/resources/templates}. There is no JSON and no
 * client-side model of the domain.
 *
 * <p>This is the plain-Spring counterpart of the OfficeFloor arm's {@code HomeView}, which took an
 * {@code ObjectResponse}/{@code ViewResponse} and was wired by its own YAML file. The difference is
 * the point of this arm: Spring lets handlers accumulate as METHODS on a controller, where
 * OfficeFloor requires one wired file per URL.
 */
@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }
}
