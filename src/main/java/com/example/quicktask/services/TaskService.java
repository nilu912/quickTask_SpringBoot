package com.example.quicktask.services;

import com.example.quicktask.common.CommonResponseBean;
import com.example.quicktask.dtos.TaskRequestDTO;

public interface TaskService {
    CommonResponseBean createTask(TaskRequestDTO dto);
    CommonResponseBean getTask(TaskRequestDTO dto);

}
