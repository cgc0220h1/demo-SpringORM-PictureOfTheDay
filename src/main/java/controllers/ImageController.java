package controllers;

import model.Comment;
import model.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import service.coment.CommentService;
import service.picture.PictureService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/images")
public class ImageController {
    private final CommentService commentService;

    private final PictureService pictureService;

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
    public RedirectView redirect() {
        return new RedirectView("/images/1");
    }

    @GetMapping("/{id}")
    public ModelAndView showPage(@PathVariable("id") int id) {
        Pageable pageable = PageRequest.of(id - 1, 1);
        return showHome(pageable);
    }

    public ModelAndView showHome(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Timestamp startTime = Timestamp.valueOf(LocalDate.now().atStartOfDay());
        Timestamp endTime = Timestamp.valueOf(LocalDateTime.now());
        Page<Picture> picturePage = pictureService.findAll(pageable);
        Picture picture = picturePage.getContent().get(0);
        List<Comment> commentList = commentService.findAllByPictureAndPostTimeBetween(picture, startTime, endTime);
        modelAndView.addObject("picture", picture);
        modelAndView.addObject("picturePage", picturePage);
        modelAndView.addObject("commentList", commentList);
        modelAndView.addObject("newComment", new Comment());
        return modelAndView;
    }
}
