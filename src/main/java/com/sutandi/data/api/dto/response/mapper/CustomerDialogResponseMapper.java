package com.sutandi.data.api.dto.response.mapper;

import com.sutandi.data.api.dto.response.CustomerDialogResponse;
import com.sutandi.data.api.repositories.entities.DialogEntity;

/**
 * Mapper for {@link CustomerDialogResponse}
 */
public enum CustomerDialogResponseMapper {
    ;

    public static CustomerDialogResponse fromEntity(DialogEntity dialogEntity) {
        return CustomerDialogResponse.builder()
                .dialogId(dialogEntity.getDialogId())
                .customerId(dialogEntity.getCustomerId())
                .text(dialogEntity.getText())
                .language(dialogEntity.getLanguage())
                .timestamp(dialogEntity.getTimestamp())
                .build();
    }
}
