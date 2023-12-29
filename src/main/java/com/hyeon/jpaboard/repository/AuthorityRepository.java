package com.hyeon.jpaboard.repository;


import com.hyeon.jpaboard.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
