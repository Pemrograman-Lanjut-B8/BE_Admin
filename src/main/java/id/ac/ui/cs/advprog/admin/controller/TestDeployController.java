package id.ac.ui.cs.advprog.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDeployController{
    @GetMapping("/")
    public String index() {
        return "HelloWorld";
    }
}