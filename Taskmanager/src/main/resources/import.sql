-- Este ficheiro é executado automaticamente pelo Spring Boot na inicialização.

-- -----------------------------------------------------
-- Tabela `tb_role`
-- -----------------------------------------------------
INSERT INTO tb_role(authority) VALUES ('ROLE_USER');
INSERT INTO tb_role(authority) VALUES ('ROLE_ADMIN');

-- -----------------------------------------------------
-- Tabela `tb_category`
-- -----------------------------------------------------
INSERT INTO tb_category(name) VALUES ('Pessoal');
INSERT INTO tb_category(name) VALUES ('Trabalho');
INSERT INTO tb_category(name) VALUES ('Estudo');
INSERT INTO tb_category(name) VALUES ('Saúde');
INSERT INTO tb_category(name) VALUES ('Financeiro');

-- -----------------------------------------------------
-- Tabela `tb_user`
-- Passwords codificadas com BCrypt:
-- 'senha123' para Ana Silva e Carlos Pereira
-- 'admin123' para Maria Oliveira
-- -----------------------------------------------------
INSERT INTO tb_user(name, email, password) VALUES ('Ana Silva', 'ana@email.com', '$2a$10$3Z3cEaX7jF8c.i2.aB2yyeL5b9Jp7w9L3z5j.e8g/q3Rk8p7d.w.e');
INSERT INTO tb_user(name, email, password) VALUES ('Carlos Pereira', 'carlos@email.com', '$2a$10$3Z3cEaX7jF8c.i2.aB2yyeL5b9Jp7w9L3z5j.e8g/q3Rk8p7d.w.e');
INSERT INTO tb_user(name, email, password) VALUES ('Maria Oliveira', 'maria.admin@email.com', '$2a$10$hBvY3.d3x.3kE1p3y/o3UuW3v8.k3o.p6j.C4w.D4g.G2g.H2e.L2');

-- -----------------------------------------------------
-- Tabela de Junção `tb_user_role`
-- -----------------------------------------------------
-- Ana Silva (ID 1) tem o papel ROLE_USER (ID 1)
INSERT INTO tb_user_role(user_id, role_id) VALUES (1, 1);
-- Carlos Pereira (ID 2) tem o papel ROLE_USER (ID 1)
INSERT INTO tb_user_role(user_id, role_id) VALUES (2, 1);
-- Maria Oliveira (ID 3) tem os papéis ROLE_USER (ID 1) e ROLE_ADMIN (ID 2)
INSERT INTO tb_user_role(user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role(user_id, role_id) VALUES (3, 2);

-- -----------------------------------------------------
-- Tabela `tb_task`
-- -----------------------------------------------------
-- Tarefas para Ana Silva (user_id = 1)
INSERT INTO tb_task(title, description, priority, status, dead_line, user_id, category_id) VALUES ('Marcar consulta médica', 'Ligar para o consultório e agendar check-up anual.', 'ALTA', 'PENDENTE', '2025-07-15', 1, 4);
INSERT INTO tb_task(title, description, priority, status, dead_line, user_id, category_id) VALUES ('Pagar fatura do cartão', 'Vencimento no final do mês. Não esquecer.', 'ALTA', 'PENDENTE', '2025-07-30', 1, 5);
INSERT INTO tb_task(title, description, priority, status, dead_line, user_id, category_id) VALUES ('Comprar presente de aniversário', 'Presente para o aniversário do João.', 'MEDIA', 'EM_ANDAMENTO', '2025-08-10', 1, 1);

-- Tarefas para Carlos Pereira (user_id = 2)
INSERT INTO tb_task(title, description, priority, status, dead_line, user_id, category_id) VALUES ('Preparar apresentação de vendas', 'Criar os slides para a reunião com o novo cliente.', 'ALTA', 'EM_ANDAMENTO', '2025-07-22', 2, 2);
INSERT INTO tb_task(title, description, priority, status, dead_line, user_id, category_id) VALUES ('Terminar curso de Spring Boot', 'Finalizar os módulos de segurança e testes.', 'MEDIA', 'CONCLUIDO', '2025-07-05', 2, 3);
INSERT INTO tb_task(title, description, priority, status, dead_line, user_id, category_id) VALUES ('Responder e-mails urgentes', 'Limpar a caixa de entrada dos e-mails marcados como importantes.', 'BAIXA', 'PENDENTE', '2025-07-11', 2, 2);

-- Tarefas para Maria Oliveira (user_id = 3)
INSERT INTO tb_task(title, description, priority, status, dead_line, user_id, category_id) VALUES ('Revisar relatório financeiro', 'Analisar o relatório do último trimestre e preparar resumo para a diretoria.', 'ALTA', 'PENDENTE', '2025-07-18', 3, 5);
INSERT INTO tb_task(title, description, priority, status, dead_line, user_id, category_id) VALUES ('Organizar a festa de fim de ano da empresa', 'Contratar o buffet e o local.', 'MEDIA', 'EM_ANDAMENTO', '2025-09-30', 3, 2);
