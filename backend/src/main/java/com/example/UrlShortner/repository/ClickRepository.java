package com.example.UrlShortner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UrlShortner.model.ClickDetails;

public interface ClickRepository extends JpaRepository<ClickDetails, Long> {
    List<ClickDetails> findByShortcode(String shortcode);
}

