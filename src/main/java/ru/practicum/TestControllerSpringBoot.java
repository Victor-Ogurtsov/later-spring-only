package ru.practicum;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test1")
public class TestControllerSpringBoot {

    @GetMapping
    public String test() {
        return "OK from controller with Spring boot!";
    }
}
