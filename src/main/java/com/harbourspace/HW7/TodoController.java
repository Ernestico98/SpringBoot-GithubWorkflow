package com.harbourspace.HW7;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TodoController {
    @Autowired
    private IDatabase DB;

    @GetMapping("/todo")
    public ResponseEntity<ArrayList<ListItem>> index() {
        var itemList = DB.getAll();

        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<ListItem> store(@RequestBody ListItem item) {
        DB.addItem(item);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("todo/{id}")
    public ResponseEntity<ListItem> show(@PathVariable int id) {

        ListItem item;
        try {
            item = DB.getItem(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}