package net.officefloor.hq.app;

import net.officefloor.spring.starter.rest.view.ViewResponse;
import org.springframework.ui.Model;

/**
 * The base home page, and the worked example of how every page in this app is rendered: a
 * procedure takes whatever it needs injected plus Spring's {@link Model} and OfficeFloor's
 * {@link ViewResponse}, puts its data on the model, and sends a template name. There is no JSON,
 * no client-side model of the domain, and nothing to keep in step with one.
 *
 * <p>Shape verified against the OfficeFloor tutorial SpringRestThymeleafHttpServer.
 */
public class HomeView {

    public void service(Model model, ViewResponse response) {
        response.send("home");
    }
}
