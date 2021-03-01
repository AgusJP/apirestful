package com.school.apirestful.services;

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

@Service
public class studentService {

    @Autowired
    studentRepository repository;
    @Autowired
    bookRepository brepository;

    public List<Student> getAllStudents()
    {
        List<Student> studentsList = repository.findAll();

        if(studentsList.size() > 0) {
            return studentsList;
        } else {
            return new ArrayList<Student>();
        }
    }

    public Student getStudentById(Long id) throws RecordNotFoundException
    {
        Optional<Student> student = repository.findById(id);

        if(student.isPresent()) {
            return student.get();
        } else {
            throw new RecordNotFoundException("No student record exist for given id",id);
        }
    }

    public Student createStudent(Student entity){
        entity = repository.save(entity);
        return entity;
    }

    public Student UpdateStudent(Student entity) throws RecordNotFoundException
    {

        if(entity.getId()!=null)
        {
            Optional<Student> student = repository.findById(entity.getId());

            if(student.isPresent())
            {
                Student newEntity = student.get();
                //newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setNationality(entity.getNationality());
                newEntity.setDescription(entity.getDescription());
                if (entity.getTeachers()!=null) {
                    newEntity.setTeachers(entity.getTeachers());
                }
                if (entity.getBooks()!=null) {
                    newEntity.setBooks(entity.getBooks());
                }
                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("student not found",entity.getId());
            }
        }else{
            throw new RecordNotFoundException("No id of student given",0l);
        }
    }

    public void deleteStudentById(Long id) throws RecordNotFoundException
    {
        Optional<Student> student = repository.findById(id);

        if(student.isPresent())
        {
            if (student.get().getBooks()!=null && student.get().getBooks().size()>0){
                throw new RecordNotFoundException("Delete book first", id);
            }else{
                repository.deleteById(id);
            }

        } else {
            throw new RecordNotFoundException("No student record exist for given id",id);
        }
    }


    public List<Student> getStudentByName(String name) {
        List<Student> studentList = repository.getByName(name);

        if(studentList.size() > 0) {
            return studentList;
        } else {
            return new ArrayList<Student>();
        }
    }

    public List<Student> getStudentByTeacherId(Long id, int page) {
        int limit = 3;

        int offset = (limit*page)-limit;

        List<Student> students = repository.getStudentByTeacherId(id, limit, offset);

        if(students.size() > 0){
            return students;
       }else{
            return new ArrayList<>();
        }
    }

    public Long addStudentToTeacher(Long id_teacher, Long id_student) {
        repository.addStudentToTeacher(id_teacher,id_student);
        return id_teacher;
    }

    public Long addBookToStudent(Long id_student, Long id_book){

        Optional<Student> student = repository.findById(id_student);
        Optional<Book> book = brepository.findById(id_book);

        student.get().addBook(book.get());
        repository.save(student.get());

        return id_book;
    }

    public Long removeBookToStudent(Long id_student, Long id_book){

        Optional<Student> student = repository.findById(id_student);
        Optional<Book> book = brepository.findById(id_book);

        student.get().removeBook(book.get());
        repository.save(student.get());

        return id_book;
    }

}
