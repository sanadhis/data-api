package com.sutandi.data.api.dto.response.mapper;

import com.sutandi.data.api.repositories.entities.DialogEntity;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerDialogResponseMapperTest {
    @Test
    void entityToResponseMapping() {
        var dialogId = 1L;
        var customerId = 1L;
        var text = "some text";
        var language = "EN";
        var now = Instant.now();

        var dialogEntity = new DialogEntity();
        dialogEntity.setDialogId(dialogId);
        dialogEntity.setCustomerId(customerId);
        dialogEntity.setText(text);
        dialogEntity.setLanguage(language);
        dialogEntity.setTimestamp(now);

        var customerDialogResponse = CustomerDialogResponseMapper.fromEntity(dialogEntity);

        assertThat(customerDialogResponse.getDialogId()).isEqualTo(dialogId);
        assertThat(customerDialogResponse.getCustomerId()).isEqualTo(customerId);
        assertThat(customerDialogResponse.getText()).isEqualTo(text);
        assertThat(customerDialogResponse.getLanguage()).isEqualTo(language);
        assertThat(customerDialogResponse.getTimestamp()).isEqualTo(now);
    }
}
