package com.example.assign3vladimir.service;

import com.example.assign3vladimir.model.Course;
import com.example.assign3vladimir.model.Student;
import com.example.assign3vladimir.repository.CourseRepository;
import com.example.assign3vladimir.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    public Student login(String email, String password) {
        return studentRepo.findByEmailAndPasswordAndDeletedFalse(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));
    }

    public Student updateProfile(Long id, Student newData) {
        Student student = studentRepo.findById(id).orElseThrow();
        student.setName(newData.getName());
        student.setEmail(newData.getEmail());
        student.setAddress(newData.getAddress());
        student.setModifiedDate(LocalDateTime.now());
        log.info("Updated profile for student: {}", student.getEmail());
        return studentRepo.save(student);
    }

    public List<Course> viewCourses() {
        return courseRepo.findAll();
    }

    public Student enrollCourse(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        Course course = courseRepo.findById(courseId).orElseThrow();
        student.getCourses().add(course);
        student.setModifiedDate(LocalDateTime.now());
        log.info("Student {} enrolled in course {}", student.getEmail(), course.getTitle());
        return studentRepo.save(student);
    }

    public Student dropCourse(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        Course course = courseRepo.findById(courseId).orElseThrow();
        student.getCourses().remove(course);
        student.setModifiedDate(LocalDateTime.now());
        log.info("Student {} dropped course {}", student.getEmail(), course.getTitle());
        return studentRepo.save(student);
    }
}