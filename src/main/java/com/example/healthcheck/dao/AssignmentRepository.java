package com.example.healthcheck.dao;

import com.example.healthcheck.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment,String> {
}
