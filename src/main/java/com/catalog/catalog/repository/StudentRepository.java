package com.catalog.catalog.repository;

import com.catalog.catalog.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByFirstNameOrLastName(String firstName, String lastName);
    boolean existsByFirstNameAndLastName(String firstName, String lastName);

}
