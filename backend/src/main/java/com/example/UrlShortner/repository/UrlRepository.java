package com.example.UrlShortner.repository;

import com.example.UrlShortner.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlMapping, Long> {
    boolean existsByShortCode(String shortCode);
    Optional<UrlMapping> findByShortCode(String shortCode);
}
