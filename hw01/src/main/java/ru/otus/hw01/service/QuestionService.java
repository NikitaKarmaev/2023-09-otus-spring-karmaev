package ru.otus.hw01.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.hw01.bean.Answer;
import ru.otus.hw01.bean.Question;
import ru.otus.hw01.dao.IDao;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionService implements IService<Question> {
	private IDao<Question> questionDAO;

	@Override
	public List<Question> printData() {
		List<Question> questions = questionDAO.getQuestions();
		for (Question question : questions) {
			System.out.println(question.getValue());
			question.getAnswers().stream().map(Answer::getValueWithTabPrefix).forEach(System.out::println);
			System.out.println();
		}
		return questions;
	}
}