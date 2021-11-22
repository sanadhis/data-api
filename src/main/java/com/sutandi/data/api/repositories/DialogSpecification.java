package com.sutandi.data.api.repositories;

import com.sutandi.data.api.repositories.entities.DialogEntity;
import org.springframework.data.jpa.domain.Specification;

public enum DialogSpecification {
    ;

    public static Specification<DialogEntity> isConsent() {
        return ((dialog, query, criteriaBuilder) -> criteriaBuilder.equal(dialog.get("consent"), Boolean.TRUE));
    }

    public static Specification<DialogEntity> hasCustomerId(Long customerId) {
        return ((dialog, query, criteriaBuilder) -> criteriaBuilder.equal(dialog.get("customerId"), customerId));
    }

    public static Specification<DialogEntity> hasLanguage(String language) {
        return ((dialog, query, criteriaBuilder) -> criteriaBuilder.equal(dialog.get("language"), language));
    }
}
