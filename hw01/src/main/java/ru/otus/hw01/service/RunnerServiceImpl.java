package ru.otus.hw01.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw01.service.api.EntityService;
import ru.otus.hw01.service.api.IOService;
import ru.otus.hw01.service.api.RunnerService;

import java.util.List;

@RequiredArgsConstructor
public class RunnerServiceImpl implements RunnerService {

	private final EntityService entityService;
	private final IOService ioService;

	@Override
	public void run() {
		List<?> result = entityService.getAll();
		ioService.print(result);
	}
}
