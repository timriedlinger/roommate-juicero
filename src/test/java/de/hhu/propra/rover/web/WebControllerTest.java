package de.hhu.propra.rover.web;

import de.hhu.propra.rover.configuration.MethodSecurityConfiguration;
import de.hhu.propra.rover.helper.WithMockOAuth2User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(MethodSecurityConfiguration.class)
class WebControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void GameWithoutLogin() throws Exception {
        mockMvc.perform(get("/game"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockOAuth2User(login="something")
    void GameWithLogin() throws Exception {
        mockMvc.perform(get("/game"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2User(login="something", roles = {"USER"})
    void AdminWithoutRole() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockOAuth2User(login="something", roles = {"USER", "ADMIN"})
    void AdminWithRole() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }


}