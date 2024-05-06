package com.example.healthcheck.service;

import com.example.healthcheck.entity.Assignment;

import java.util.List;

public interface AssignmentService {
    List<Assignment> findAll();
    Assignment save(Assignment assignment);
    Assignment findById(String id);
    void deleteById(String id);
    void update(Assignment assignment);
}
