
package com.example.sellit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    public TestController() {
    }

    @GetMapping({"/test"})
    public String test() {
        return "xdxddds";
    }
}




