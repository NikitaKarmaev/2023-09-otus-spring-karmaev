package ru.otus.hw.converter;

import org.springframework.stereotype.Component;
import ru.otus.hw.model.Comment;

@Component
public class CommentConverter {

    public String commentToString(Comment comment) {
        return "Book id: %s.\n Comment id: %s\n Comment text: %s.\n"
                .formatted(comment.getBook().getBookId(),
                        comment.getId(),
                        comment.getDescription());
    }
}
