package com.example.devops_group_examination6.bootstrap;

import com.example.devops_group_examination6.Repositories.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class BootstrapData implements CommandLineRunner {

    String filePath = "/documents/menu.txt";
    MenuRepo menuRepo;

    @Autowired
    public BootstrapData(MenuRepo menuRepo) {
        this.menuRepo = menuRepo;
    }



    public void menuReader() {
        try (InputStream inputStream = getClass().getResourceAsStream(filePath)) {
            assert inputStream != null;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

                bufferedReader.lines()
                        .forEachOrdered(line -> menuRepo.getMenuMap().put(menuRepo.getMenuMap().size(), line));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void run(String... args) {
        menuReader();
    }
}

