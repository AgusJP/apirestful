package com.school.apirestful.controllers;

import com.school.apirestful.exceptions.RecordNotFoundException;
import com.school.apirestful.models.teacher;
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
    public ResponseEntity<List<teacher>> getAllTeachers() {
        List<teacher> list = service.getAllTeachers();

        return new ResponseEntity<List<teacher>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<teacher> getTeacherById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        teacher entity = service.getTeacherById(id);

        return new ResponseEntity<teacher>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<teacher>> getTeachersByTitle(@PathVariable("name") String name) {
        List<teacher> list = service.getTeacherByName(name);

        return new ResponseEntity<List<teacher>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<teacher> createTeacher(@Valid @RequestBody teacher myteacher){
        teacher created = service.createTeacher(myteacher);
        return new ResponseEntity<teacher>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<teacher> UpdateTeacher(@Valid @RequestBody teacher myteacher)
            throws RecordNotFoundException {
        teacher updated = service.createTeacher(myteacher);
        return new ResponseEntity<teacher>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteTeacherById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteTeacherById(id);
        return HttpStatus.ACCEPTED;
    }
}
