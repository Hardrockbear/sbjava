package model;

import javax.persistence.*;

@Entity
@Table(name = "emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;

    public Email (){}

    public Email(String email){
        this.email = email;
    }
}
