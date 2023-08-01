package com.example.project1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project1.model.Tutorial;
import com.example.project1.repository.TutorialRepository;

@RestController
@RequestMapping("/api")

public class TutorialController {
   @Autowired
   TutorialRepository tutorialRepository;//object is created

   @GetMapping("/show_all")
    
   public List<Tutorial> getAllTutorials(){
      return (List<Tutorial>) tutorialRepository.findAll();
   }

   @PostMapping("/create")
   public ResponseEntity<Tutorial>createTutorial(@RequestBody Tutorial tutorial){
      Tutorial tutorialrep = tutorialRepository
         .save(new Tutorial(tutorial.getFirstname(),tutorial.getLastname(),tutorial.getPassword()));

      return new ResponseEntity<Tutorial>(tutorialrep, HttpStatus.OK);
   }

   @DeleteMapping("/delete_all")
   public ResponseEntity<HttpStatus> deleteAllTutorial(){
      tutorialRepository.deleteAll();
      return new ResponseEntity<> (HttpStatus.NOT_FOUND);
   }

   @PutMapping("/insert/{id}")
   public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") Long id,@RequestBody Tutorial tutorial){
      Optional<Tutorial> tutorialrepo = tutorialRepository.findById(id);
      if(tutorialrepo.isPresent()){
         Tutorial _tutorialrepo = tutorialrepo.get();
         _tutorialrepo.setFirstname(tutorial.getFirstname());
         _tutorialrepo.setLastname(tutorial.getLastname());
         _tutorialrepo.setPassword(tutorial.getPassword());

         return new ResponseEntity<>(tutorialRepository.save(_tutorialrepo),HttpStatus.OK);
      }
      else{
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
   }
}
