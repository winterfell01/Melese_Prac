package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private int accountnum;
    @NotBlank
    private float change;
    @NotBlank
    private float amount;
    @NotBlank
    private float balance;
    @NotBlank
    private String username;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /*@OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    public Set<Transaction> transactions;*/

}
