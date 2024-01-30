package ru.otus.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.course.config.LocalizedMessages;
import ru.otus.course.entity.Student;
import ru.otus.course.service.api.IOService;
import ru.otus.course.service.api.StudentService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final LocalizedMessages messages;

	private final IOService ioService;

	@Override
	public Student determineCurrentStudent() {
		String firstName = ioService.readStringWithPrompt(messages.getFirstNameQuestion());
		String lastName = ioService.readStringWithPrompt(messages.getLastNameQuestion());
		return new Student(firstName, lastName);
	}
}
