package ru.otus.hw01.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
	private String value;

	private boolean flag;

	@Override
	public String toString() {
		return value;
	}
}