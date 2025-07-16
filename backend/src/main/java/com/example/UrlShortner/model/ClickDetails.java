package com.example.UrlShortner.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class ClickDetails {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String shortcode; 

	    private LocalDateTime timestamp;
	    private String referrer;
	    private String geoLocation;

	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}

		public String getReferrer() {
			return referrer;
		}

		public void setReferrer(String referrer) {
			this.referrer = referrer;
		}

		public String getGeoLocation() {
			return geoLocation;
		}

		public void setGeoLocation(String geoLocation) {
			this.geoLocation = geoLocation;
		}

		public String getShortcode() {
	        return shortcode;
	    }

	    public void setShortcode(String shortcode) {
	        this.shortcode = shortcode;
	    }


	


}

