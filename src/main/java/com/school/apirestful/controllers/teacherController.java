package com.school.apirestful.controllers;

import com.school.apirestful.exceptions.RecordNotFoundException;
import com.school.apirestful.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.school.apirestful.services.teacherService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class teacherController {

    @Autowired
    teacherService service;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> list = service.getAllTeachers();

        return new ResponseEntity<List<Teacher>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Teacher entity = service.getTeacherById(id);

        return new ResponseEntity<Teacher>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Teacher>> getTeachersByTitle(@PathVariable("name") String name) {
        List<Teacher> list = service.getTeacherByName(name);

        return new ResponseEntity<List<Teacher>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@Valid @RequestBody Teacher myteacher){
        Teacher created = service.createTeacher(myteacher);
        return new ResponseEntity<Teacher>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Teacher> UpdateTeacher(@Valid @RequestBody Teacher myteacher)
            throws RecordNotFoundException {
        Teacher updated = service.UpdateTeacher(myteacher);
        return new ResponseEntity<Teacher>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteTeacherById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteTeacherById(id);
        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<Teacher>> getTeachersByStudentId(@PathVariable("id") Long id,
                                                                @PathVariable("page") int page){

        List<Teacher> list = service.getTeacherByStudentId(id, page);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
