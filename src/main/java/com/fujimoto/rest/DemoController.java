package com.fujimoto.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.Pattern;
import java.net.URI;
import java.util.Map;

@RestController
@Validated
public class DemoController {

    @GetMapping("/names")
    public String getName( @RequestParam("id") @Pattern (regexp = "^[1-3]$" ) String id) {
        Map<Integer, String> namesById = Map.of(1,"Ichiro",2, "Jiro",3,"Saburo");
        return namesById.get(Integer.valueOf(id));
    }

    @PostMapping("/names")
    public ResponseEntity<String> create(@RequestBody CreateForm form){
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

    @DeleteMapping("/names/{id}")
    public ResponseEntity<Map<String, String>> deleteForm(@PathVariable("id") int id , @RequestBody UpdateForm form){
        return ResponseEntity.ok(Map.of("message", "name successfully deleted" ));
    }



}
