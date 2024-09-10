package com.hcc.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.ManyToOne;

public class Authority implements GrantedAuthority {
    private Long id;
    private String authority;

    @ManyToOne
    private User user;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
