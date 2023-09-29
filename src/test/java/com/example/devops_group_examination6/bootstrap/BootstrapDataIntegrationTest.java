package com.example.devops_group_examination6.bootstrap;

import com.example.devops_group_examination6.Repositories.MenuRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BootstrapDataIntegrationTest {

    MenuRepo menuRepo;

    @Autowired
    public BootstrapDataIntegrationTest(MenuRepo menuRepo) {

        this.menuRepo = menuRepo;
    }
    @Test
    public void repoHasDataTest() {

        assertThat(menuRepo.menuMap).isNotEmpty();
    }

    @Test
    public void repoFirstIterationIsMondayTest() {
        String monday = menuRepo.menuMap.get(0).toUpperCase();
        boolean containsMonday = monday.contains("MONDAY");

        assertTrue(containsMonday);
    }

    @Test
    public void repoDataIsLongEnoughTest() {

        assertEquals(7, menuRepo.menuMap.size());
    }


}