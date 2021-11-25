package com.sutandi.data.api.services;

import com.sutandi.data.api.dto.request.CustomerDialogRequest;
import com.sutandi.data.api.dto.response.CustomerDialogResponse;

import java.util.List;
import java.util.Optional;

/**
 * API Contract
 */
public interface DialogService {
    List<CustomerDialogResponse> findAll(Optional<String> language,
                                         Optional<Long> customerId,
                                         Integer pageNo,
                                         Integer pageSize);

    void insert(Long dialogId,
                Long customerId,
                CustomerDialogRequest customerDialogRequest);

    void updateConsent(Long dialogId,
                       Boolean consent);
}
