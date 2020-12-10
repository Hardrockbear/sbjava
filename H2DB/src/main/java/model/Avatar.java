package model;

import javax.persistence.*;

@Entity
@Table(name = "avatars")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "photo_url")
    private String photoUrl;

    public Avatar(){ }

    public Avatar(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
