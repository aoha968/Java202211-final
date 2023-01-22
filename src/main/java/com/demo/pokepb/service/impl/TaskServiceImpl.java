package com.demo.pokepb.service.impl;

import com.demo.pokepb.entity.Task;
import com.demo.pokepb.exception.MyException;
import com.demo.pokepb.mapper.TaskMapper;
import com.demo.pokepb.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskMapper taskMapper;

    public TaskServiceImpl(TaskMapper taskMapper) { this.taskMapper = taskMapper; }

    @Override
    public List<Task> findAllTask() {
        return taskMapper.findAllTask();
    }

    @Override
    public String findIdTask(int id, String request, Model model) {
        Task retVal;
        try {
            retVal = taskMapper.findIdTask(id);
            if(retVal == null) {
                throw new MyException("想定外のidが指定されました");
            } else {
                model.addAttribute(request, retVal);
                return "task/" + request;
            }
        } catch (MyException e) {
            return "failsafe/failsafe";
        }
    }

    @Override
    public String updateIdTask(int id, String detail) {
        int retVal = 0;
        try {
            retVal = taskMapper.updateIdTask(id, detail);
            if(retVal != 1) {
                throw new MyException("更新処理失敗");
            } else {
                return "redirect:/task/tasks/" + id;
            }
        } catch(MyException e){
            return "failsafe/failsafe";
        }
    }

    @Override
    public String deleteIdTask(int id) {
        boolean retVal;
        retVal = taskMapper.deleteIdTask(id);
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

    @Override
    public String registerTask(String detail) {
        boolean retVal = taskMapper.registerTask(detail);
        try {
            if(retVal == false){
                throw new MyException("登録に失敗しました");
            }
            /* 追加成功した場合はタスク一覧画面に遷移 */
            return "redirect:/task/tasks";
        } catch (MyException e) {
            return "failsafe/failsafe";
        }
    }
}
