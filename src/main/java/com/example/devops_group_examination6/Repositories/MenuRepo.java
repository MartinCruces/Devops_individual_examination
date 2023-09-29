package com.example.devops_group_examination6.Repositories;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class MenuRepo {

    public  Map<Integer, String> getMenuMap() {
        return menuMap;
    }

    public Map<Integer, String> menuMap = new HashMap<>();

}
