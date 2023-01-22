package com.demo.pokepb.controller;

import com.demo.pokepb.entity.Task;
import com.demo.pokepb.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /***
     * ログイン後のタスク追加画面に遷移するためのリンク
     */
    @RequestMapping("/task/register")
    public String displayRegisterTask() {
        return "task/register";
    }

    /***
     * ログイン後のタスク追加画面
     */
    @RequestMapping(value = "/task/add", method = RequestMethod.POST)
    public String registerTask(@RequestParam String addText) {
        return taskService.registerTask(addText);
    }

    /***
     * ログイン後のタスク詳細画面
     */
    @GetMapping("/task/tasks/{id}")
    public String detailEditTask(@PathVariable("id") int id, Model model) {
        return taskService.findIdTask(id, "details", model);
    }

    /***
     * ログイン後のタスク編集画面
     */
    @GetMapping("/task/tasks/{id}/edit")
    public String editTask(@PathVariable("id") int id, Model model) {
        return taskService.findIdTask(id, "edit", model);
    }

    /***
     * ログイン後のタスク編集の更新確定
     * 成功すれば詳細画面へ戻る
     * 失敗の場合は編集画面へ戻る
     */
    @RequestMapping(value = "/task/tasks/update", method = RequestMethod.POST)
    public String updateTask(@Validated @ModelAttribute Task task) {
        return taskService.updateIdTask(task.getId(), task.getDetail());
    }

    @RequestMapping(value = "/task/tasks/delete/{id}", method = RequestMethod.POST)
    public String deleteTask(@PathVariable("id") int id) {
        return taskService.deleteIdTask(id);
    }
}
