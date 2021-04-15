package com.todo.todo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class TodoService{

    @Autowired
    private TodoRepository repository;

    public List<Todo> list(){ return repository.findAll(); }

    public Todo save(Todo todo){ return repository.save(todo); }

    public void delete(Long id){
        repository.delete(get(id));
    }

    public Todo get(Long id){
         return repository.findById(id).orElseThrow();
    }

}