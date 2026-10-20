-- Cadastro de Comentários (atividade 1 - TWII)
-- Rode este script no MySQL (phpMyAdmin ou linha de comando)
-- Banco utilizado pelo projeto: facebook

CREATE TABLE IF NOT EXISTS comments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    comment_date DATE NOT NULL,
    post_id INT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts(id)
);
