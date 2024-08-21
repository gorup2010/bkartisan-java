package com.bkartisan.be.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.bkartisan.be.Dto.RegisterRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

enum LoginType {
    NORMAL("normal"),
    FACEBOOK("facebook"),
    GOOGLE("google");

    private final String value;

    LoginType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

enum Role {
    BUYER("buyer"),
    SELLER("seller"),
    COLLABORATOR("collab"),
    ADMIN("admin");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

@Entity
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    @Id
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private String numPhone;
    @Column
    private Character gender;
    @Column
    private String loginType;
    @Column(nullable = true)
    private LocalDateTime createdAt;
    @Column
    private String avatar;
    @Column(nullable = false)
    private String role;
    @Column()
    private LocalDateTime lockUntil;
    @Column
    private String nation;
    @Column
    private String shopName;

    public User(RegisterRequestDTO registerRequest) {
        this.username = registerRequest.username();
        this.password = registerRequest.password();
        this.name = registerRequest.username();
        this.email = registerRequest.email();
    }
}
