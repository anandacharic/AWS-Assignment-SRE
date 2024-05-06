package com.example.healthcheck.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "joinkeys")
public class JoinKeys {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name="email")
    private String email;

    public JoinKeys(String account_email, String assignment_id) {
        this.email = account_email;
        this.assid = assignment_id;
    }

    @Override
    public String toString() {
        return "JoinKeys{" +
                "id='" + id + '\'' +
                ", account_email='" + email + '\'' +
                ", assignment_id='" + assid + '\'' +
                '}';
    }

    public String getAccount_email() {
        return email;
    }

    public void setAccount_email(String account_email) {
        this.email = account_email;
    }

    public String getAssignment_id() {
        return assid;
    }

    public void setAssignment_id(String assignment_id) {
        this.assid = assignment_id;
    }

    @Column(name="assid")
    public String assid;

    public JoinKeys(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
