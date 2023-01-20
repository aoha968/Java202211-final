package com.demo.pokepb.service;

import com.demo.pokepb.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllTask();

    Task findIdTask(int id);

    int updateIdTask(int id, String detail);

    boolean deleteIdTask(int id);

    boolean registerTask(String detail);
}
