package com.sutandi.data.api.services.impl;

import com.sutandi.data.api.dto.request.CustomerDialogRequest;
import com.sutandi.data.api.repositories.DialogRepository;
import com.sutandi.data.api.services.DialogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.sutandi.data.api.repositories.DialogSpecification.isConsent;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.jpa.domain.Specification.where;

@ExtendWith(SpringExtension.class)
@EntityScan(basePackages = "com.sutandi.data.api.repositories.entities")
@DataJpaTest
@ActiveProfiles("local")
class DialogServiceImplTest {
    @Autowired
    private DialogRepository dialogRepository;

    private DialogService dialogService;
    private int numberOfDialog;
    private int numberOfDialogWithTrueConsent;

    private static final Pageable DEFAULT_PAGE = PageRequest.of(0, 10);

    @BeforeEach
    void setUp() {
        dialogService = new DialogServiceImpl(dialogRepository);
        numberOfDialog = dialogRepository.findAll().size();
        numberOfDialogWithTrueConsent = dialogRepository.findAll(where(isConsent()), DEFAULT_PAGE).getNumberOfElements();
    }

    @Test
    void selectAll() {
        var allDialog = dialogService.findAll(empty(), empty(), 0,10);
        assertThat(allDialog).hasSize(numberOfDialogWithTrueConsent);
    }

    @Test
    void selectAllWithCreterias() {
        var filteredDialog = dialogService.findAll(Optional.of("EN"), empty(), 0,10);
        assertThat(filteredDialog).hasSize(2);

        filteredDialog = dialogService.findAll(empty(), Optional.of(1L), 0,10);
        assertThat(filteredDialog).hasSize(3);

        filteredDialog = dialogService.findAll(Optional.of("FR"), Optional.of(1L), 0, 10);
        assertThat(filteredDialog).hasSize(1);
    }

    @Test
    void newCustomerDialog() {
        var dialogId = Long.valueOf(5);
        var customerId = Long.valueOf(5);
        var customerDialogRequest = new CustomerDialogRequest();
        customerDialogRequest.setLanguage("DE");
        customerDialogRequest.setText("Ich bin Adhi");

        dialogService.insert(dialogId, customerId, customerDialogRequest);

        assertThat(dialogRepository.findAll()).hasSize(numberOfDialog + 1);
    }

    @Test
    void updateTrueConsent() {
        dialogService.updateConsent(2L, Boolean.TRUE);

        assertThat(dialogRepository.findAll(where(isConsent()), DEFAULT_PAGE)).hasSize(numberOfDialogWithTrueConsent + 1);
    }

    @Test
    void updateFalseConsent() {
        dialogService.updateConsent(3L, Boolean.FALSE);

        assertThat(dialogRepository.findAll()).hasSize(numberOfDialog - 1);
    }
}
