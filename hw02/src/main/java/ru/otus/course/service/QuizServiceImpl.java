package ru.otus.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.course.dao.QuestionDAO;
import ru.otus.course.entity.Answer;
import ru.otus.course.entity.Question;
import ru.otus.course.entity.QuizResult;
import ru.otus.course.entity.Student;
import ru.otus.course.service.api.IOService;
import ru.otus.course.service.api.QuizService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

	private final IOService ioService;

	private final QuestionDAO questionDAO;

	@Override
	public QuizResult executeQuizFor(Student student) {
		ioService.printLine("");
		ioService.printFormattedLine("Please answer the questions below:");
		List<Question> questions = questionDAO.getAll();
		QuizResult testResult = new QuizResult(student);

		for (Question question: questions) {
			int specifiedAnswer = getSpecifiedAnswer(question);
			boolean isAnswerValid = question.getAnswers().get(specifiedAnswer - 1).isCorrect();
			testResult.applyAnswer(question, isAnswerValid);
		}
		return testResult;
	}

	private int getSpecifiedAnswer(Question question) {
		AtomicInteger counter = new AtomicInteger(1);
		List<Answer> answers = question.getAnswers();

		StringBuilder sb = new StringBuilder();
		for (Answer answer : answers) {
			sb.append("\n\t");
			sb.append(counter.getAndIncrement());
			sb.append(")");
			sb.append(answer.getValue());
		}
		ioService.printFormattedLine("\n%s:%s", question.getValue(), sb.toString());

		return ioService.readIntForRangeWithPrompt(
				1,
				answers.size(),
				"Your answer:",
				"Specified answer does not exist! Try again"
		);
	}
}