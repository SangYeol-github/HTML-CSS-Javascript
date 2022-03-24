USE world;
SELECT * FROM city;
-- * 모든 컬럼, city : table name , 문자열은 ''

SELECT * FROM city WHERE CountryCode = "KOR";
-- WHERE : 특정행 검색조건

SELECT population FROM city WHERE name = "Hanam";
-- population : 특정 컬럼명

SELECT * FROM city;
-- SELECT col1, col2.... FROM tableName;
-- SELECT * FROM tb WHERE col1='val';

-- 인구수가 100만 이상인 도시명과 인구수를 표시해보세요.
SELECT name, population FROM city WHERE Population >= 1000000;

-- 한국 도시명과 국가코드를 표시해보세요.
SELECT name, countrycode FROM city WHERE countrycode = 'KOR';

-- 서울의 인구수를 표시해보세요.
SELECT name, population FROM city where name = 'Seoul';

-- 우크라이나(UKR)
SELECT * FROM city WHERE CountryCode = 'UKR';
-- 중국(CHN)
SELECT * FROM city WHERE CountryCode = 'CHN';

-- 국가코드 나열하기 중복되지 않게.
SELECT DISTINCT CountryCode FROM city;

-- 한국의 도시수를 표시하려면?
SELECT COUNT(name) FROM city Where CountryCode = 'KOR';

-- 국가의 수? AS 별칭 "" 특수문자, 공백 영어가 아닌 다른문자
SELECT COUNT(DISTINCT CountryCode) AS "국가수" FROM city;
SELECT COUNT(DISTINCT countryCode) "국가수" FROM city;

-- 위의 문장을 하위질의(Sub-Query) 형식으로 표현해보면 ?
SELECT COUNT(*) NationCount FROM -- AS Nationcount 별칭 
(
	SELECT DISTINCT countryCode FROM city
)t1; -- 가명 만들어주기  --	Error Code: 1248. Every derived table must have its own alias 

-- ID가 100인 도시정보
SELECT * FROM city WHERE ID = 100;

-- ID가 10~20사이인 도시정보
SELECT * FROM city WHERE ID>=10 AND ID<=20;

-- BETWEEN 연산자로 위의 문장을 표현해보면?
SELECT * FROM city WHERE ID BETWEEN 10 AND 20;

-- ID가 10, 20, 22, 30, 45 에 해당하는 도시정보를 표시하려면?
SELECT * FROM city WHERE ID IN(10,20,22,30,45);

-- district 컬럼의 값 중에 'Noord'으로 시작되는 값이 포함된 도시정보 표시
SELECT * FROM city WHERE District LIKE 'Noord%'; -- % 뒤에 없거나 뭐가 있는경우 wildcard

-- 도시명이 'K' 로 시작되는 도시정보를 표시
SELECT * FROM city WHERE name LIKE 'K%';

-- 도시명이 'K' 로 시작되는 도시 숫자
SELECT COUNT(*) FROM city WHERE name LIKE 'K%';

-- 두번째 철자가 a인것
SELECT * FROM city WHERE name LIKE '_a%';

-- 도시명 2번째 철자가 'a'인 행을 찾아 검색된 행 수를 표시할 때 Sub_Query 사용하기
SELECT COUNT(*) FROM
(
	SELECT * FROM city WHERE Name LIKE '_a%'
)t1; 

-- 국가코드가 AFG 이거나 NLD에 속하는 도시들의 정보 표시하기
SELECT * FROM city WHERE countryCode = 'AFG' OR CountryCode = 'NLD';