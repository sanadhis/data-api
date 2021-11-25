package com.sutandi.data.api.repositories.mappers;

import com.sutandi.data.api.dto.request.CustomerDialogRequest;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class DialogEntityMapperTest {
    @Test
    void requestToEntityMapping() {
        var dialogId = 1L;
        var customerId = 1L;
        var text = "some text";
        var language = "EN";
        var checkpoint = Instant.now();

        var customerDialogRequest = new CustomerDialogRequest();
        customerDialogRequest.setText(text);
        customerDialogRequest.setLanguage(language);

        var dialogEntity = DialogEntityMapper.from(dialogId, customerId, customerDialogRequest);

        assertThat(dialogEntity.getDialogId()).isEqualTo(dialogId);
        assertThat(dialogEntity.getCustomerId()).isEqualTo(customerId);
        assertThat(dialogEntity.getText()).isEqualTo(text);
        assertThat(dialogEntity.getLanguage()).isEqualTo(language);
        assertThat(dialogEntity.getConsent()).isNull();
        assertThat(dialogEntity.getTimestamp()).isAfter(checkpoint);
    }
}
