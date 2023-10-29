package ru.otus.hw01.converter;

import com.opencsv.bean.AbstractBeanField;
import lombok.NoArgsConstructor;
import ru.otus.hw01.bean.Answer;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AnswersConverter extends AbstractBeanField {

	@Override
	protected Object convert(String s) {
		List<Answer> answers = new ArrayList<>();
		String[] rawAnswers = s.split("\\|");
		for (String rawAnswer : rawAnswers) {
			String[] answerParts = rawAnswer.split("%");
			Answer answer = new Answer(answerParts[0], Boolean.parseBoolean(answerParts[1]));
			answers.add(answer);
		}
		return answers;
	}
}