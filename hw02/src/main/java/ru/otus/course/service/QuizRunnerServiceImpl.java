package ru.otus.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.course.entity.QuizResult;
import ru.otus.course.entity.Student;
import ru.otus.course.service.api.*;

@Service
@RequiredArgsConstructor
public class QuizRunnerServiceImpl implements QuizRunnerService {

	private final StudentService studentService;

	private final QuizService quizService;

	private final ResultService resultService;

	@Override
	public void run() {
		Student student = studentService.determineCurrentStudent();
		QuizResult result = quizService.executeQuizFor(student);
		resultService.showResult(result);
	}
}
