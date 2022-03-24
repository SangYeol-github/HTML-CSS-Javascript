DESC user;
INSERT INTO user(uid,pwd) VALUE
	('scott', 'scottpwd'),
    ('jone', 'jonepwd'),
    ('david', 'davidpwd');
SELECT * FROM user;

SELECT * FROM emp;
SELECT * FROM EMP LIMIT 2, 2;  -- 2개는 건너뛰고 2개를 가져와라. 

-- Pagination
-- 한 화면에 3개씩 2 페이지 가져오기
-- 1 -> 0	: (1-1) * 3 = 0
-- 2 -> 3	: (2-1) * 3 = 3
-- 3 -> 6	: (3-1) * 3 = 6
-- 4 -> 9	: (4-1) * 3 = 9  

SELECT * FROM emp LIMIT 3,3;
SELECT * FROM emp JOIN (SELECT COUNT(*) as cnt FROM emp) t1;

SELECT * FROM
(SELECT * FROM emp LIMIT 3,3) t1
CROSS JOIN
(SELECT COUNT(*) totalCnt FROM emp) t2;

SELECT * FROM emp;
