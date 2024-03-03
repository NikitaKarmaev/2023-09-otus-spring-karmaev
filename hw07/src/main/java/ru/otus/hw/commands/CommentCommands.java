package ru.otus.hw.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.converters.CommentConverter;
import ru.otus.hw.models.Comment;
import ru.otus.hw.services.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentCommands {

    private final CommentService commentService;

    private final CommentConverter converter;

    @ShellMethod(value = "Find comments by book ID", key = "findbkcm")
    public String findByBookId(long bookId) {
        List<Comment> comments = commentService.findByBookId(bookId);
        if (comments.isEmpty()) {
            return "Book hasn't comments";
        }
        return comments.stream().map(converter::commentToString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @ShellMethod(value = "Find comment by ID", key = "findcm")
    public String findById(long commentId) {
        return commentService.findById(commentId).map(converter::commentToString)
                .orElse("Sorry comment doesn't found");
    }

    @ShellMethod(value = "Insert comment", key = "addcm")
    public String insert(String text, long bookId) {
        var comment = commentService.insert(text, bookId);
        return converter.commentToString(comment);
    }

    @ShellMethod(value = "Update comment", key = "updcm")
    public String update(long commentId, String commentText) {
        var comment = commentService.update(commentId, commentText);
        return converter.commentToString(comment);
    }

    @ShellMethod(value = "Delete comment by ID", key = "delcm")
    public void delete(long commentId) {
        commentService.delete(commentId);
    }
}
