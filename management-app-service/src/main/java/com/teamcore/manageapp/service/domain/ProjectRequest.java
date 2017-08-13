package com.teamcore.manageapp.service.domain;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

public class ProjectRequest {
    private Long id;
    private String externalName;
    /*
     * specLink is just projects description instead of attached file
     */
    private String specLink;

    private String customerName;

    private String customerEmail;

    private ProjectRequest() {}

    private ProjectRequest(ProjectRequest projectRequest) {
        this.externalName = projectRequest.externalName;
        this.specLink = projectRequest.specLink;
        this.customerName = projectRequest.customerName;
        this.customerName = projectRequest.customerEmail;
    }

    public static ProjectRequest getProjectRequest(){
        return new ProjectRequest();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id =id;
    }

    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(String externalName) {
        this.externalName = externalName;
    }

    public String getSpecLink() {
        return specLink;
    }

    public void setSpecLink(String specLink) {
        this.specLink = specLink;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", id)
                .append("externalName", externalName)
                .append("specLink", specLink)
                .append("customerName", customerName)
                .append("customerEmail", customerEmail)
                .toString();
    }


    public static ProjectRequest.Builder newBuilder() {
        return new ProjectRequest().new Builder();
    }

    public class Builder {

        private Builder() {}

        public ProjectRequest.Builder setId(Long id) {
            ProjectRequest.this.setId(id);
            return this;
        }

        public ProjectRequest.Builder setExternalName(String externalName) {
            ProjectRequest.this.setExternalName(externalName);
            return this;
        }

        public ProjectRequest.Builder setSpecLink(String specLink) {
            ProjectRequest.this.setSpecLink(specLink);
            return this;
        }

        public ProjectRequest.Builder setCustomerName(String customerName) {
            ProjectRequest.this.setCustomerName(customerName);
            return this;
        }

        public ProjectRequest.Builder setCustomerEmail(String customerEmail) {
            ProjectRequest.this.setCustomerEmail(customerEmail);
            return this;
        }

        public ProjectRequest build() {
            return new ProjectRequest(ProjectRequest.this);
        }

    }

}