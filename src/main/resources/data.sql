INSERT IGNORE INTO tb_roles (role_name) VALUES ('ROLE_ADMIN');
INSERT IGNORE INTO tb_roles (role_name) VALUES ('ROLE_BASIC');

INSERT IGNORE INTO tb_users (user_id, username, password)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'admin', '$2a$12$NnLrACpbqzMEuXGolmbyr.0HNZKhSkTppJ/xEW7vSefMnifjJYK6C');

INSERT IGNORE INTO tb_users_roles (user_id, role_id)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 1);