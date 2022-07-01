package com.fujimoto.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class DemoController {

    @GetMapping("/names")
    public List<String> getName(){
        return List.of("Ichiro","Jiro", "Saburo");
    }

    @PostMapping("/names")
    public ResponseEntity<String> create(@RequestBody CreateForm form){
        System.out.println(form);
        URI url = UriComponentsBuilder.fromUriString("http:localhost:8080")
                .path("/names/id")
                .build()
                .toUri();
        return ResponseEntity.created(url).body("name successfully created");
    }

    @PatchMapping("/names/{id}")
    public ResponseEntity<Map<String, String>> updateForm(@PathVariable("id") int id , @RequestBody UpdateForm form){
        return ResponseEntity.ok(Map.of("message", "name successfully updated" ));
    }




}
