package com.hyeon.jpaboard.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Tag {
    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

}
