package com.school.apirestful.repositories;

import com.school.apirestful.models.teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface teacherRepository
        extends JpaRepository<teacher, Long> {

    @Query(value = "SELECT * FROM teacher AS t WHERE t.name LIKE ?1%", nativeQuery = true)
    List<teacher> getByName(String name);


}
