package com.school.apirestful.repositories;

import com.school.apirestful.models.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface bookRepository
        extends JpaRepository<Book, Long> {


    @Query(value = "SELECT * FROM book AS b WHERE b.name LIKE ?1%", nativeQuery = true)
    List<Book> getByName(String name);

    @Query(value = "SELECT * FROM book AS b WHERE b.student_id = ?1", nativeQuery = true)
    Set<Book> getBooksOfStudent(Long id);

}
