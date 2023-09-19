package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.Stadium;
import com.example.sportsproyect.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/stadiums")
public class StadiumController {

    @Autowired
    StadiumRepository stadiumRepository;

    @GetMapping()
    public ResponseEntity<List<Stadium>> getAllStadiums(@RequestParam(required = false) String title) {
        try {
            List<Stadium> stadiums = new ArrayList<Stadium>();

            if (title == null)
                stadiumRepository.findAll().forEach(stadiums::add);
            if (stadiums.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(stadiums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stadium> getStadiumById(@PathVariable("id") long id) {
        Optional<Stadium> stadiumData = stadiumRepository.findById(id);

        if (stadiumData.isPresent()) {
            return new ResponseEntity<>(stadiumData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Stadium> createStadium(@RequestBody Stadium stadium) {
        try {
            Stadium _stadium = stadiumRepository
                    .save(new Stadium(stadium.getName(), stadium.getLocation(), stadium.getTeam()));
            return new ResponseEntity<>(_stadium, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stadium> updateStadium(@PathVariable("id") long id, @RequestBody Stadium stadium) {
        Optional<Stadium> stadiumData = stadiumRepository.findById(id);

        if (stadiumData.isPresent()) {
            Stadium _stadium = stadiumData.get();
            _stadium.setName(stadium.getName());
            _stadium.setLocation(stadium.getLocation());
            _stadium.setTeam(stadium.getTeam());
            return new ResponseEntity<>(stadiumRepository.save(_stadium), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStadium(@PathVariable("id") long id) {
        try {
            stadiumRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllStadiums() {
        try {
            stadiumRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}