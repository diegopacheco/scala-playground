package com.github.diegopacheco.scala3.sb3.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("data_entries")
public class DataEntry {
    @Id
    private Long id;
    private String name;
    private String value;
    private LocalDateTime createdAt;

    public DataEntry() {
    }

    public DataEntry(Long id, String name, String value, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
