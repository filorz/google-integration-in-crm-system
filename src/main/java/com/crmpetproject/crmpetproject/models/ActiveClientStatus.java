package com.crmpetproject.crmpetproject.models;

import javax.persistence.*;

@Entity
@Table(name = "active_client_status")
public class ActiveClientStatus {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private String status;

    public ActiveClientStatus() {
    }

    public ActiveClientStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ActiveClientStatus{" + "id=" + id + ", status='" + status + "'}";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
