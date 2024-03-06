package ru.otus.hw.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private String description;

    @DBRef
    private Book book;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Comment) {
            Comment b = (Comment) obj;
            return Objects.equals(this.id, b.getId())
                    && Objects.equals(this.description, b.getDescription());
        }
        return false;
    }
}
