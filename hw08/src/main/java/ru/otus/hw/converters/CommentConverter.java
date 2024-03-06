package ru.otus.hw.converters;

import org.springframework.stereotype.Component;
import ru.otus.hw.models.Comment;

@Component
public class CommentConverter {

    public String commentToString(Comment comment) {
        return "Book id: %s.\n Comment id: %s\n Comment text: %s.\n"
                .formatted(comment.getBook().getId(),
                        comment.getId(),
                        comment.getDescription());
    }
}
