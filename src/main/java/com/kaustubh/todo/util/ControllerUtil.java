package com.kaustubh.todo.util;

import com.kaustubh.todo.model.Task;
import com.kaustubh.todo.model.Todo;
import com.kaustubh.todo.model.request.TaskRequest;
import com.kaustubh.todo.model.request.TodoRequest;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ControllerUtil {
    public static Todo copyToTodoFromRequest(Integer userId, TodoRequest request) {

        Todo todo = new Todo();
        todo.setUserId(userId);
        List<Task> tasks = new ArrayList<>();
        todo.setTasks(tasks);
        if (request != null && todo.getTodoId() == null) {
            BeanUtils.copyProperties(request, todo, "tasks");
            if (request.getTasks() != null) {
                for (TaskRequest taskRequest : request.getTasks()) {
                    Task task = new Task();
                    BeanUtils.copyProperties(taskRequest, task);
                    tasks.add(task);
                }
            }
        }
        return todo;
    }
}
