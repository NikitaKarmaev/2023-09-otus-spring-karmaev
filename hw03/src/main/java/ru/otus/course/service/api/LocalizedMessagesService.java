package ru.otus.course.service.api;

public interface LocalizedMessagesService {

	String getMessage(String code, Object ...args);
}
