package ru.otus.course.service.api;

import ru.otus.course.entity.QuizResult;
import ru.otus.course.entity.Student;

public interface QuizService {

	QuizResult executeQuizFor(Student student);
}
