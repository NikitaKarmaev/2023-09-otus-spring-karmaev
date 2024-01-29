package ru.otus.course.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuizResult {

	private final Student student;

	private final List<Question> answeredQuestions;

	private int rightAnswersCount;

	public QuizResult(Student student) {
		this.student = student;
		this.answeredQuestions = new ArrayList<>();
	}

	public void applyAnswer(Question question, boolean isRightAnswer) {
		answeredQuestions.add(question);
		if (isRightAnswer) {
			rightAnswersCount++;
		}
	}
}
