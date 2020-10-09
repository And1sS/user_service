package com.and1ss.user_service.service.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "access_token")
@DynamicInsert
public class AccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    private UUID token;

    @NonNull
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "created_at")
    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    private Timestamp createdAt;
}