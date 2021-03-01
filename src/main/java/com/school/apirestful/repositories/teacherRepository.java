package com.school.apirestful.repositories;

import com.school.apirestful.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface teacherRepository
        extends JpaRepository<Teacher, Long> {

    @Query(value = "SELECT * FROM teacher AS t WHERE t.name LIKE ?1%", nativeQuery = true)
    List<Teacher> getByName(String name);

    @Query(value="SELECT t.* FROM teacher AS t INNER JOIN teacher_student" +
            " AS ts ON ts.teacher_id = t.id WHERE ts.student_id=?1 " +
            "ORDER BY t.name ASC LIMIT ?2 OFFSET ?3 ", nativeQuery=true)
    List<Teacher> getTeacherByStudentId(Long id, int limit, int offset);

}
