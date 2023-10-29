package ru.otus.hw01.dao;

import java.util.List;

public interface IDao<T> {

	List<T> getQuestions();
}