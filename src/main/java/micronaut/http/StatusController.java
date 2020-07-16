package micronaut.http;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.logging.Logger;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class StatusController {
    private final RequestValidator requestValidator;

    @Inject
    public StatusController(RequestValidator requestValidator) {
        this.requestValidator = requestValidator;
    }

    @ExecuteOn(TaskExecutors.IO)
    @Get("/status")
    String status() {
        System.out.println(Thread.currentThread().getName());
        return requestValidator.isValid("requestId") ? "valid" : "invalid";
    }
}
