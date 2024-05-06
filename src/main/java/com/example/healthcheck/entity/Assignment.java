package com.example.healthcheck.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="Assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ReadOnlyProperty
    @Column(name = "id")
    private String id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "points",nullable = false)
    @Min(value = 1)
    @Max(value = 100)
    private int points;
    @Column(name = "num_of_attemps",nullable = false)
    @Min(value = 1)
    @Max(value = 100)
    private int num_of_attemps;
    @Column(name = "deadline",nullable = false)
    private Date deadline;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getAccount_created() {
        return account_created;
    }

    public void setAccount_created(Date account_created) {
        this.account_created = account_created;
    }

    public Date getAccount_updated() {
        return account_updated;
    }

    public void setAccount_updated(Date account_updated) {
        this.account_updated = account_updated;
    }

    @Column(name = "account_created")
    @ReadOnlyProperty
    @CreationTimestamp(source = SourceType.DB)
    private Date account_created;
    @Column(name = "account_updated")
    @ReadOnlyProperty
    @UpdateTimestamp(source = SourceType.DB)
    private Date account_updated;

    public Assignment() {
    }

    public Assignment(String name, int points, int num_of_attemps, Date deadline) {
        this.name = name;
        this.points = points;
        this.num_of_attemps = num_of_attemps;
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNum_of_attemps() {
        return num_of_attemps;
    }

    public void setNum_of_attemps(int num_of_attemps) {
        this.num_of_attemps = num_of_attemps;
    }

    public Date getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", points=" + points +
                ", num_of_attemps=" + num_of_attemps +
                ", deadline='" + deadline + '\'' +
                ", account_created='" + account_created + '\'' +
                ", account_updated='" + account_updated + '\'' +
                '}';
    }

    public void setDeadline(Date deadline) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String dead = dateFormat.format(deadline);
        try {
            this.deadline=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(dead);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
