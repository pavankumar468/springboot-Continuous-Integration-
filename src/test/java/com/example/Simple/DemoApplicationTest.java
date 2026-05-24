package com.example.Simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoApplicationTest {

    @Test
    void getData() {
        DemoApplication demoApplication = new DemoApplication();
        String result = demoApplication.getData();
        assertEquals("Hello, World! pavan from insight global!", result);
    }

}