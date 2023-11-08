package ru.otus.hw01.dao;

import ru.otus.hw01.entity.Question;

import java.util.List;

public interface QuestionDAO {

	List<Question> getAll();
}