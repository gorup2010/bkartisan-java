package com.bkartisan.be.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.bkartisan.be.Dto.RegisterRequestDTO;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    // @Convert(converter = RoleConverter.class)
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

// @Converter(autoApply = true)
// class RoleConverter implements AttributeConverter<Role, String> {

//     @Override
//     public String convertToDatabaseColumn(Role role) {
//         if (role == null) {
//             return null;
//         }
//         return role.getValue();
//     }

//     @Override
//     public Role convertToEntityAttribute(String dbData) {
//         if (dbData == null || dbData.isEmpty()) {
//             return null;
//         }

//         for (Role role : Role.values()) {
//             if (role.getValue().equalsIgnoreCase(dbData)) {
//                 return role;
//             }
//         }

//         throw new IllegalArgumentException("Unknown database value: " + dbData);
//     }
// }
