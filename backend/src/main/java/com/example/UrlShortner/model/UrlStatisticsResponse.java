package com.example.UrlShortner.model;

import java.time.LocalDateTime;
import java.util.List;

public class UrlStatisticsResponse {
    private String originalUrl;
    private String shortcode;
    private int clickCount;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private List<ClickDetails> clickDetails;
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getShortcode() {
		return shortcode;
	}
	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}
	public List<ClickDetails> getClickDetails() {
		return clickDetails;
	}
	public void setClickDetails(List<ClickDetails> clickDetails) {
		this.clickDetails = clickDetails;
	}

}

