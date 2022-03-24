SELECT * FROM world.country;
-- 대륙이 'Africa' 가 아닌 다른 대륙에 속하는 국가의 정보 표시
SELECT * FROM country WHERE NOT Continent =  'Africa';
SELECT * FROM country WHERE Continent !=  'Africa';
SELECT * FROM country WHERE Continent <> 'Africa';
-- 오름차순(ASC) : , 내림차순(DESC)
SELECT name, population FROM country ORDER BY Population DESC;
SELECT name, population, GNP FROM country ORDER BY population DESC;
