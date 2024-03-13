CREATE TABLE authors (
    author_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(255)
);

CREATE TABLE genres (
    genre_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE books (
    book_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors (author_id) on delete cascade,
    genre_id BIGINT,
    FOREIGN KEY (genre_id) REFERENCES genres (genre_id) on delete cascade
);

CREATE TABLE comments (
    comment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),
    book_id BIGINT,
    FOREIGN KEY (book_id) REFERENCES books (book_id) on delete cascade
);