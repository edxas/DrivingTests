package com.example.learningPlatform.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @Setter(value = AccessLevel.NONE)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private long uid;
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
    @NotBlank(message = "Not blank")
    private Role role = Role.guest;
    @Column(name = "Email")
    @NotBlank(message = "Not blank")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Can use alphabet, numbers and .-")
    private String email;
    @Column(name = "Username")
    @NotBlank(message = "Not blank")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,16}", message = "From 6 to 16 numbers and alphabet")
    private String username;
    @Column(name = "Password")
    @NotBlank(message = "Not blank")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;

    public User(String name, String surname, Role role, String email, String username,String password) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
