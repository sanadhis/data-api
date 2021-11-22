package com.sutandi.data.api.repositories.mappers;

import com.sutandi.data.api.dto.request.CustomerDialogRequest;
import com.sutandi.data.api.repositories.entities.DialogEntity;

import static java.time.Instant.now;

/**
 * Mapper for {@link DialogEntity}
 */
public enum DialogEntityMapper {
    ;

    public static DialogEntity from(Long dialogId,
                                    Long customerId,
                                    CustomerDialogRequest customerDialogRequest) {
        return DialogEntity.builder()
                .customerId(customerId)
                .dialogId(dialogId)
                .text(customerDialogRequest.getText())
                .language(customerDialogRequest.getLanguage())
                .consent(null)
                .timestamp(now())
                .build();
    }
}
