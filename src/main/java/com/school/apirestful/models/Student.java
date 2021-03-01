package com.school.apirestful.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Entity
@JsonIgnoreProperties(value = {"teachers"},allowGetters = false, allowSetters = true)
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "nationality", length = 128)
    private String nationality;

    @Column(name = "description", length = 128)
    private String description;

    @JsonIgnoreProperties({"student","hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "student", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Book> books;

    @JsonIgnoreProperties({"students"})
    @ManyToMany(mappedBy = "students", cascade = CascadeType.PERSIST)
    Set<Teacher> teachers;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book book){
        if (!books.contains(book)){
            books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book){
        if (books.contains(book)){
            books.remove(book);
            book.setStudent(null);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", description='" + description + '\'' +
                ", books=" + books +
                '}';
    }
}
