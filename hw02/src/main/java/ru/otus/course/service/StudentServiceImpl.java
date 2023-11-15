package ru.otus.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.course.entity.Student;
import ru.otus.course.service.api.IOService;
import ru.otus.course.service.api.StudentService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final IOService ioService;

	@Override
	public Student determineCurrentStudent() {
		String firstName = ioService.readStringWithPrompt("Please input your first name");
		String lastName = ioService.readStringWithPrompt("Please input your last name");
		return new Student(firstName, lastName);
	}
}
