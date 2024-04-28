package com.seungilahn.todolist.application.port.out;

import com.seungilahn.todolist.domain.ToDo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoadToDoPort {

    ToDo loadById(Long doId);

    List<ToDo> findToDos(Long userId, Pageable pageable);
    List<ToDo> findToDosBeforeCursor(Long userId, Long cursor, Pageable pageable);
    List<ToDo> findToDosAfterCursor(Long userId, Long cursor, Pageable pageable);

    Long countToDos(Long userId);
}
