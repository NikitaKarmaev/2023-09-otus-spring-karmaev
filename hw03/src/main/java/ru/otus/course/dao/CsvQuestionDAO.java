package ru.otus.course.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.course.config.QuizFileNameProvider;
import ru.otus.course.dao.dto.QuestionDTO;
import ru.otus.course.entity.Question;
import ru.otus.course.exception.QuestionReadException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CsvQuestionDAO implements QuestionDAO {

	private final QuizFileNameProvider fileNameProvider;

	@Override
	public List<Question> getAll() {
		String csvPath = fileNameProvider.getQuizFileName();
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