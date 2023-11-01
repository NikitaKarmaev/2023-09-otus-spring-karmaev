package ru.otus.hw01.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw01.service.api.IOService;

import java.io.*;
import java.util.List;

@RequiredArgsConstructor
public class PrintService implements IOService {

	private final InputStream input;
	private final OutputStream output;

	@Override
	public void print(String message) {
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output))) {
			writer.write(message);
		} catch (IOException e) {
			throw new RuntimeException("Can`t execute write operation!", e);
		}
	}

	@Override
	public void print(List<?> objects) {
		StringBuilder sb = new StringBuilder();
		objects.forEach(q -> sb.append(q).append("\n\n"));
		print(sb.toString());
	}

	@Override
	public String read() {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
			while (reader.ready()) {
				sb.append(reader.readLine()).append("\n");
			}
		} catch (IOException e) {
			throw new RuntimeException("Can`t execute read operation!", e);
		}
		return sb.toString();
	}
}
