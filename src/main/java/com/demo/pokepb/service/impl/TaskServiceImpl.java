package com.demo.pokepb.service.impl;

import com.demo.pokepb.entity.Task;
import com.demo.pokepb.exception.MyException;
import com.demo.pokepb.mapper.TaskMapper;
import com.demo.pokepb.service.TaskService;
import org.springframework.stereotype.Service;

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
    public Task findTaskById(int id) {
        return taskMapper.findIdTask(id);
    }

    @Override
    public int updateTaskById(int id, String detail) {
        return taskMapper.updateIdTask(id, detail);
    }

    @Override
    public boolean deleteTaskById(int id) {
        return taskMapper.deleteIdTask(id);
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
