package com.quantumsoft.insurance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hospitals")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospitalId;

    private String name;

    private String address;

    private String city;

    private String state;

    private String postalCode;

    private String contactNumber;

    private String email;

    private String website;

    private boolean isNetworkHospital;

    private Integer availableBeds;

    private Boolean icuAvailable;

    private Boolean isActive;

    private LocalDateTime lastUpdated;

    @ElementCollection
    @CollectionTable(name = "hospital_specialties", joinColumns = @JoinColumn(name = "hospital_id"))
    @Column(name = "specialty")
    private Set<String> specialties;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Doctor> doctors;


    public Hospital() {

        this.lastUpdated = LocalDateTime.now();
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateLastUpdated();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        updateLastUpdated();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        updateLastUpdated();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        updateLastUpdated();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        updateLastUpdated();
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        updateLastUpdated();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        updateLastUpdated();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
        updateLastUpdated();
    }

    public boolean isNetworkHospital() {
        return isNetworkHospital;
    }

    public void setNetworkHospital(boolean networkHospital) {
        isNetworkHospital = networkHospital;
        updateLastUpdated();
    }

    public Integer getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(Integer availableBeds) {
        this.availableBeds = availableBeds;
        updateLastUpdated();
    }

    public Boolean getIcuAvailable() {
        return icuAvailable;
    }

    public void setIcuAvailable(Boolean icuAvailable) {
        this.icuAvailable = icuAvailable;
        updateLastUpdated();
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
        updateLastUpdated();
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public Set<String> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<String> specialties) {
        this.specialties = specialties;
        updateLastUpdated();
    }

    // Auto update timestamp on changes
    private void updateLastUpdated() {
        this.lastUpdated = LocalDateTime.now();
    }

    // toString() - useful for logging/debugging
    @Override
    public String toString() {
        return "Hospital{" +
                "hospitalId=" + hospitalId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", isNetworkHospital=" + isNetworkHospital +
                ", availableBeds=" + availableBeds +
                ", icuAvailable=" + icuAvailable +
                ", isActive=" + isActive +
                ", lastUpdated=" + lastUpdated +
                ", specialties=" + specialties +
                '}';
    }
}

