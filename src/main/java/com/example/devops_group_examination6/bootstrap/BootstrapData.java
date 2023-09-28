package com.example.devops_group_examination6.bootstrap;

import com.example.devops_group_examination6.Repositories.MenuRepo;
import com.example.devops_group_examination6.service.MenuManipulatorImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class BootstrapData implements CommandLineRunner {

    String filePath = "src/main/resources/documents/menu.txt";
    MenuRepo menuRepo;

    @Autowired
    public BootstrapData(MenuRepo menuRepo, MenuManipulatorImplements menuManipulatorImplements) {
        this.menuRepo = menuRepo;
    }


    public void menuReader() {

        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            bufferedReader.lines()
                    .forEachOrdered(line -> menuRepo.menuMap.put(menuRepo.menuMap.size(), line));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run(String... args) {
        menuReader();
    }
}
