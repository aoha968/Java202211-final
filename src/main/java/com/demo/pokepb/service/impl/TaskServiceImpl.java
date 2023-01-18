package com.demo.pokepb.service.impl;

import com.demo.pokepb.entity.Task;
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
    public Task findIdTask(int id) {
       return taskMapper.findIdTask(id);
    }

    @Override
    public int updateIdTask(int id, String detail) {
        return taskMapper.updateIdTask(id, detail);
    }

    @Override
    public boolean deleteIdTask(int id, String detail) {
        return taskMapper.deleteIdTask(id, detail);
    }

    @Override
    public boolean registerTask(String detail) {
        return taskMapper.registerTask(detail);
    }
}
