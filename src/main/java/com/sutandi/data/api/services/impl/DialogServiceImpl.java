package com.sutandi.data.api.services.impl;

import com.sutandi.data.api.dto.request.CustomerDialogRequest;
import com.sutandi.data.api.dto.response.CustomerDialogResponse;
import com.sutandi.data.api.dto.response.mapper.CustomerDialogResponseMapper;
import com.sutandi.data.api.repositories.DialogRepository;
import com.sutandi.data.api.repositories.mappers.DialogEntityMapper;
import com.sutandi.data.api.services.DialogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.sutandi.data.api.repositories.DialogSpecification.*;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.toUnmodifiableList;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
public class DialogServiceImpl implements DialogService {

    private final DialogRepository repository;

    @Transactional
    public List<CustomerDialogResponse> findAll(Optional<String> language,
                                                Optional<Long> customerId,
                                                Integer pageNo,
                                                Integer pageSize) {
        var specification = where(isConsent());
        var pageable = PageRequest.of(pageNo, pageSize, Sort.by("timestamp").descending());

        if (language.isPresent()) {
            specification = specification.and(hasLanguage(language.get()));
        }
        if (customerId.isPresent()) {
            specification = specification.and(hasCustomerId(customerId.get()));
        }

        return repository.findAll(specification, pageable)
                .stream()
                .map(CustomerDialogResponseMapper::fromEntity)
                .collect(toUnmodifiableList());
    }

    @Transactional
    public void insert(Long dialogId,
                       Long customerId,
                       CustomerDialogRequest customerDialogRequest) {
        repository.save(DialogEntityMapper.from(dialogId, customerId, customerDialogRequest));
    }

    @Transactional
    public void updateConsent(Long dialogId,
                              Boolean consent) {
        if (TRUE.equals(consent)) {
            updateConsentFor(dialogId);
        } else {
            deleteByDialogId(dialogId);
        }
    }

    private void updateConsentFor(Long dialogId) {
        repository.updateConsentForDialogId(dialogId);
    }

    private void deleteByDialogId(Long dialogId) {
        repository.deleteByDialogId(dialogId);
    }
}
