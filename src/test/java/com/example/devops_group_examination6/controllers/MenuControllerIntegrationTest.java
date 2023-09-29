package com.example.devops_group_examination6.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import com.example.devops_group_examination6.Repositories.MenuRepo;
import com.example.devops_group_examination6.service.MenuManipulator;

@SpringBootTest
@AutoConfigureMockMvc
public class MenuControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private MenuManipulator menuManipulator;

    @Test
    public void testGetWeeklyMenu() throws Exception {
        String expectedMenu = menuRepo.menuMap.toString();

        mockMvc.perform(get("/menu"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedMenu));
    }

    @Test
    public void testGetTodaysMenu() throws Exception {
        String todaysDate = LocalDate.now().getDayOfWeek().toString();
        String expectedTodaysMenu = menuManipulator.checkTodaysMenu(todaysDate);

        mockMvc.perform(get("/menu/today"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedTodaysMenu));
    }
}