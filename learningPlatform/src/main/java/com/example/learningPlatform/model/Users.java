package com.example.learningPlatform.model;


import com.example.learningPlatform.repo.IUserRepo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Table(name = "users")
@Entity
@Data
@NoArgsConstructor

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private int id ;
    @Column(name = "Name")
    @NotBlank(message = "Not blank")
    @Size(min = 3,max = 30, message = "Out of range min 3 un max 30")
    @Pattern(regexp = "[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}+[a-zāčēģīķļņšūž]*+[- ]{1}+[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}+[a-zāčēģīķļņšūž]*|[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}+[a-zāčēģīķļņšūž]*", message = "examples June or Mary Barbara")
    private String name;
    @Column(name = "Surname")
    @NotBlank(message = "Not blank")
    @Size(min = 3,max = 30, message = "Out of range min 3 un max 30")
    @Pattern(regexp = "[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}+[a-zāčēģīķļņšūž]*+[- ]{1}+[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}+[a-zāčēģīķļņšūž]*|[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}+[a-zāčēģīķļņšūž]*", message = "examples Alien or Harding-Rolls")
    private String surname;
    @Column(name = "Role")
    private String role ;
    @Column(name = "Email")
    @NotBlank(message = "Not blank")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Can use alphabet, numbers and .- and should include, for example, @gmail.com")
    private String email;
    @Column(name = "Username")
    @NotBlank(message = "Not blank")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,16}", message = "From 6 to 16 numbers and alphabet")
    private String username;
    @Column(name = "Hashpassword")
    private String hashPassword;


    @Column(name = "Password", insertable=false, updatable=false)
    @NotBlank(message = "Not blank")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password ;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "saltid", referencedColumnName = "id")
    private Salt salt;
    @OneToMany(mappedBy="user_id")
    private Collection<Tests> userTests;

    public Users(String name, String surname, String role, String email, String username, String hashPassword, String password, Salt salt) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.surname = surname;
        this.username = username;
        this.hashPassword = hashPassword;
        setSalt(salt);

    }
}
