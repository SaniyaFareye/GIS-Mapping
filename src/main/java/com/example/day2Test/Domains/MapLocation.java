package com.example.day2Test.Domains;

import javax.persistence.*;

@Entity
@Table(name = "map_location")
public class MapLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mapLocation_id")
    private Long categoryId;

    @Column(name = "locationName",unique = true)
    private String locationName;

    @Column(name="approval_status")
    private String approval_status;

    @Column(name="latitiude")
    private Double latitude;

    @Column(name="longitude")
    private Double longitude;

    @ManyToOne
    @JoinColumn(name="categoryId",insertable=true,updatable=true,nullable = false)
    private Category category;

    public MapLocation() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String  getApproval_status() {
        return approval_status;
    }

    public void setApproval_status(String  approval_status) {
        this.approval_status = approval_status;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
