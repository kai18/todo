package com.kaustubh.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;
    private String text;
    private Boolean isComplete = Boolean.FALSE;
    private Boolean isDeleted = Boolean.FALSE;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinTable(name = "tb_todo_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "todo_id"))
    private Todo todo;

}
