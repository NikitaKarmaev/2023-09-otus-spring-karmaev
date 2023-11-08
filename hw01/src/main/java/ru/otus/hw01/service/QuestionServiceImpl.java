package ru.otus.hw01.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw01.dao.QuestionDAO;
import ru.otus.hw01.entity.Question;

import java.util.List;

@RequiredArgsConstructor
public class QuestionServiceImpl implements ru.otus.hw01.service.api.QuestionService {

	private final QuestionDAO questionDAO;

	@Override
	public List<Question> getAll() {
		return questionDAO.getAll();
	}
}