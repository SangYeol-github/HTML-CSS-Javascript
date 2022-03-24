USE mydb;
DESC emp;
-- empno, ename, hiredate, deptno, sal
INSERT INTO emp(empno,ename,hiredate,deptno,sal)
VALUE (11, 'Smith', '2011-05-14', 20, 3400);

INSERT INTO emp VALUES (12, 'Jone', '2022-07-21', 210, 2600);
UPDATE emp SET deptno=10 WHERE empno=12;

INSERT INTO emp VALUES
	(13, 'Blake', '2021-08-02', 30, 2500),
    (14, 'Ward', '2020-02-23', 10, 2800),
    (15, 'Scott', '2009-12-21', 20, 2900);

INSERT INTO emp (empno, ename) VALUES(16, 'Suzan'); 
-- 삭제
DELETE FROM emp WHERE empno = 16;

-- Transaction 명령
ROLLBACK; -- 되돌기기
COMMIT; -- 영구반영 

-- 사번 16, 이름 손흥민, 급여 5000
INSERT INTO emp(empno, ename, sal) VALUE (16, "손흥민" , 5000);

-- NULL값 불러오기
SELECT * FROM emp WHERE  deptno IS NULL;
SELECT * FROM emp WHERE deptno IS NOT NULL;

-- 손흥민에게 10번 부서 배정
UPDATE emp SET deptno = 10 WHERE empno = 16;

-- 부서번호 순으로 정렬
SELECT * FROM emp ORDER BY deptno;
SELECT * FROM emp ORDER BY deptno DESC;

-- 급여액 TOP 3 출력
SELECT * FROM emp ORDER BY sal DESC LIMIT 3; -- 3행

SELECT * FROM emp ORDER BY sal DESC LIMIT 1,2; -- 2번째부터 2행

SELECT * FROM emp;