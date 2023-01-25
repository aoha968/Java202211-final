package com.demo.pokepb.service;

import com.demo.pokepb.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllTask();

    Task findTaskById(int id);

    int updateTaskById(int id, String detail);

    boolean deleteTaskById(int id);

    String registerTask(String detail);
}
