package com.example.healthcheck.service;

import com.example.healthcheck.entity.Account;
import com.example.healthcheck.entity.JoinKeys;
import org.hibernate.mapping.Join;

import java.util.List;

public interface JoinKeysService {
    public JoinKeys save(JoinKeys joinKeys);
    public List<JoinKeys> findByEmail(String email);
    public List<JoinKeys> findByAccountId(String id);
    public List<JoinKeys> findByEmailAndID(String email,String id);
    void deleteById(String id);
}
