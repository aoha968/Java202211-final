package com.demo.pokepb.controller;

import com.demo.pokepb.entity.Task;
import com.demo.pokepb.exception.MyException;
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
    @RequestMapping(value = "/task/tasks", method = RequestMethod.GET)
    public String registerAllTask(Model model) {
        model.addAttribute("tasks", taskService.findAllTask());
        return "task/tasks";
    }

    /***
     * ログイン後のタスク追加画面に遷移するためのリンク
     */
    @RequestMapping(value = "/task/register", method = RequestMethod.GET)
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
    @RequestMapping(value = "/task/tasks/{id}", method = RequestMethod.GET)
    public String detailEditTask(@PathVariable("id") int id, Model model) {
        Task retVal;
        try {
            retVal = taskService.findTaskById(id);
            if(retVal == null) {
                throw new MyException("想定外のidが指定されました");
            } else {
                model.addAttribute("details", retVal);
                return "task/details";
            }
        } catch (MyException e) {
            return "failsafe/failsafe";
        }
    }

    /***
     * ログイン後のタスク編集画面
     */
    @RequestMapping(value = "/task/tasks/{id}/edit", method = RequestMethod.GET)
    public String editTask(@PathVariable("id") int id, Model model) {
        Task retVal;
        try {
            retVal = taskService.findTaskById(id);
            if(retVal == null) {
                throw new MyException("想定外のidが指定されました");
            } else {
                model.addAttribute("edit", retVal);
                return "task/edit";
            }
        } catch (MyException e) {
            return "failsafe/failsafe";
        }
    }

    /***
     * ログイン後のタスク編集の更新確定
     * 成功すれば詳細画面へ戻る
     * 失敗の場合は編集画面へ戻る
     */
    @RequestMapping(value = "/task/tasks/update", method = RequestMethod.POST)
    public String updateTask(@Validated @ModelAttribute Task task) {
        int retVal;
        try {
            retVal = taskService.updateTaskById(task.getId(), task.getDetail());
            if(retVal != 1) {
                throw new MyException("更新処理失敗");
            } else {
                return "redirect:/task/tasks/" + task.getId();
            }
        } catch(MyException e){
            return "failsafe/failsafe";
        }
    }

    /***
     * ログイン後のタスク削除
     * 成功すれば一覧画面へ戻る
     * 失敗の場合は編集画面へ戻る
     */
    @RequestMapping(value = "/task/tasks/delete/{id}", method = RequestMethod.POST)
    public String deleteTask(@PathVariable("id") int id) {
        boolean retVal;
        retVal = taskService.deleteTaskById(id);
        try {
            if(retVal == false){
                throw new MyException("削除処理失敗");
            }else {
                /* 更新成功した場合はタスク一覧画面に遷移 */
                return "redirect:/task/tasks";
            }
        } catch (MyException e) {
            return "failsafe/failsafe";
        }
    }
}
