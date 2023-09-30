INSERT INTO employee
(
   id,
   name,
   age
)
VALUES
('1','Tom',30);
//初期データを投入するSQ

/* ユーザーマスタ */
INSERT INTO m_user (
user_id
,password
,user_name
,birthday
,age
,gender
,department_id
,role
) VALUES
('system@co.jp', '$2a$10$P74tOcpXNgqpn9jN7Kb2N.9d7crygz0hds9YtHzPjSZXbhIS.jOgm', 'システム管理者', '2000-01-01', 21, 1, 1, 'ROLE_ADMIN')
,('user@co.jp', '$2a$10$P74tOcpXNgqpn9jN7Kb2N.9d7crygz0hds9YtHzPjSZXbhIS.jOgm', 'ユーザー1', '2000-01-01', 21, 2, 2, 'ROLE_GENERAL')
;
//ユーザーデータ設定

/* 部署マスタ */
INSERT INTO m_department (
department_id
,department_name
) VALUES
(1, 'システム管理部')
,(2, '営業部')
;

/* 給料テーブル */
INSERT INTO t_salary (
user_id
,year_month
,salary
) VALUES
('user@co.jp', '2020/11', 280000)
,('user@co.jp', '2020/12', 290000)
,('user@co.jp', '2021/01', 300000)
;