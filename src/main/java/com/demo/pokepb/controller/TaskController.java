package com.demo.pokepb.controller;

import com.demo.pokepb.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /***
     * ログイン後のタスク一覧表示画面
     */
    @GetMapping("/task/tasks")
    public String registerAllTask(Model model) {
        model.addAttribute("tasks", taskService.findAllTask());
        return "task/tasks";
    }
}
