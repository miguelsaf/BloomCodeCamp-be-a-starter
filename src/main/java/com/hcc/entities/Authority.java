package com.hcc.entities;

import javax.persistence.ManyToOne;

public class Authority {
    private Long id;
    private String authority;

    @ManyToOne
    private User user;
}
