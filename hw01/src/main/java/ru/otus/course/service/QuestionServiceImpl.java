package ru.otus.course.service;

import lombok.RequiredArgsConstructor;
import ru.otus.course.dao.QuestionDAO;
import ru.otus.course.entity.Question;
import ru.otus.course.service.api.QuestionService;

import java.util.List;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

	private final QuestionDAO questionDAO;

	@Override
	public List<Question> getAll() {
		return questionDAO.getAll();
	}
}