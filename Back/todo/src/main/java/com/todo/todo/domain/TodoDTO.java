package com.todo.todo.domain;

import lombok.Data;

/**
 * Descripción: Manejo de datos
 * */
@Data
public class TodoDTO {
    private Long id;
    private Boolean aBoolean;
    private String tarea;
}