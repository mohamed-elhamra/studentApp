package com.melhamra.studentApp.repository;


import com.melhamra.studentApp.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //search using name of student.
    List<Student> findByName(String name);
    //search using name of student page per page.
    Page<Student> findByName(String name, Pageable pageable);

    @Query("select e from Student e where e.name like %:name%")
    Page<Student> searchByName(@Param("name") String name, Pageable pageable);
}
