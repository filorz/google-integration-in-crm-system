package com.crmpetproject.crmpetproject.models;

import javax.persistence.*;

@Entity
@Table(name = "client_data")
public class ClientData {
    @Id
    @GeneratedValue
    @Column(name = "client_data_id")
    private Long id;

    @Column(name = "client_info")
    private String info;

    public ClientData() {}

    public ClientData(String info) {
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
