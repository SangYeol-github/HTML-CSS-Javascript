
-- 도시 중에서 인구가 가장 적은 도시의 이름과 인구수를 표시해보세요

USE world;
SELECT MIN(population) FROM city;
-- 인구수가 가장 적은 도시의 이름과 인구수, 국가코드를 표시해보세요.
SELECT Name, population, CountryCode
FROM city
WHERE population=(
SELECT MIN(population) FROM city
);

-- city 테이블에는 국가코드(PCN)만 있고 국가명은 없다.
-- 국가명은 country 테이블에 저장되어 있음
-- 최소의 인구를 가진 도시명과 그 도시의 국가명을 동시에 표시하려면?
-- SQL문장 한문장에서 2개의 테이블을 다룰 필요가 있을때 JOIN 을 사용한다
SELECT cn.name, ct.name, ct.population, cn.continent, region
FROM city AS ct INNER JOIN country AS cn 
ON ct.countrycode = cn.code 
WHERE ct.population=(
   SELECT MIN(population)FROM city
);

-- 국토의 면적이 가장 넓은 국가의 이름과 면적의 크기를 표시해보세요.
SELECT country.name, country.surfacearea
FROM country
WHERE surfacearea=(SELECT MAX(surfacearea) FROM country);

-- AVG() : 평균, AVG(population)
-- SUM() : 합게, SUM(sal)
-- 사원급여 평균액, 급여총액을 구해보세요.
SELECT AVG(sal), SUM(sal) FROM emp;
-- SELECT ename, sal, AVG(sal), SUM(sal) FROM emp; -- error

-- 평균보다 높은 급여를 받는 사원의 모든 정보를 표시해보세요.
SELECT * FROM emp
WHERE sal > (
SELECT AVG(sal) FROM emp
);

-- JOIN : INNER JOIN, OUTER JOIN, FULL JOIN, CROSS JOIN(연결 조건이 필요 없다, 무조건)
-- OUTER JOIN : LEFT OUTER JOIN, RIGHaaT OUTER JOIN --연결 조건에 맞지 않아도 선택해서 가져오라.

-- table1에 col1 컬럼 생성, 각 행에 a, b, c, d 입력.
-- table2에 col1 컬럼 생성, 각 행에 1, 2, 3, 4 입력. 
-- 위의 테이블 2개를 CROSS JOIN 하면?
DESCRIBE table1; -- 자료를 설명해보세요.
DESCRIBE table2; 
SELECT * FROM table1, table2;

SELECT * FROM table1, table2 ORDER BY table1.col1;

-- table2의 레코드를 1만 남기고 모두 삭제하기.
SELECT * FROM table2;
DELETE FROM table2 WHERE col1 != 1;

-- 한 행만 가진 table2와 table1을 CROSS JOIN
SELECT * FROM table1 CROSS JOIN table2;

-- 평균 급여액보다 더 많이 급여를 받은 사람들의 정보와 평균 급여액을 표시해보세요.
-- 사원명, 급여액, 전체평균급여

SELECT * FROM emp CROSS JOIN (SELECT AVG(sal) AS "전체평균급여" FROM emp) t1  -- CROSS JOIN 생략가능
WHERE sal > (
SELECT AVG(sal) FROM emp 
);

SELECT ename "사원명", sal "급여액", avg "평균급여액" -- table 먼저 확인. 실행.
FROM emp, (SELECT ROUND(AVG(sal)) AS "avg" FROM emp) t1 -- inline view -- () 안이 먼저 실행
WHERE sal > t1.avg;

DESCRIBE emp;
SELECT detpno FROM emp;

CREATE TABLE dept (
	deptno INT NOT NULL,
    dname VARCHAR(20) NOT NULL,
    loc VARCHAR(20),
    PRIMARY KEY(deptno)
);
DESC dept;

-- 10, '총무과', '대전'
-- 20, '개발팀', '강릉'
-- 30, '생산과', '광주'
-- 40, '연구실', '인천'
-- 50, '인사과', '서울'

INSERT INTO dept VALUES
	(10, "총무과", "대전"),
	(20, "개발팀", "강릉"),
	(30, "생산과", "광주"),
	(40, "연구실", "인천"),
	(50, "인사과", "서울");

SELECT * FROM dept;
SELECT * FROM emp;

