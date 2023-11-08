package ru.otus.hw01.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
	private String value;

	private boolean isCorrect;

	@Override
	public String toString() {
		return value;
	}
}