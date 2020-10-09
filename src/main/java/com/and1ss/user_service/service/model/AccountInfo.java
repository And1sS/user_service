package com.and1ss.user_service.service.model;

import com.and1ss.user_service.exceptions.InternalServerException;
import com.and1ss.user_service.service.impl.password_hasher.PasswordHasher;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_info")
@DynamicInsert
public class AccountInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String login;

    @NonNull
    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "created_at")
    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    private Timestamp createdAt;

    public AccountInfo(RegisterInfo registerInfo, PasswordHasher hasher) {
        name = registerInfo.getName();
        surname = registerInfo.getSurname();
        login = registerInfo.getLogin();
        try {
            passwordHash = hasher.hashPassword(registerInfo.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerException();
        }
    }
}
