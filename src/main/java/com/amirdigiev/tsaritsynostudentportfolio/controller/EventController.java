package com.amirdigiev.tsaritsynostudentportfolio.controller;

import com.amirdigiev.tsaritsynostudentportfolio.dao.event.EventService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.file.FileService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.student.StudentService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.Event;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.Student;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class EventController {

    private final EventService eventService;
    private final UserService userService;
    private final StudentService studentService;
    private final FileService fileService;

    @Value("${application.event-folder}")
    private String eventFolder;

    @Autowired
    public EventController(EventService eventService,
                           UserService userService,
                           FileService fileService,
                           StudentService studentService)
    {
        this.eventService = eventService;
        this.userService = userService;
        this.fileService = fileService;
        this.studentService = studentService;
    }

    @GetMapping("/events")
    public String getEventPage(Model model) {
        User currentUser = userService.getAnAuthorizedUser();
        List<Event> events = eventService.findAll();

        model.addAttribute("events", events);
        model.addAttribute("role", currentUser.getRole());
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("avatar", currentUser.getAvatar());

        return "events";
    }

    @GetMapping("/add_event")
    public String getAddEventForm(Model model) {
        User currentUser = userService.getAnAuthorizedUser();

        model.addAttribute("event", new Event());
        model.addAttribute("role", currentUser.getRole());
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("avatar", currentUser.getAvatar());

        return "add_event";
    }

    @PostMapping("/add_event")
    public String submitAddEventForm(@ModelAttribute Event event,
                                     @RequestParam("img") MultipartFile img,
                                     BindingResult bindingResult) throws IOException
    {
        if (bindingResult.hasErrors()) {
            return "add_event";
        }

        fileService.uploadImg(img, Paths.get(eventFolder));
        event.setImage(img.getOriginalFilename());

        eventService.add(event);
        log.info("New Event Announced: " + event.getName());

        return "redirect:/events";
    }

    @PostMapping("/participate/{id}")
    public String participate(@PathVariable Long id) {
        eventService.participate(id);
        return "redirect:/events";
    }

    @GetMapping("/involved/{id}")
    public String getInvolved(@PathVariable Long id, Model model)
    {
        User currentUser = userService.getAnAuthorizedUser();
        Event event = eventService.findById(id);

        model.addAttribute("event", event);
        model.addAttribute("students", event.getStudents());
        model.addAttribute("role", currentUser.getRole());
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("avatar", currentUser.getAvatar());

        return "involved";
    }

    @PostMapping("/accrue_rating/{studentId}")
    public String accrueRatingById(@PathVariable Long studentId,
                                   @RequestParam Integer rating)
    {
        studentService.accrueRatingById(studentId, rating);
        return "redirect:/involved/" + event.getId();
    }

    @PostMapping("/involved/{eventId}/delete_student/{studentId}")
    public String deleteStudentFromEvent(@PathVariable Long eventId,
                                         @PathVariable Long studentId)
    {
        Optional<Student> student = studentService.findById(studentId);
        Event event = eventService.findById(eventId);

        if (student.isPresent()) {

        }
        return "redirect:/involved";
    }

    @PostMapping("/delete_event/{id}")
    public String deleteEvent(@PathVariable Long id) {
        Event event = eventService.findById(id);
        if (event != null) {
            eventService.deleteById(id);
        }
        return "redirect:/events";
    }
}
