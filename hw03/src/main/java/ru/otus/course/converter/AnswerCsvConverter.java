package ru.otus.course.converter;

import com.opencsv.bean.AbstractCsvConverter;
import lombok.NoArgsConstructor;
import ru.otus.course.entity.Answer;

@NoArgsConstructor
public class AnswerCsvConverter extends AbstractCsvConverter {

	@Override
	public Object convertToRead(String value) {
		String[] answerParts = value.split("%");
		return new Answer(answerParts[0], Boolean.parseBoolean(answerParts[1]));
	}
}