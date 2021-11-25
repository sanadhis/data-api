package com.sutandi.data.api.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "dialog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DialogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long dialogId;
    private Long customerId;
    private String text;
    private String language;
    private Boolean consent;
    private Instant timestamp;
}
