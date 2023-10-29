package ru.otus.hw01.dao.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.hw01.bean.Question;
import ru.otus.hw01.dao.AbstractCsvDAO;

import java.io.InputStreamReader;
import java.util.List;

public class QuestionCsvDAO extends AbstractCsvDAO<Question> {

	public QuestionCsvDAO(String csvPath) {
		super(csvPath);

		InputStreamReader input = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(csvPath));
		this.csvBean = new CsvToBeanBuilder<Question>(input)
				.withType(Question.class)
				.withIgnoreLeadingWhiteSpace(true)
				.withSkipLines(1)
				.withSeparator(';')
				.build();
	}

	@Override
	public List<Question> getQuestions() {
		return csvBean.parse();
	}
}