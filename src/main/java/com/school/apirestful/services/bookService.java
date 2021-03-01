package com.school.apirestful.services;

import com.school.apirestful.exceptions.AlreadyExistsException;
import com.school.apirestful.exceptions.RecordNotFoundException;
import com.school.apirestful.models.Book;
import com.school.apirestful.models.Student;
import com.school.apirestful.repositories.bookRepository;
import com.school.apirestful.repositories.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class bookService {

    @Autowired
    bookRepository repository;

    @Autowired
    studentRepository srepository;

    public boolean isExist(Book book) {
        return repository.findById(book.getId()) != null;
    }

    public List<Book> getAllBooks()
    {
        List<Book> BooksList = repository.findAll();

        if(BooksList.size() > 0) {
            return BooksList;
        } else {
            return new ArrayList<Book>();
        }
    }

    public Book getBookById(Long id) throws RecordNotFoundException
    {
        Optional<Book> Book = repository.findById(id);

        if(Book.isPresent()) {
            return Book.get();
        } else {
            throw new RecordNotFoundException("No Book record exist for given id",id);
        }
    }

    public Book createBook(Book entity){
        entity = repository.save(entity);
        return entity;
    }

    public Book UpdateBook(Book entity) throws RecordNotFoundException
    {

        if(entity.getId()!=null)
        {
            Optional<Book> Book = repository.findById(entity.getId());

            if(Book.isPresent())
            {
                Book newEntity = Book.get();
                //newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setSubject(entity.getSubject());
                newEntity.setStudent(entity.getStudent());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Book not found",entity.getId());
            }
        }else{
            throw new RecordNotFoundException("No id of Book given",0l);
        }
    }

    public void deleteBookById(Long id) throws RecordNotFoundException
    {
        Optional<Book> Book = repository.findById(id);

        if(Book.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Book record exist for given id",id);
        }
    }

    public List<Book> getBookByName(String name) {
        List<Book> bookList = repository.getByName(name);

        if(bookList.size() > 0) {
            return bookList;
        } else {
            return new ArrayList<Book>();
        }
    }

    public Set<Book> getBooksOfStudent(Long id) {
        Set<Book> books = repository.getBooksOfStudent(id);

       return books;
    }
    
}

