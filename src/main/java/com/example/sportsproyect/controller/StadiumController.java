package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.Stadium;
import com.example.sportsproyect.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("stadiums")
public class StadiumController {
    @Autowired
    StadiumService stadiumService;
    @GetMapping()
    public List<Stadium> getAllStadiums()  {
        return stadiumService.getAllStadiums();
    }
    @GetMapping("{id}")
    public Stadium getStadiumById(@PathVariable("id") long id) {
        return stadiumService.getStadiumById(id);
    }
    @PostMapping()
    public Stadium createStadium(@RequestBody Stadium stadium) {
        return stadiumService.createStadium(stadium);
    }
    @PutMapping()
    public Stadium updateStadium(@RequestBody Stadium stadium) {
        return stadiumService.updateStadium(stadium);
    }
    @DeleteMapping("{id}")
    public void deleteStadium(@PathVariable("id") long id) {
        stadiumService.deleteStadium(id);
    }
    @DeleteMapping()
    public void deleteAllStadiums() {
        stadiumService.deleteAllStadiums();
    }
}