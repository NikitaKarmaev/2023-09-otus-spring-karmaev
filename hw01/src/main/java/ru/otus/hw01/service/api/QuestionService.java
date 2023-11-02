package ru.otus.hw01.service.api;

import ru.otus.hw01.entity.Question;

import java.util.List;

public interface QuestionService {

	List<Question> getAll();
}