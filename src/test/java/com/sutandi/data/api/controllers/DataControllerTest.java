package com.sutandi.data.api.controllers;

import com.google.gson.reflect.TypeToken;
import com.sutandi.data.api.dto.request.CustomerDialogRequest;
import com.sutandi.data.api.dto.response.CustomerDialogResponse;
import com.sutandi.data.api.dto.response.GenericResponse;
import com.sutandi.data.api.services.DialogService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static com.sutandi.data.api.TestUtil.OBJECT_MAPPER;
import static com.sutandi.data.api.TestUtil.execute;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class DataControllerTest {
    private MockMvc mockMvc;
    @Mock
    private DialogService dialogService;
    @InjectMocks
    private DataController dataController;

    private static final String DATA_PATH = "/data";
    private static final Long CUSTOMER_ID = 19L;
    private static CustomerDialogResponse CUSTOMER_DIALOG_RESPONSE;

    @BeforeAll
    static void beforeClass() {
        CUSTOMER_DIALOG_RESPONSE = CustomerDialogResponse.builder()
                .customerId(CUSTOMER_ID)
                .build();
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(dataController).build();
    }

    @Test
    void getCustomerDialog() throws Exception {
        List<CustomerDialogResponse> responses = of(CUSTOMER_DIALOG_RESPONSE);

        when(dialogService.findAll(Mockito.<Optional<String>>any(), Mockito.<Optional<Long>>any(), anyInt(), anyInt()))
                .thenReturn(responses);

        TypeToken<List<CustomerDialogResponse>> typeToken = new TypeToken<>() {
        };

        var responseDTO = execute(mockMvc, get(String.join("/", DATA_PATH)), "", typeToken);

        verify(dialogService).findAll(Mockito.<Optional<String>>any(), Mockito.<Optional<Long>>any(), anyInt(), anyInt());
        assertThat(responseDTO).hasSize(1);
        assertThat(responseDTO.get(0).getCustomerId()).isEqualTo(CUSTOMER_ID);
    }

    @Test
    void getParameterizedCustomerDialog() throws Exception {
        List<CustomerDialogResponse> responses = of(CUSTOMER_DIALOG_RESPONSE);

        var queryLanguage = Optional.of("EN");
        var queryCustomerId = Optional.of(CUSTOMER_ID);
        var queryPageNo = Integer.valueOf(1);
        var queryPageSize = Integer.valueOf(10);

        when(dialogService.findAll(queryLanguage, queryCustomerId, queryPageNo, queryPageSize))
                .thenReturn(responses);

        TypeToken<List<CustomerDialogResponse>> typeToken = new TypeToken<>() {
        };
        var queryString = "?language=EN&customerId=" + CUSTOMER_ID + "&pageNo=1&pageSize=10";

        var responseDTO = execute(mockMvc, get(String.join("/", DATA_PATH) + queryString), "", typeToken);

        verify(dialogService).findAll(queryLanguage, queryCustomerId, queryPageNo, queryPageSize);
        assertThat(responseDTO).hasSize(1);
        assertThat(responseDTO.get(0).getCustomerId()).isEqualTo(CUSTOMER_ID);
    }

    @Test
    void postNewCustomer() throws Exception {
        var dialogId = Long.valueOf(1);
        var customerId = Long.valueOf(1);
        var text = "some text";
        var language = "some language";
        var customerDialogRequest = new CustomerDialogRequest();
        customerDialogRequest.setText(text);
        customerDialogRequest.setLanguage(language);

        TypeToken<GenericResponse> typeToken = new TypeToken<>() {
        };

        var responseDTO = execute(mockMvc, post(String.join("/", DATA_PATH, customerId.toString(), dialogId.toString())), OBJECT_MAPPER.writeValueAsString(customerDialogRequest), typeToken);

        verify(dialogService).insert(dialogId, customerId, customerDialogRequest);
        assertThat(responseDTO.getStatus()).isEqualTo(OK.value());
        assertThat(responseDTO.getMessage()).isNotBlank();
    }
}
