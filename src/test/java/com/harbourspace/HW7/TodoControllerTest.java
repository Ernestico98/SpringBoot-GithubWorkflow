package com.harbourspace.HW7;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void indexMehtodShouldRespondsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todo")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1)) // check first element has id = 1
                .andExpect(MockMvcResultMatchers.status().isOk()); // check response is 200
    }

    @Test
    public void showMehtodShouldRespondsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todo/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1)) // check first element has id = 1
                .andExpect(MockMvcResultMatchers.status().isOk()); // check response is 200
    }


    @Test
    public void showMehtodShouldResturnEmptyResponseAndNotFoundIfNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todo/10")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }


    @Test
    public void sotoreMethodShuldReturnOk_And_ReturnNewItem() throws Exception {
        String requestBody = "{\"id\":7,\"userId\":2,\"companyId\":4,\"description\":\"item1\",\"deadline\":\"20/04/2023\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/todo")
                                              .content(requestBody)
                                              .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(requestBody));
    }

}