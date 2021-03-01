package com.school.apirestful.controllers;

import com.school.apirestful.exceptions.RecordNotFoundException;
import com.school.apirestful.models.Student;
import com.school.apirestful.services.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class studentController {

    @Autowired
    studentService service;

    @GetMapping
    public ResponseEntity<List<Student>> getAllstudents() {
        List<Student> list = service.getAllStudents();

        return new ResponseEntity<List<Student>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Student entity = service.getStudentById(id);

        return new ResponseEntity<Student>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable("name") String name) {
        List<Student> list = service.getStudentByName(name);

        return new ResponseEntity<List<Student>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student mystudent){
        Student created = service.createStudent(mystudent);
        return new ResponseEntity<Student>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Student> Updatestudent(@Valid @RequestBody Student mystudent)
            throws RecordNotFoundException {
        Student updated = service.UpdateStudent(mystudent);
        return new ResponseEntity<Student>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteStudentById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteStudentById(id);
        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/teacher/{id}/{page}")
    public ResponseEntity<List<Student>> getStudentsByTeacherId(@PathVariable("id") Long id,
                                                                @PathVariable("page") int page ){
        List<Student> list = service.getStudentByTeacherId(id, page);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/add/{id_teacher}/{id_student}")
    public HttpStatus addStudentToTeacher(@PathVariable("id_teacher") Long id_teacher,
                                    @PathVariable("id_student") Long id_student) {

       Long id= service.addStudentToTeacher(id_teacher,id_student);

        if (id == id_teacher){
            return HttpStatus.ACCEPTED;
        } else {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PutMapping("/addBook/{id_student}/{id_book}")
    public HttpStatus addBookToStudent(@PathVariable("id_student") Long id_student,
                                       @PathVariable("id_book") Long id_book){

        service.addBookToStudent(id_student,id_book);
        return HttpStatus.ACCEPTED;
    }

    @PutMapping("/removeBook/{id_student}/{id_book}")
    public HttpStatus removeBookToStudent(@PathVariable("id_student") Long id_student,
                                       @PathVariable("id_book") Long id_book){

        service.removeBookToStudent(id_student,id_book);

        return HttpStatus.ACCEPTED;
    }
}
