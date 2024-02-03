package ru.otus.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.course.entity.Student;
import ru.otus.course.service.api.LocalizedIOService;
import ru.otus.course.service.api.StudentService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final LocalizedIOService ioService;

	@Override
	public Student determineCurrentStudent() {
		String firstName = ioService.readStringWithPromptLocalized("user.firstname");
		String lastName = ioService.readStringWithPromptLocalized("user.lastname");
		return new Student(firstName, lastName);
	}
}
