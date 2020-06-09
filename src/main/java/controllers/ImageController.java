package controllers;

import model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.coment.CommentService;
import service.picture.PictureService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/")
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
    public ModelAndView showHome() {
        Timestamp startTime = Timestamp.valueOf(LocalDate.now().atStartOfDay());
        Timestamp endTime = Timestamp.valueOf(LocalDateTime.now());
        Map<Long, List<Comment>> commentMap = new LinkedHashMap<>();
        commentMap.put(1L, commentService.findAllByPictureAndPostTimeBetween(pictureService.findOne(1L), startTime, endTime));
        commentMap.put(2L, commentService.findAllByPictureAndPostTimeBetween(pictureService.findOne(2L), startTime, endTime));
        commentMap.put(3L, commentService.findAllByPictureAndPostTimeBetween(pictureService.findOne(3L), startTime, endTime));
        commentMap.put(4L, commentService.findAllByPictureAndPostTimeBetween(pictureService.findOne(4L), startTime, endTime));
        commentMap.put(5L, commentService.findAllByPictureAndPostTimeBetween(pictureService.findOne(5L), startTime, endTime));
        commentMap.put(6L, commentService.findAllByPictureAndPostTimeBetween(pictureService.findOne(6L), startTime, endTime));
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("commentMap", commentMap);
        return modelAndView;
    }
}
