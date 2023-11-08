package ru.otus.course.dao.dto;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import ru.otus.course.converter.AnswerCsvConverter;
import ru.otus.course.entity.Answer;
import ru.otus.course.entity.Question;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionDTO {

	@CsvBindByPosition(position = 0)
	private String text;

	@CsvBindAndSplitByPosition(position = 1, collectionType = ArrayList.class, elementType = Answer.class,
			converter = AnswerCsvConverter.class, splitOn = "\\|")
	private List<Answer> answers;

	public Question toDomainObject() {
		return new Question(text, answers);
	}
}
