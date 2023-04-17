package com.example.learningPlatform.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Data
@NoArgsConstructor
public class Salt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private int sid;
    @Column(name = "Salt")
    private byte[] salt;
    @OneToOne(mappedBy = "salt")
    private Users users;

    public Salt(byte[] salt){
        this.salt = salt;
    }


}
