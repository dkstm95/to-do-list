package com.seungilahn.todolist.application.service;

import com.seungilahn.todolist.application.port.in.GetToDoListQuery;
import com.seungilahn.todolist.application.port.in.GetToDoListResponse;
import com.seungilahn.todolist.application.port.out.LoadToDoPort;
import com.seungilahn.todolist.domain.SortType;
import com.seungilahn.todolist.domain.ToDo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class GetToDoListService implements GetToDoListQuery {

    private final LoadToDoPort loadToDoPort;

    GetToDoListService(LoadToDoPort loadToDoPort) {
        this.loadToDoPort = loadToDoPort;
    }

    @Override
    public GetToDoListResponse getToDoList(Long userId, Long cursor, int limit, SortType sortType) {

        Pageable pageable = generatePageableInfo(limit, sortType);
        List<ToDo> toDos = getToDos(userId, cursor, sortType, pageable);

        Long totalCount = loadToDoPort.countToDos(userId);

        String nextCursor = toDos.size() == limit + 1 ? String.valueOf(toDos.get(limit).getId()) : null;

        List<ToDo> toDosData = toDos.subList(0, Math.min(toDos.size(), limit));

        return new GetToDoListResponse(totalCount, nextCursor, toDosData);
    }

    private Pageable generatePageableInfo(int limit, SortType sortType) {
        if (sortType == SortType.ASC) {
            return PageRequest.of(0, limit + 1, Sort.by("id").ascending());
        } else {
            return PageRequest.of(0, limit + 1, Sort.by("id").descending());
        }
    }

    private List<ToDo> getToDos(Long userId, Long cursor, SortType sortType, Pageable pageable) {
        if (cursor == null) {
            return loadToDoPort.findToDos(userId, pageable);
        } else if (sortType == SortType.DESC) {
            return loadToDoPort.findToDosBeforeCursor(userId, cursor, pageable);
        } else {
            return loadToDoPort.findToDosAfterCursor(userId, cursor, pageable);
        }
    }

}

