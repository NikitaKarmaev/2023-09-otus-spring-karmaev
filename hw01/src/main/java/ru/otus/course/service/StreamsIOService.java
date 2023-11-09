package ru.otus.course.service;

import ru.otus.course.service.api.IOService;

import java.io.OutputStream;
import java.io.PrintStream;

public class StreamsIOService implements IOService {

	private final PrintStream printStream;

	public StreamsIOService(OutputStream out) {
		this.printStream = new PrintStream(out);
	}

	@Override
	public void printLine(String s) {
		printStream.println(s);
	}

	@Override
	public void printFormattedLine(String s, Object... args) {
		printStream.printf(s, args);
	}
}
