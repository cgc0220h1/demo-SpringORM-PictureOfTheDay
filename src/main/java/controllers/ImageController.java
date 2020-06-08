package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.coment.CommentService;
import service.picture.PictureService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class ImageController {
    private CommentService commentService;

    private PictureService pictureService;

    public ImageController(CommentService commentService, PictureService pictureService) {
        this.commentService = commentService;
        this.pictureService = pictureService;
    }

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
