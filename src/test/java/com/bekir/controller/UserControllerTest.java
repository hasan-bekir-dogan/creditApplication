package com.bekir.controller;

import com.bekir.ui.rest.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bekir.business.dto.UserSaveDto;
import com.bekir.business.services.UserServices;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    private final static String CONTENT_TYPE = "application/json";


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServices userServices;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        // given
        UserSaveDto user = UserSaveDto.builder().name("Test-Name").surname("Test-Surname").build();

        // when
        ResultActions actions = mockMvc.perform(post("/users")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(user)));

        // then
        ArgumentCaptor<UserSaveDto> captor = ArgumentCaptor.forClass(UserSaveDto.class);
        verify(userServices, times(1)).createUser(captor.capture());
        assertThat(captor.getValue().getName()).isEqualTo("Test-Name");
        assertThat(captor.getValue().getSurname()).isEqualTo("Test-Surname");
        actions.andExpect(status().isOk());
    }
}
