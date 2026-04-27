package com.example.quicktask.serviceImpls;


import com.example.quicktask.common.CommonResponseBean;
import com.example.quicktask.dtos.TaskRequestDTO;
import com.example.quicktask.services.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public CommonResponseBean createTask(TaskRequestDTO dto) {
        try {
            System.out.println(dto);
        } catch (Exception e) {
            System.out.println("{ }"+ e);
        }
        return new CommonResponseBean(false, 500, "Internal server Error", null);
    };
    public CommonResponseBean getTask(TaskRequestDTO dto) {
        try {
            System.out.println(dto);
        } catch (Exception e) {
            System.out.println("{ }"+ e);
        }
        return new CommonResponseBean(false, 500, "Internal server Error", null);
    }
}
