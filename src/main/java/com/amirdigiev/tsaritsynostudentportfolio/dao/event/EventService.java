package com.amirdigiev.tsaritsynostudentportfolio.dao.event;

import com.amirdigiev.tsaritsynostudentportfolio.dao.student.StudentService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.Event;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.Student;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserService userService;
    private final StudentService studentService;

    @Autowired
    public EventService(EventRepository eventRepository,
                        UserService userService,
                        StudentService studentService)
    {
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.studentService = studentService;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null);
    }

    public void add(Event event) {
        if (event == null) throw new NullPointerException();
        eventRepository.save(event);
    }

    public void deleteById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            eventRepository.deleteById(id);
        } else {
            System.out.println("Мероприятия с " + id + " не существует");
        }
    }

    public void participate(Long id) {
        User currentUser = userService.getAnAuthorizedUser();
        if (currentUser.getRole().equals("STUDENT")) {
            Student student = findStudentByUserId();

            Event event = findById(id);
            if (event != null) {
                event.getStudents().add(student);
                student.getEvents().add(event);
                eventRepository.save(event);
                studentService.update(student);
            }
        } else {
            throw new IllegalArgumentException("Пользователь не является студентом");
        }
    }

    private Student findStudentByUserId() {
        List<Student> students = studentService.findAll();
        User currentUser = userService.getAnAuthorizedUser();

        return students.stream()
                .filter(student -> student.getUser().getId().equals(currentUser.getId()))
                .findFirst()
                .get();
    }
}
