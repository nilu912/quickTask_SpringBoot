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
@RequestMapping("/api/product")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/getproduct")
    CommonResponseBean getProducts(@RequestBody TaskRequestDTO dto) {
        return taskService.getTask(dto);
    }
    @PostMapping("/setproduct")
    CommonResponseBean addProduct(@RequestBody TaskRequestDTO dto) {
        return taskService.addTask(dto);
    }
}
