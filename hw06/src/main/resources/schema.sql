CREATE TABLE authors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(255)
);

CREATE TABLE genres (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors (id),
    genre_id BIGINT,
    FOREIGN KEY (genre_id) REFERENCES genres (id)
);

CREATE TABLE comments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),
    book_id BIGINT,
    FOREIGN KEY (book_id) REFERENCES books (id)
);