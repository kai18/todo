package com.kaustubh.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tb_todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer todoId;
    private String title;
    @Column(name = "user_id_fk")
    private Integer userId;
    @JsonIgnore
    private Boolean isDeleted = Boolean.FALSE;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "tb_todo_task",
            joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> tasks;

}
