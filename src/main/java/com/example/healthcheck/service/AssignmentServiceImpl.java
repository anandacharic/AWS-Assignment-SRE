package com.example.healthcheck.service;

import com.example.healthcheck.dao.AssignmentRepository;
import com.example.healthcheck.entity.Assignment;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService{

    private AssignmentRepository assignmentRepository;
    private EntityManager entityManager;

    @Autowired
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository,EntityManager entityManager){
        this.assignmentRepository=assignmentRepository;
        this.entityManager=entityManager;
    }
    @Override
    public List<Assignment> findAll(){
        return assignmentRepository.findAll();
    }

    @Override
    public Assignment save(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }
    @Override
    public Assignment findById(String id){
        Optional<Assignment> assignedValue=assignmentRepository.findById(id);
        if(assignedValue.isPresent())
            return assignedValue.get();
        else
            return null;
    }
    @Override
    @Transactional
    public void deleteById(String id){
        assignmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Assignment assignment){
        entityManager.merge(assignment);
    }
}
