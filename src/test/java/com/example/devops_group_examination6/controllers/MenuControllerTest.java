package com.example.devops_group_examination6.controllers;

import com.example.devops_group_examination6.Repositories.MenuRepo;
import com.example.devops_group_examination6.service.MenuManipulator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(MenuController.class)
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuRepo menuRepo;

    @MockBean
    private MenuManipulator menuManipulator;

    @Test
    public void getTodaysMenuTest() throws Exception {
        String todaysDate = LocalDate.now().getDayOfWeek().toString();
        String expectedMenu = menuManipulator.checkTodaysMenu(todaysDate);  // Sample data for our mock
        when(menuManipulator.checkTodaysMenu(todaysDate)).thenReturn(expectedMenu);

        mockMvc.perform(get("/menu/today"))
                .andExpect(status().isOk())
                .andExpect(content().string("Sample Menu for Today"));
    }
}
//
//    @Test
//    void listBeersByStyleAndName() throws Exception {
//        mockMvc.perform(get(BeerController.BEER_PATH)
//                        .with(BeerControllerTest.jwtRequestPostProcessor)
//                        .queryParam("beerName", "IPA")
//                        .queryParam("beerStyle", BeerStyle.IPA.name())
//                        .queryParam("pageSize", "800"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content.size()", is(310)));
