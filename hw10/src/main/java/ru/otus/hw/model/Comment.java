package ru.otus.hw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
@NamedEntityGraph(name = "comment-entity-graph",
        attributeNodes = @NamedAttributeNode("book"))
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Comment) {
            Comment b = (Comment) obj;
            return this.id == b.getId()
                    && Objects.equals(this.description, b.getDescription());
        }
        return false;
    }
}