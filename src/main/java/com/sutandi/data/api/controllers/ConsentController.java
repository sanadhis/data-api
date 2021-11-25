package com.sutandi.data.api.controllers;

import com.sutandi.data.api.dto.response.GenericResponse;
import com.sutandi.data.api.services.DialogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.Boolean.TRUE;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/consents")
@RequiredArgsConstructor
public class ConsentController {

    private final DialogService dialogService;

    @PostMapping("/{dialogId}")
    public ResponseEntity<GenericResponse> postConsent(@PathVariable Long dialogId,
                                                       @RequestBody Boolean consent) {
        dialogService.updateConsent(dialogId, consent);
        return ResponseEntity.ok(new GenericResponse(OK.value(),
                "Consent is updated successfully: this dialog with id=" + dialogId + (TRUE.equals(consent) ?
                        " will be used to improve our chatbot" :
                        " will be deleted from our database")
        ));
    }
}
