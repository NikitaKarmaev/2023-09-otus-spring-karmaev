package ru.otus.hw01.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
	private String value;

	private boolean flag;

	public String getValueWithTabPrefix() {
		return "\t- " + getValue();
	}
}