-- 'King' 이라는 사원을 emp 테이블에 입력(17,'King','2000-01-01', null, 7000)
INSERT INTO emp VALUES (17,'King','2000-01-01', null, 7000);

-- 각 사원의 이름과 부서명, 급여액을 표시해보세요.

SELECT e.ename, d.dname, e.sal 
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno;

SELECT e.ename, d.dname, e.sal 
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno = d.deptno;

-- 테이블간의 관계(Relation), RDBMS(관계형 데이터베이스 관리 시스템)

-- 테이블 생성시에 FK 설정
-- 테이블 생성 후 FK 설정 가능.

-- CREATE TABLE emp (
-- 	empno,
--     ename,
--     deptno,
--     hiredate,
--     sal,
--     FOREIGN KEY(deptno) REFERENCES dept(dempno) emp의 deptno가 dept의 deptno를 참조한다는 키값을 준다는 뜻.
-- );

-- FK를 설정할 테이블이 이미 생성된 후에 FK 설정하기.
-- ALTER를 사용하여 테이블을 지정해주고  ADD CONSTRAINT 제약조건의 이름을 지정
-- FOREIGN KEY(deptno) REFERENCES dept(deptno); 제약조건의 내용을 지정한다.

ALTER TABLE emp
ADD CONSTRAINT FK_deptno -- 제약조건의 이름은 FK_deptno
FOREIGN KEY(deptno) REFERENCES dept(deptno); -- 제약조건의 내용은 FOREIGN KEY(deptno) REFERENCES dept(deptno);

INSERT INTO emp VALUES(20, 'xxx', '2000-10-10',100, 0);

-- FK 삭제방법
ALTER TABLE emp
DROP FOREIGN KEY FK_deptno;

-- 부모 테이블에서 자식 테이블이 참조하고 있는 외부키 컬럼을 변경한다면?
-- dept 테이블의 deptno 컬럼 값 중에서 10 -> 15로 변경한다면?
-- 자식은 부모를 잃게 된다. 무결성이 깨진다.
-- CASCADE 단계적으로.
-- 따라서 ON DELETE CASCADE ON UPDATE CASCADE를 사용하여
-- 부모 데이터 값이 변경되면 자식 데이터 값도 변경되고 삭제도 동일하게 제약조건을 걸어준다.

ALTER TABLE emp
ADD CONSTRAINT FK_deptno 
FOREIGN KEY(deptno) REFERENCES dept(deptno)
ON DELETE CASCADE ON UPDATE CASCADE; -- 단계적으로 부모 자식간의 데이터값이 같이 변경되게 설정하는 조건

UPDATE dept SET deptno = 10 WHERE deptno = 15;

SELECT * FROM dept;
SELECT * FROM emp;

-- 집합연산
-- 동일한 컬럼과 테이블일 때, TABLE을 합칠수 있다. 합치는 키워드는 UNION.
-- UNION ALL 좌우는 WIDE 상하는 LONG.
SELECT * FROM emp WHERE deptno IN(15, 20)
UNION 
SELECT * FROM emp WHERE deptno IN(30, 40)
UNION
SELECT * FROM emp WHERE deptno IS NULL
ORDER BY empno;

-- emp 테이블에서 모든 행을 가져오는 2개의 SELECT 문장을 UNION ALL 으로 합치면 ?
-- 결과 값은 두번 가져온 결과 값을 가져온다.
SELECT * FROM emp
UNION ALL
SELECT * FROM emp;

-- emp 테이블에서 모든 행을 가져오는 2개의 SELECT 문장을 UNION 으로 합치면 ?
-- 결과 값은 DISTINCT키워드가 적용된 것처럼 중복된 데이터는 표시하지 않는다.
SELECT * FROM emp
UNION
SELECT * FROM emp;

-- 각 부서별로 인원수를 보고하세요. 부서별 = GROUP BY
SELECT deptno, COUNT(*) AS cnt
FROM emp
GROUP BY deptno
ORDER BY cnt DESC, deptno DESC;

-- 그룹에 대한 조건은 HAVING을 사용한다. 컬럼은 WHERE 이듯.
-- 그룹조건을 적용하여 deptno값이 null인 사람을 제외 해보세요.
SELECT deptno, COUNT(*) AS cnt
FROM emp
GROUP BY deptno
HAVING deptno IS NOT NULL
ORDER BY cnt DESC;

USE mydb;
SELECT * FROM city;


