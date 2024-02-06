package ru.otus.course.config;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.course.service.api.QuizRunnerService;

@ShellComponent
@RequiredArgsConstructor
public class QuizCommands {

	private final QuizRunnerService service;

	@ShellMethod(value = "Hello", key = {"h", "hi"})
	public String hello(@ShellOption(defaultValue = "User") String name) {
		return String.format("Hello, %s!", name);
	}

	@ShellMethod(value = "Running quiz", key = {"r", "run"})
	public void run() {
		service.run();
	}
}
