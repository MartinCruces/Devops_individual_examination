package com.example.devops_group_examination6.controllers;

import com.example.devops_group_examination6.Repositories.MenuRepo;
import com.example.devops_group_examination6.service.MenuManipulator;
import com.example.devops_group_examination6.service.MenuManipulatorImplements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MenuControllerTest {
    @MockBean
    private MenuRepo menuRepo;

    private final MockMvc mockMvc;

    private MenuController menuController;
    @MockBean
    private MenuManipulator menuManipulator;

    @Autowired
    public MenuControllerTest(MenuRepo menuRepo, MockMvc mockMvc, MenuManipulator menuManipulator, MenuController menuController) {
        this.menuRepo = menuRepo;
        this.mockMvc = mockMvc;
        this.menuManipulator = menuManipulator;
        this.menuController = menuController;
    }

    @Test
    public void getTodaysMenuTest() throws Exception {
        String todaysDate = LocalDate.now().getDayOfWeek().toString();
        String todaysMenu = menuManipulator.checkTodaysMenu(todaysDate);
        mockMvc.perform(get("localhost:8080/menu/today")).andExpect(status().isOk()).andExpect(content().string(todaysMenu));
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
}