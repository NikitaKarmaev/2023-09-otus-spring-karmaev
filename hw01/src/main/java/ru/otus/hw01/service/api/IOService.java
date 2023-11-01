package ru.otus.hw01.service.api;

import java.util.List;

public interface IOService {

	void print(String message);

	void print(List<?> list);

	String read();
}
