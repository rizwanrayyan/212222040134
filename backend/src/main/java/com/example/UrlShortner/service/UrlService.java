package com.example.UrlShortner.service;

import com.example.UrlShortner.model.ClickDetails;
import com.example.UrlShortner.model.UrlMapping;
import com.example.UrlShortner.model.UrlStatisticsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.UrlShortner.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private ClickRepository clickRepository;

    public UrlMapping createShortUrl(String originalUrl, String shortCode, int validityMinutesStr) {
        if (originalUrl == null || originalUrl.isBlank()) {
            throw new IllegalArgumentException("Original URL is required.");
        }

        int validity = 30;
        if (validityMinutesStr != 0) {
            validity = validityMinutesStr;
        }

        if (shortCode == null || shortCode.isBlank()) {
            shortCode = UUID.randomUUID().toString().substring(0, 6);
        } else if (!shortCode.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Shortcode must be alphanumeric.");
        } else if (urlRepository.existsByShortCode(shortCode)) {
            throw new IllegalArgumentException("Shortcode already exists.");
        }

        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortCode(shortCode);
        mapping.setCreatedAt(LocalDateTime.now());
        mapping.setValidUntil(mapping.getCreatedAt().plusMinutes(validity));

        return urlRepository.save(mapping);
    }

    public String getOriginalUrl(String shortCode) {
        Optional<UrlMapping> optional = urlRepository.findByShortCode(shortCode);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Shortcode not found.");
        }

        UrlMapping mapping = optional.get();
        if (mapping.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Link has expired.");
        }

        return mapping.getOriginalUrl();
    }

	public UrlStatisticsResponse getStatistics(String shortcode) {
	    UrlMapping urlMapping = urlRepository.findByShortCode(shortcode)
	            .orElseThrow(() -> new IllegalArgumentException("Shortcode not found"));

	        List<ClickDetails> clicks = clickRepository.findByShortcode(shortcode);

	        UrlStatisticsResponse response = new UrlStatisticsResponse();
	        response.setShortcode(shortcode);
	        response.setOriginalUrl(urlMapping.getOriginalUrl());
	        response.setClickCount(clicks.size());
	        response.setCreatedAt(urlMapping.getCreatedAt());
	        response.setExpiresAt(urlMapping.getValidUntil());
	        response.setClickDetails(clicks);
	        
	        return response;
	}
}
