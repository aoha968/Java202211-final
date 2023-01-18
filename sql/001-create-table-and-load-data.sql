DROP TABLE IF EXISTS pokemon;
DROP TABLE IF EXISTS task;

CREATE TABLE pokemon (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 type1 VARCHAR(20) NOT NULL,
 type2 VARCHAR(20),
 PRIMARY KEY(id)
);

CREATE TABLE task (
 id int unsigned AUTO_INCREMENT,
 detail VARCHAR(20) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO pokemon (id, name, type1, type2) VALUES (1, "フシギダネ", "くさ", "どく");
INSERT INTO pokemon (id, name, type1, type2) VALUES (2, "フシギソウ", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (3, "フシギバナ", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (4, "ヒトカゲ", "ほのお", "");
INSERT INTO pokemon (id, name, type1, type2) VALUES (5, "リザード", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (6, "リザードン", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (7, "ゼニガメ", "みず", "");
INSERT INTO pokemon (id, name, type1, type2) VALUES (8, "カメール", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (9, "カメックス", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (10, "キャタピー", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (11, "トランセル", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (12, "バタフリー", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (13, "ビードル", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (14, "コクーン", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (15, "スピアー", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (16, "ポッポ", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (17, "ピジョン", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (18, "ピジョット", "？？？", "？？？");
INSERT INTO pokemon (id, name, type1, type2) VALUES (19, "コラッタ", "？？？", "？？？");

INSERT INTO task (id, detail) VALUES (1, "ミッション1");
INSERT INTO task (id, detail) VALUES (2, "ミッション2");
