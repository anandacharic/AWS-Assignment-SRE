package com.example.healthcheck.service;

import com.example.healthcheck.dao.JoinKeysRepository;
import com.example.healthcheck.entity.Account;
import com.example.healthcheck.entity.JoinKeys;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinKeysServiceImpl implements JoinKeysService{

    private JoinKeysRepository joinKeysRepository;
    private EntityManager entityManager;

    @Autowired
    public JoinKeysServiceImpl(JoinKeysRepository joinKeysRepository,EntityManager entityManager){
        this.joinKeysRepository=joinKeysRepository;
        this.entityManager=entityManager;
    }
    @Override
    public JoinKeys save(JoinKeys joinKeys) {
        return joinKeysRepository.save(joinKeys);
    }


    @Override
    public List<JoinKeys> findByEmail(String email) {
        TypedQuery<JoinKeys> theQuery = entityManager.createQuery("From JoinKeys where email=:data", JoinKeys.class);
        theQuery.setParameter("data",email);
        return theQuery.getResultList();
    }

    @Override
    public List<JoinKeys> findByAccountId(String id) {
        TypedQuery<JoinKeys> theQuery = entityManager.createQuery("From JoinKeys where assid=:data", JoinKeys.class);
        theQuery.setParameter("data",id);
        return theQuery.getResultList();
    }

    @Override
    public List<JoinKeys> findByEmailAndID(String email,String id){
        TypedQuery<JoinKeys> theQuery = entityManager.createQuery("From JoinKeys where email=:data and assid=:data1 ", JoinKeys.class);
        theQuery.setParameter("data",email);
        theQuery.setParameter("data1",id);
        return theQuery.getResultList();
    }

    @Override
    public void deleteById(String id){
        joinKeysRepository.deleteById(id);
    }

}
