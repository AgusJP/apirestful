package com.school.apirestful.repositories;

import com.school.apirestful.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface studentRepository
        extends JpaRepository<Student, Long> {

    @Query(value = "SELECT * FROM student AS s WHERE s.name LIKE ?1%", nativeQuery = true)
    List<Student> getByName(String name);

    @Query(value="SELECT s.* FROM student AS s INNER JOIN teacher_student" +
            " AS ts ON ts.student_id = s.id WHERE ts.teacher_id=?1 " +
            "ORDER BY s.name ASC LIMIT ?2 OFFSET ?3", nativeQuery=true)
     List<Student> getStudentByTeacherId(Long id, int limit, int offset);

     @Query(value = "INSERT INTO teacher_student(teacher_id, student_id)" +
             "VALUES (?1, ?2) RETURNING teacher_id", nativeQuery = true)
     Long addStudentToTeacher(Long idStudent, Long idTeacher);
}
