package com.school.apirestful.controllers;


import com.school.apirestful.exceptions.RecordNotFoundException;
import com.school.apirestful.models.Book;
import com.school.apirestful.models.Student;
import com.school.apirestful.services.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/book")
public class bookController {

    @Autowired
    bookService service;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> list = service.getAllBooks();

        return new ResponseEntity<List<Book>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Book entity = service.getBookById(id);

        return new ResponseEntity<Book>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book myBook){
        Book created = service.createBook(myBook);
        return new ResponseEntity<Book>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Book> UpdateBook(@Valid @RequestBody Book myBook)
            throws RecordNotFoundException {
        Book updated = service.UpdateBook(myBook);
        return new ResponseEntity<Book>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteBookById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteBookById(id);
        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Book>> getStudentsByName(@PathVariable("name") String name) {
        List<Book> list = service.getBookByName(name);

        return new ResponseEntity<List<Book>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Set<Book>> getBooksOfStudent(@PathVariable("id") Long id){
        Set<Book> list = service.getBooksOfStudent(id);

        return new ResponseEntity<Set<Book>>(list, new HttpHeaders(), HttpStatus.OK);
    }

}
