package com.sutandi.data.api.controllers;

import com.sutandi.data.api.dto.request.CustomerDialogRequest;
import com.sutandi.data.api.dto.response.CustomerDialogResponse;
import com.sutandi.data.api.dto.response.GenericResponse;
import com.sutandi.data.api.services.DialogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class DataController {

    private final DialogService dialogService;

    @GetMapping
    public ResponseEntity<List<CustomerDialogResponse>> getCustomerDialog(@RequestParam Optional<String> language,
                                                                          @RequestParam Optional<Long> customerId,
                                                                          @RequestParam(defaultValue = "0") Integer pageNo,
                                                                          @RequestParam(defaultValue = "50") Integer pageSize) {
        return ResponseEntity.ok(dialogService.findAll(language, customerId, pageNo, pageSize));
    }

    @PostMapping("/{customerId}/{dialogId}")
    public ResponseEntity<GenericResponse> newCustomerDialog(@PathVariable Long dialogId,
                                                             @PathVariable Long customerId,
                                                             @RequestBody CustomerDialogRequest customerDialogRequest) {
        dialogService.insert(dialogId, customerId, customerDialogRequest);
        return ResponseEntity.ok(new GenericResponse(OK.value(), "New dialog from customer " + customerId + " has been stored"));
    }
}
