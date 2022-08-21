package io.s3vt.usho.controller;

import io.s3vt.usho.model.UshoEntity;
import io.s3vt.usho.service.UshoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usho")
public class UshoRestController {
    private static final Logger logger = LoggerFactory.getLogger(UshoRestController.class);

    @Autowired
    private UshoService ushoService;


    @GetMapping("{usho}")
    public ResponseEntity<UshoEntity> getUshoById(@PathVariable String usho) {
        return ResponseEntity.status(HttpStatus.OK).body(ushoService.find(usho));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UshoEntity> createShortURL(@RequestBody String longUrl) {
        UshoEntity entity = ushoService.createUsho(longUrl);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }
}
