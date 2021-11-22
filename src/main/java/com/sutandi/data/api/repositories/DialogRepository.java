package com.sutandi.data.api.repositories;

import com.sutandi.data.api.repositories.entities.DialogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DialogRepository extends JpaRepository<DialogEntity, Long> {
    Page<DialogEntity> findAll(Specification<DialogEntity> specification, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE dialog d SET CONSENT=true where d.DIALOG_ID=:dialogId", nativeQuery = true)
    void updateConsentForDialogId(@Param("dialogId") Long dialogId);

    @Modifying
    @Query(value = "DELETE FROM dialog d where d.DIALOG_ID=:dialogId", nativeQuery = true)
    void deleteByDialogId(@Param("dialogId") Long dialogId);
}
