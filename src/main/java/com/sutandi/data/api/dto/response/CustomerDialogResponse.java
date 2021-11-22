package com.sutandi.data.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDialogResponse {
    private Long dialogId;
    private Long customerId;
    private String text;
    private String language;
    private Instant timestamp;
}
