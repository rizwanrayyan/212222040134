package com.example.UrlShortner.controller;


import com.example.UrlShortner.middleware.LogService;
import com.example.UrlShortner.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.UrlShortner.service.UrlService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @Autowired
    private LogService logService;

    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody UrlRequest request) {
        try {
            UrlMapping mapping = urlService.createShortUrl(
                    request.getUrl(),
                    request.getShortcode(),
                    request.getValidity()
            );

            logService.log("backend", "info", "controller", "Shortened URL created");
            return ResponseEntity.ok(mapping);
        } catch (IllegalArgumentException e) {
            logService.log("backend", "error", "controller", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }


    @GetMapping("/shorturls/{shortcode}")
    public ResponseEntity<?> getUrlStatistics(@PathVariable String shortcode) {
        try {
            UrlStatisticsResponse stats = urlService.getStatistics(shortcode);
            logService.log("backend", "info", "controller", "Fetched stats for shortcode: " + shortcode);
            return ResponseEntity.ok(stats);
        } catch (IllegalArgumentException e) {
            logService.log("backend", "error", "controller", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

}
