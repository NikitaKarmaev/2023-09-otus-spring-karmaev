package ru.otus.course.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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