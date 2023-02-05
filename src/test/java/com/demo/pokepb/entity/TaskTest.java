package com.demo.pokepb.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskTest {
    @Test
    @DisplayName("正常系：タスククラスを作成してgetメソッド実行")
    void Create_a_task_class_and_execute_get_method() {
        Task task = new Task(1, "detail");
        assertEquals(task.getId(), 1);
        assertEquals(task.getDetail(), "detail");
    }
}
