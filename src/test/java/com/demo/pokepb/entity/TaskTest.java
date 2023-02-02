package com.demo.pokepb.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
public class TaskTest {
    @Test
    void タスククラスを作成してgetメソッド実行() {
        Task task = new Task(1, "detail");
        assertEquals(task.getId(), 1);
        assertEquals(task.getDetail(), "detail");
    }
}
