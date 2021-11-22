package com.sutandi.data.api.controllers;

import com.google.gson.reflect.TypeToken;
import com.sutandi.data.api.dto.response.GenericResponse;
import com.sutandi.data.api.services.DialogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.sutandi.data.api.TestUtil.OBJECT_MAPPER;
import static com.sutandi.data.api.TestUtil.execute;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class ConsentControllerTest {
    private MockMvc mockMvc;
    @Mock
    private DialogService dialogService;
    @InjectMocks
    private ConsentController consentController;

    private static final String CONSENT_PATH = "/consents";
    private static final Long DIALOG_ID = Long.valueOf(19);

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(consentController).build();
    }

    @Test
    void postTrueConsent() throws Exception {
        postConsent(Boolean.TRUE);
    }

    @Test
    void postFalseConsent() throws Exception {
        postConsent(Boolean.FALSE);
    }

    private void postConsent(Boolean consent) throws Exception {
        TypeToken<GenericResponse> typeToken = new TypeToken<>() {
        };

        var responseDTO = execute(mockMvc, post(String.join("/", CONSENT_PATH, DIALOG_ID.toString())), OBJECT_MAPPER.writeValueAsString(consent), typeToken);

        verify(dialogService).updateConsent(DIALOG_ID, consent);
        assertThat(responseDTO.getStatus()).isEqualTo(OK.value());
        assertThat(responseDTO.getMessage()).isNotBlank();
    }
}
