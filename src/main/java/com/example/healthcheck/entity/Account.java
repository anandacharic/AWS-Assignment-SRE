package com.example.healthcheck.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "first_name",nullable = false)
    private String first_name;
    @Column(name="last_name",nullable = false)
    private String last_name;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "account_created")
    @ReadOnlyProperty
    @CreationTimestamp(source = SourceType.DB)
    private String account_created;
    @Column(name = "account_updated")
    @ReadOnlyProperty
    @UpdateTimestamp(source = SourceType.DB)
    private String account_updated;

    public Account(String first_name, String last_name, String email, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    public Account(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount_created() {
        return account_created;
    }

    public void setAccount_created(String account_created) {
        this.account_created = account_created;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", password='" + password + '\'' +
                ", account_created='" + account_created + '\'' +
                ", account_updated='" + account_updated + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public String getAccount_updated() {
        return account_updated;
    }
    public void setAccount_updated(String account_updated) {
        this.account_updated = account_updated;
    }

}
