package com.demo.pokepb.service;

import com.demo.pokepb.entity.Task;
import org.springframework.ui.Model;

import java.util.List;

public interface TaskService {
    List<Task> findAllTask();

    String findIdTask(int id, String request, Model model);

    String updateIdTask(int id, String detail);

    String deleteIdTask(int id);

    String registerTask(String detail);
}
