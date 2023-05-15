CREATE TABLE post (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL
);

INSERT INTO post (title, content) VALUES ('Hello, world !', 'My First Blog Post');