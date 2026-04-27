package com.example.quicktask.controllers;

import com.example.quicktask.common.CommonResponseBean;
import com.example.quicktask.dtos.TaskRequestDTO;
import com.example.quicktask.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/gettask")
    CommonResponseBean getTask(@RequestBody TaskRequestDTO dto) {
        return taskService.getTask(dto);
    }
    @PostMapping("/createtask")
    CommonResponseBean createTask(@RequestBody TaskRequestDTO dto) {
        return taskService.createTask(dto);
    }
}
