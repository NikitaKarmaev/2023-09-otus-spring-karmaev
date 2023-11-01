package ru.otus.hw01.entity;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.hw01.converter.AnswerCsvConverter;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

	private String value;

	private List<Answer> answers;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(value);
		answers.forEach(awr -> sb.append("\n").append("\t- ").append(awr));
		return sb.toString();
	}
}