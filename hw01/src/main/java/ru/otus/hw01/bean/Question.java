package ru.otus.hw01.bean;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.otus.hw01.converter.AnswersConverter;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Question {

	@CsvBindByPosition(position = 0)
	private String value;

	@CsvCustomBindByPosition(position = 1, converter = AnswersConverter.class)
	private List<Answer> answers;
}