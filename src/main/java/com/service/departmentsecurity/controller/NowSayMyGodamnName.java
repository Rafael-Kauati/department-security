package com.service.departmentsecurity.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NowSayMyGodamnName {

    @GetMapping("/NowSayMyGodamnName")
    public String NowSayMyGodamnName(){
        return "Heisenberg";
    }

}
