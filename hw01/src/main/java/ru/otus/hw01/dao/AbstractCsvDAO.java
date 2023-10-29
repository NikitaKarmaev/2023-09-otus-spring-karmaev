package ru.otus.hw01.dao;

import com.opencsv.bean.CsvToBean;

public abstract class AbstractCsvDAO<T> implements IDao<T> {

	protected CsvToBean<T> csvBean;

	private final String csvPath;

	public AbstractCsvDAO(String csvPath) {
		this.csvPath = csvPath;
	}
}