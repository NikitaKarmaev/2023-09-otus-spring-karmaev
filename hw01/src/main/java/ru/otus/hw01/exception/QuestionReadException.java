package ru.otus.hw01.exception;

public class QuestionReadException extends RuntimeException {

	public QuestionReadException(String message, Throwable ex) {
		super(message, ex);
	}
}
