package com.sutandi.data.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.google.gson.JsonParser.parseString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public enum TestUtil {
    ;

    public static final Gson GSON = new GsonBuilder().create();
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T execute(MockMvc mockMvc, MockHttpServletRequestBuilder requestBuilder, String payload, TypeToken<T> typeToken) throws Exception {
        var jsonResponse = getResponse(mockMvc, requestBuilder, payload);
        return GSON.fromJson(jsonResponse, typeToken.getType());
    }

    public static JsonElement getResponse(MockMvc mockMvc, MockHttpServletRequestBuilder mockHttpServletRequestBuilder, String payload) throws Exception {
        return parseString(mockMvc.perform(mockHttpServletRequestBuilder
                        .contentType(APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString());
    }
}
