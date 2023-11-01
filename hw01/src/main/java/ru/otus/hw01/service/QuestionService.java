package ru.otus.hw01.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw01.dao.Dao;
import ru.otus.hw01.entity.Question;
import ru.otus.hw01.service.api.EntityService;
import ru.otus.hw01.service.api.IOService;

import java.util.List;

@RequiredArgsConstructor
public class QuestionService implements EntityService {

	private final Dao<Question> questionDAO;

	@Override
	public List<Question> getAll() {
		return questionDAO.getAll();
	}
}