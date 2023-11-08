package ru.otus.hw01.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.hw01.dao.dto.QuestionDTO;
import ru.otus.hw01.entity.Question;
import ru.otus.hw01.exception.QuestionReadException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QuestionDAOImpl implements QuestionDAO {

	private final String csvPath;

	public QuestionDAOImpl(String csvPath) {
		this.csvPath = csvPath;
	}

	@Override
	public List<Question> getAll() {
		InputStream in = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(csvPath));
		try (InputStreamReader input = new InputStreamReader(in)) {
			CsvToBean<QuestionDTO> csvBean = new CsvToBeanBuilder<QuestionDTO>(input)
					.withType(QuestionDTO.class)
					.withIgnoreLeadingWhiteSpace(true)
					.withSkipLines(1)
					.withSeparator(';')
					.build();

			return csvBean.parse()
					.stream()
					.map(QuestionDTO::toDomainObject)
					.collect(Collectors.toList());
		} catch (IllegalStateException | IOException e) {
			throw new QuestionReadException("Can`t read questions!", e);
		}
	}
}