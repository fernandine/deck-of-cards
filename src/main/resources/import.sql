--CREATE DATABASE  IF NOT EXISTS `deck`;
--USE `deck`;
--CREATE TABLE tb_player (
--   id BIGINT NOT NULL AUTO_INCREMENT,
--     winner BOOLEAN,
--     card_sum INT,
--     PRIMARY KEY (id)
--);
--
--CREATE TABLE tb_cards (
--  id BIGINT NOT NULL AUTO_INCREMENT,
--  code VARCHAR(255),
--  tb_value VARCHAR(255),
--  suit VARCHAR(255),
--  player_id BIGINT,
--  PRIMARY KEY (id),
--  FOREIGN KEY (player_id) REFERENCES tb_player(id)
--);
INSERT INTO tb_player(name, points) VALUES ('jogador 1', 0);
INSERT INTO tb_player(name, points) VALUES ('jogador 2', 0);
INSERT INTO tb_player(name, points) VALUES ('jogador 3', 0);
INSERT INTO tb_player(name, points) VALUES ('jogador 4', 0);
