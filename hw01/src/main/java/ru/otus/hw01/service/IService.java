package ru.otus.hw01.service;

import java.util.List;

public interface IService<T> {

	List<T> printData();
}