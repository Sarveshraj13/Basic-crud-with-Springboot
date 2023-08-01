package com.example.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;

import com.example.project1.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial,Long> {
    
}
