package com.quantumsoft.insurance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AddOn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addOnId;
    private String name;
    private String description;
    private Double cover;
    private Double premiumPerYear;

    public AddOn(){}

    public AddOn(Long addOnId, String name, String description, Double cover, Double premiumPerYear) {
        this.addOnId = addOnId;
        this.name = name;
        this.description = description;
        this.cover = cover;
        this.premiumPerYear = premiumPerYear;
    }

    public Long getAddOnId() {
        return addOnId;
    }

    public void setAddOnId(Long addOnId) {
        this.addOnId = addOnId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCover() {
        return cover;
    }

    public void setCover(Double cover) {
        this.cover = cover;
    }

    public Double getPremiumPerYear() {
        return premiumPerYear;
    }

    public void setPremiumPerYear(Double premiumPerYear) {
        this.premiumPerYear = premiumPerYear;
    }
}
