package com.epam.managementapp.service.domain;


import java.util.Date;

public class Project {
    private int id;
    private String outerName;
    private String innerName;
    private String techFile;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public Project(){

    }

    public Project(String outerName, String innerName, String techFile, String status, Date createdAt, Date updatedAt) {
       // this.id = id;
        this.outerName = outerName;
        this.innerName = innerName;
        this.techFile = techFile;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =id;
    }

    public String getOuterName() {
        return outerName;
    }

    public void setOuterName(String outerName) {
        this.outerName = outerName;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public String getTechFile() {
        return techFile;
    }

    public void setTechFile(String techFile) {
        this.techFile = techFile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "id="+id+" outerName="+outerName+ "innername= "+innerName + "status="+status;
    }
}
