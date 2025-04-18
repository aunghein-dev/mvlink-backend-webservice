package com.minidevtechcom.myanHomeLabBackend.controller;

import com.minidevtechcom.myanHomeLabBackend.model.BrokenLink;
import com.minidevtechcom.myanHomeLabBackend.service.BrokenLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/linkbroken")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BrokenLinkController {

    @Autowired
    private BrokenLinkService service;

    @GetMapping("/get")
    public ResponseEntity<List<BrokenLink>> getAllBrokenLink(){
        return new ResponseEntity<>(service.getAllBrokenLink(), HttpStatus.FOUND);
    }

    // POST: Create new broken link entry
    @PostMapping("/create")
    public ResponseEntity<BrokenLink> createBrokenLink(@RequestBody BrokenLink brokenLink) {
        BrokenLink saved = service.save(brokenLink);
        return ResponseEntity.ok(saved);
    }


    // PUT: Update existing broken link entry
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBrokenLink(@RequestBody BrokenLink brokenLink) {
        Optional<BrokenLink> existing = service.findByTmdbId(brokenLink.getTmdbId());
        if (existing.isPresent()) {
            return ResponseEntity.status(409).body("BrokenLink with this TMDB ID already exists.");
        }

        BrokenLink saved = service.save(brokenLink);
        return ResponseEntity.ok(saved);
    }

}
