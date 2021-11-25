package com.sutandi.data.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Generic response class
 * To display message after controller's action
 */
@Data
@AllArgsConstructor
public class GenericResponse {
    private int status;
    private String message;
}
