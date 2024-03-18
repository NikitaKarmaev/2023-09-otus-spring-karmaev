package ru.otus.hw.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OldBookDto {

    @NotNull(message = "incorrect id")
    private long bookId;

    @NotBlank(message = "incorrect title")
    private String title;

    @NotNull(message = "unknown author")
    private Long authorId;

    @NotNull(message = "unknown genre")
    private Long genreId;
}
