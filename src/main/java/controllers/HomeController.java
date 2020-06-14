package controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import util.CommentUtil;

import java.io.IOException;

@Controller
@RequestMapping("/")
@PropertySource(value = "classpath:/exclude-word.properties", ignoreResourceNotFound = true)
public class HomeController {

    @Value("${test.words}")
    private static String testProperty;

    static {
        System.out.println(testProperty);
    }

    @GetMapping()
    public RedirectView redirect() {
        return new RedirectView("/images");
    }
}
