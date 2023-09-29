package com.example.devops_group_examination6.controllers;

import com.example.devops_group_examination6.Repositories.MenuRepo;
import com.example.devops_group_examination6.service.MenuManipulator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest
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
        String expectedMenu = "Todays Menu";
        when(menuManipulator.checkTodaysMenu(todaysDate)).thenReturn(expectedMenu);

        mockMvc.perform(get("/menu/today"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedMenu));
    }
    @Test
    public void getWeekMenuTest() throws Exception {
        Map<Integer, String> expectedMenu = new HashMap<>();
        expectedMenu.put(0,"Monday");
        expectedMenu.put(1,"Tuesday");
        when(menuRepo.getMenuMap()).thenReturn(expectedMenu);
        String expectedMenuString = expectedMenu.toString();
        mockMvc.perform(get("/menu"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedMenuString));
    }
}
