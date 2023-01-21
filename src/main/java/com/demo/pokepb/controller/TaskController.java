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
        boolean result;
        result = taskService.registerTask(addText);
        if(result == true) {
            /* 追加成功した場合はタスク一覧画面に遷移 */
            return "redirect:/task/tasks";
        }else{
            /* 追加失敗した場合は追加画面を再度表示させる */
            return "failsafe/failsafe";
        }
    }

    /***
     * ログイン後のタスク詳細画面
     */
    @GetMapping("/task/tasks/{id}")
    public String detailEditTask(@PathVariable("id") int id, Model model) {
        model.addAttribute("details", taskService.findIdTask(id));
        return "task/details";
    }

    /***
     * ログイン後のタスク編集画面
     */
    @GetMapping("/task/tasks/{id}/edit")
    public String editTask(@PathVariable("id") int id, Model model) {
        model.addAttribute("edit", taskService.findIdTask(id));
        return "task/edit";
    }

    /***
     * ログイン後のタスク編集の更新確定
     * 成功すれば詳細画面へ戻る
     * 失敗の場合は編集画面へ戻る
     */
    @RequestMapping(value = "/task/tasks/update", method = RequestMethod.POST)
    public String updateTask(@Validated @ModelAttribute Task task) {
        // タスクの更新
        int update = taskService.updateIdTask(task.getId(), task.getDetail());
        if(update == 1){
            /* 更新成功した場合はタスク詳細画面に遷移 */
            return "redirect:/task/tasks/" + task.getId();
        }else{
            /* 更新失敗した場合 */
            return "failsafe/failsafe";
        }
    }

    @GetMapping("/task/tasks/delete/{id}")
    public String deleteTask(@PathVariable("id") int id) {
        boolean result;
        result = taskService.deleteIdTask(id);
        if(result == true){
            /* 更新成功した場合はタスク一覧画面に遷移 */
            return "redirect:/task/tasks";
        } else {
            /* 更新失敗した場合はエラー画面に遷移 */
            return "failsafe/failsafe";
        }

    }
}
