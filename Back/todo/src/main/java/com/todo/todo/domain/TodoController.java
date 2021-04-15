package com.todo.todo.domain;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class TodoController {

    @Autowired
    private TodoService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "todos/todos")
    @ResponseBody
    public List<TodoDTO> list(){
        List<Todo> todos = service.list();
        return todos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping(value = "todos/todos")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TodoDTO save(@Valid @RequestBody TodoDTO todoDto) throws ParseException {
        Todo todo = convertToEntity(todoDto);
        Todo todoSaved = service.save(todo);
        return convertToDto(todoSaved);
    }


    @DeleteMapping(value = "todos/{id}/todos")
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }

    @GetMapping(value = "todos/{id}/todos")
    @ResponseBody
    public TodoDTO get(@PathVariable("id") Long id){
        return convertToDto(service.get(id)) ;
    }


    private TodoDTO convertToDto(Todo todo) {
        return modelMapper.map(todo, TodoDTO.class);
    }


    private Todo convertToEntity(TodoDTO todoDto) throws ParseException {
        return modelMapper.map(todoDto, Todo.class);
    }
}