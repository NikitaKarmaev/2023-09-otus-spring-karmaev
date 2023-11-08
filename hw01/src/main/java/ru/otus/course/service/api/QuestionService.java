package ru.otus.course.service.api;

import ru.otus.course.entity.Question;

import java.util.List;

public interface QuestionService {

	List<Question> getAll();
}