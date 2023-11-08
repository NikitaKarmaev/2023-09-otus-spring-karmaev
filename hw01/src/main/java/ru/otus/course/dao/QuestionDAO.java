package ru.otus.course.dao;

import ru.otus.course.entity.Question;

import java.util.List;

public interface QuestionDAO {

	List<Question> getAll();
}