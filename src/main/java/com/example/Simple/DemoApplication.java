package com.example.Simple;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApplication {

  @GetMapping("/getData")
  public String getData() {
    return "Hello, World! pavan from insight global!";
  }
}
