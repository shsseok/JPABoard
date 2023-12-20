package com.hyeon.jpaboard.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private String reviewTitle;
    private String reviewContent;
    private Long reviewViews;
    private LocalDateTime reviewCreateAt;
    private LocalDateTime reviewUpdateAt;
}