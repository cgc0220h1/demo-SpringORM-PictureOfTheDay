package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class ImageController {

    @ModelAttribute("timeNow")
    public String timeNow() {
        Locale locale = new Locale("vi", "VN");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);
        return LocalDate.now().format(formatter);
    }

    @GetMapping
    public ModelAndView showHome() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
