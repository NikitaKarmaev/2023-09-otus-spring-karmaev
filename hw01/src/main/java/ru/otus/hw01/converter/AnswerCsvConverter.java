package ru.otus.hw01.converter;

import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import lombok.NoArgsConstructor;
import ru.otus.hw01.entity.Answer;

@NoArgsConstructor
public class AnswerCsvConverter extends AbstractCsvConverter {

	@Override
	public Object convertToRead(String value) {
		String[] answerParts = value.split("%");
		return new Answer(answerParts[0], Boolean.parseBoolean(answerParts[1]));
	}
}