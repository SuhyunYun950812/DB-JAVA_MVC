--EMPLOYEES 이름과 직급을 출력하시오. ELLEN님의 직급은 매니저 입니다.
SELECT FIRST_NAME || '님의 직급은'|| JOB_ID || '매니저입니다.' FROM EMPLOYEES;
-- EMPLOYEES 직급을 출력하시오. (중복하지말고)
SELECT DISTINCT JOB_ID FROM EMPLOYEES;
--GROUP BY 연습
SELECT JOB_ID,COUNT(*) 직급별카운트 FROM EMPLOYEES GROUP BY JOB_ID HAVING COUNT(*) >= 20;
--NULL은 비교대상이 되지 않는다.
SELECT EMPLOYEE_ID, FIRST_NAME, COMMISSION_PCT, JOB_ID
FROM EMPLOYEES
WHERE COMMISSION_PCT IS NOT NULL;
--사번을 기준으로 내림차순 정렬,연봉은 오름차순 2차정렬이상 처리된다.
SELECT JOB_ID,SALARY FROM EMPLOYEES ORDER BY JOB_ID DESC, SALARY ASC;
--가상테이블 => 진짜테이블(인스턴스 1개 있는 테이블,컬럼명 1개)
SELECT 24 + 34 AS RESULT FROM DUAL;
--GROUP BY : 2개 이상이 나와야 그룹대상이 된다.
--부서별 총 급여를 구하시오.
SELECT DEPARTMENT_ID,SUM(SALARY) FROM EMPLOYEES
GROUP BY DEPARTMENT_ID HAVING SUM(SALARY) >= 50000
AND DEPARTMENT_ID = 100;

SELECT * FROM EMPLOYEES;
--JOB_ID : IT_PROG 문자열을 비교할 때에는 UPPER(),LOWER()를 사용
SELECT EMPLOYEE_ID, FIRST_NAME, JOB_ID FROM EMPLOYEES
WHERE JOB_ID = UPPER('IT_PROg');

SELECT EMPLOYEE_ID,FIRST_NAME,JOB_ID FROM EMPLOYEES;
--CONCAT(인수가 2개만 허용된다.)
SELECT CONCAT(FIRST_NAME,'($'||SALARY||')') AS "사원 정보" FROM EMPLOYEES
WHERE DEPARTMENT_ID = 30;
-- 부서 20번 사원들의 월만 출력
SELECT FIRST_NAME,HIRE_DATE,SUBSTR(HIRE_DATE,1,2) FROM EMPLOYEES
WHERE DEPARTMENT_ID = 20;
--현재 날짜를 출력
SELECT SYSDATE FROM DUAL;
-EMPLOYEES에서 사원의 입사연도로 부터 현재까지 근무개월수
SELECT FIRST_NAME,SYSDATE 오늘, TO_CHAR(HIRE_DATE,'YYYY/MM/DD') 입사일,
TRUNC(MONTHS_BETWEEN(SYSDATE,HIRE_DATE)) 근무달수
FROM EMPLOYEES
WHERE DEPARTMENT_ID = 30;

ALTER SESSION SET NLS_LANGUAGE = KOREAN;
SELECT SYSDATE, NEXT_DAY(SYSDATE,'수요일')
FROM DUAL;

--현재 날짜에 '2025/04/24' 날짜를 빼서 계싼
--SELECT SYSDATE-'2025/04/24' FROM DUAL;
SELECT CONCAT(TRUNC(SYSDATE- TO_DATE('2025/04/24','YYYY/MM/DD')),'일') FROM DUAL;
--문자열 + 문자열 => 오류
SELECT CONCAT('10,000' , '20,000') FROM DUAL;
SELECT TO_NUMBER('10,000' ,'999,999') + TO_NUMBER('20,000','99,999') FROM DUAL;
-- EMPLOYEES에서 직급별 정렬(오름차순) 이름, 봉금, 인상률, 보너스금액, 월급 + 보너스금액
SELECT FIRST_NAME, SALARY, COMMISSION_PCT, SALARY*NVL(COMMISSION_PCT,0) AS COMMISSION,
SALARY+(SALARY*NVL(COMMISSION_PCT,0)) AS TOTAL, JOB_ID
FROM EMPLOYEES ORDER BY JOB_ID;

-- NVL2(컬럼,NULL이 아니면,NULL이면)
SELECT FIRST_NAME, SALARY, COMMISSION_PCT,
NVL2(COMMISSION_PCT,SALARY+(SALARY*COMMISSION_PCT),SALARY) TOTAL_SAL FROM EMPLOYEES;

SELECT DEPARTMENT_ID,
DECODE(DEPARTMENT_ID,
10,'ADMINSTRATION',
20,'MARKETING',
30,'PURCHASING',
40,'HUMAN RESOURCES',
50,'SHIPPING',
60,'IT',
'DEFAULT') AS DEPARTMENTS
FROM EMPLOYEES ORDER BY DEPARTMENT_ID;
-- GROUP BY SUM,AVG,MAX,MIN,COUNT
-- COUNT(*)연산,비교,카운트를 쓸 때 NULL이 들어가면 널 값은 인정하지 않는다.
SELECT COUNT(*) AS 총인원수 FROM EMPLOYEES;
SELECT COUNT(COMMISSION_PCT) AS 총인원수 FROM EMPLOYEES;
--부서별로 월급 평균을 구하시오.
SELECT DEPARTMENT_ID,ROUND(AVG(SALARY),2) "부서별 평균연봉" FROM EMPLOYEES GROUP BY DEPARTMENT_ID;
SELECT DEPARTMENT_ID,ROUND(AVG(SALARY),2), MAX(SALARY),MIN(SALARY),COUNT(*) "부서별 평균연봉" FROM EMPLOYEES GROUP BY DEPARTMENT_ID;
--EMPLOYEES에서 부서별로 최대급여,최소급여를 보여주되, 부서별 정렬(ASC)
SELECT DEPARTMENT_ID, MAX(SALARY),MIN(SALARY) FROM EMPLOYEES GROUP BY DEPARTMENT_ID ORDER BY DEPARTMENT_ID ASC;
SELECT * FROM EMPLOYEES WHERE DEPARTMENT_ID IS NULL;

--ROLLUP GROUP BY 부분항목별로 통계를 해줄 것
SELECT DEPARTMENT_ID,JOB_ID,MAX(SALARY),MIN(SALARY),COUNT(*),TRUNC(AVG(SALARY)),SUM(SALARY)
FROM EMPLOYEES
GROUP BY ROLLUP(DEPARTMENT_ID,JOB_ID)
ORDER BY DEPARTMENT_ID,JOB_ID;

SELECT DEPARTMENT_ID,JOB_ID,MAX(SALARY),MIN(SALARY),COUNT(*),TRUNC(AVG(SALARY)),SUM(SALARY)
FROM EMPLOYEES
GROUP BY CUBE(DEPARTMENT_ID,JOB_ID)
ORDER BY DEPARTMENT_ID,JOB_ID ASC;

SELECT DEPARTMENT_ID,JOB_ID,MAX(SALARY),MIN(SALARY),COUNT(*),TRUNC(AVG(SALARY)),SUM(SALARY)
FROM EMPLOYEES
GROUP BY CUBE(DEPARTMENT_ID,JOB_ID)
ORDER BY DEPARTMENT_ID;

--새로운 테이블 만들기 DEPT 속성(DEPTNO,DNAME,LOC) NOT NULL(모두),PK(DEPTNO)
CREATE TABLE DEPT(
    DEPTNO NUMBER(4),
    DNAME VARCHAR2(20) NOT NULL,
    LOC VARCHAR2(20) NOT NULL
);
ALTER TABLE DEPT ADD CONSTRAINT DEPT_PK PRIMARY KEY (DEPTNO);
ALTER TABLE DEPT DROP CONSTRAINT DEPT_PK;
ALTER TABLE DEPT MODIFY DNAME VARCHAR2(20);
ALTER TABLE DEPT ADD DPHONE VARCHAR2(12);
ALTER TABLE DEPT DROP COLUMN DPHONE;
ALTER TABLE DEPT MODIFY LOC VARCHAR2(20) NULL;
--INSERT
INSERT INTO DEPT(DEPTNO,DNAME,LOC) VALUES(1,'컴퓨터학과','본관');
INSERT INTO DEPT(DEPTNO,DNAME,LOC) VALUES(2,'시스템학과','공학관');
INSERT INTO DEPT VALUES(3,'컴공','공학관2');
INSERT INTO DEPT(DEPTNO,DNAME) VALUES(4,'디자인');
INSERT INTO DEPT VALUES(5,'컴디',NULL);
--SELECT
SELECT * FROM DEPT;
DESC DEPT;

-- 새로운 테이블 생성 (개-논-물 INSERT INTO 테이블명(컬럼명) VALUES() WHERE(없다)) 50번반복해야된다.
-- 다른 테이블에 이러한 구조가 있는데,삽입,수정, 삭제 빈번히 일어나야 된다.
DROP TABLE DEPT;
SELECT * FROM DEPARTMENTS;

-- 기존의 테이블에 데이터만 다른 테이블에  있는 내용을 가져오고 싶을 때
INSERT INTO DEPT
SELECT DEPARTMENT_ID,DEPARTMENT_NAME,LOCATION_ID FROM DEPARTMENTS;

SELECT * FROM DEPARTMENTS;
-- 새로운 테이블 생성 (개-논-물 INSERT INTO 테이블명(컬럼명) VALUES() WHERE(없다)) 50번반복해야된다.
-- 다른 테이블에 이러한 구조가 있는데,삽입,수정, 삭제 빈번히 일어나야 된다.
-- 제약조건 복사를 하지 못한다.
CREATE TABLE EMP
AS
SELECT * FROM EMPLOYEES;

SELECT * FROM EMP;
DESC EMP;
--EMP EMPLOYEE_ID PK 지정하라.
ALTER TABLE EMP ADD CONSTRAINT EMP_PK PRIMARY KEY (EMPLOYEE_ID);
--EMP 부서번호 90 => 91번으로 변경하세요.
UPDATE EMP SET DEPARTMENT_ID = 91 WHERE DEPARTMENT_ID = 90;
COMMIT;
ROLLBACK;
-- EMP에서 부서 91 부서들만 봉급을 10% 인상
UPDATE EMP SET SALARY = ROUND(SALARY * 1.1) WHERE DEPARTMENT_ID = 91;
SELECT * FROM EMP WHERE UPPER(FIRST_NAME) = 'SUSAN';
-- EMP에서 사원 이름이 SUSAN인 사람을 부서 20, 직급을 FI_MGR로 변경
UPDATE EMP SET DEPARTMENT_ID = 20, JOB_ID = 'FI_MGR' WHERE UPPER(FIRST_NAME) = 'SUSAN';
-- EMP에서 부서 20인 직원들만 삭제.
DELETE FROM EMP WHERE DEPARTMENT_ID = 20;
SELECT * FROM EMP WHERE DEPARTMENT_ID = 20;

SELECT * FROM EMPLOYEES;
--1번 (해결)
SELECT FIRST_NAME,SALARY FROM EMPLOYEES WHERE INITCAP(EMAIL) = INITCAP('JKING');
SELECT FIRST_NAME,SALARY FROM EMPLOYEES WHERE UPPER(EMAIL) = UPPER('JKING');

--2번 (해결)
SELECT EMPLOYEE_ID,FIRST_NAME , SALARY FROM EMPLOYEES WHERE LENGTH(FIRST_NAME) >= 6;

--3번 (해결)
SELECT FIRST_NAME,HIRE_DATE FROM EMPLOYEES WHERE HIRE_DATE >='03/01/01' AND HIRE_DATE <= '03/12/31';
SELECT FIRST_NAME,HIRE_DATE FROM EMPLOYEES WHERE HIRE_DATE BETWEEN '03/01/01' AND '03/12/31' ;
SELECT FIRST_NAME,HIRE_DATE FROM EMPLOYEES WHERE SUBSTR(HIRE_DATE,1,2) = 03;

--4번 (해결)
SELECT FIRST_NAME FROM EMPLOYEES WHERE FIRST_NAME LIKE '%k';
SELECT FIRST_NAME FROM EMPLOYEES WHERE SUBSTR(FIRST_NAME,-1,1) = 'k';

--5번 (해결)
SELECT EMPLOYEE_ID,FIRST_NAME ,JOB_ID FROM EMPLOYEES WHERE MOD(EMPLOYEE_ID,2) = 0;

--6번
SELECT NVL(MANAGER_ID,'CEO') FROM EMPLOYEES ORDER BY MANAGER_ID;
SELECT FIRST_NAME,NVL2(MANAGER_ID,'','CEO') FROM EMPLOYEES WHERE MANAGER_ID IS NULL;
--7번
SELECT EMPLOYEE_ID,FIRST_NAME,JOB_ID,SALARY,DECODE(DEPARTMENT_ID,
20, SALARY * 1.05,
30, SALARY * 1.1,
40, SALARY * 1.15,
60, SALARY * 1.20,
SALARY)
AS TOTAL FROM EMPLOYEES;

SELECT EMPLOYEE_ID,FIRST_NAME,JOB_ID,
CASE WHEN DEPARTMENT_ID = 20 THEN SALARY * 1.05
    WHEN DEPARTMENT_ID = 30 THEN SALARY * 1.1
    WHEN DEPARTMENT_ID = 40 THEN SALARY * 1.15
    WHEN DEPARTMENT_ID = 60 THEN SALARY * 1.20
    ELSE SALARY
    END TOTAL
FROM EMPLOYEES
ORDER BY DEPARTMENT_ID;

SELECT E.DEPARTMENT_ID, EMPLOYEE_ID,FIRST_NAME,JOB_ID,
CASE WHEN E.DEPARTMENT_ID = 20 THEN SALARY * 1.05
    WHEN E.DEPARTMENT_ID = 30 THEN SALARY * 1.1
    WHEN E.DEPARTMENT_ID = 40 THEN SALARY * 1.15
    WHEN E.DEPARTMENT_ID = 60 THEN SALARY * 1.20
    ELSE E.SALARY
    END TOTAL
FROM EMPLOYEES E
JOIN DEPARTMENTS D ON D.DEPARTMENT_ID = E.DEPARTMENT_ID;


--8번
-- 서브 쿼리
SELECT COUNT(DISTINCT JOB_ID) FROM EMPLOYEES;
SELECT COUNT(*) AS "전체 직업수" FROM (SELECT DISTINCT JOB_ID FROM EMPLOYEES);
SELECT JOB_ID,COUNT(JOB_ID) FROM EMPLOYEES GROUP BY JOB_ID;
--9번
SELECT DEPARTMENT_ID,COUNT(*),COUNT(COMMISSION_PCT) FROM EMPLOYEES GROUP BY DEPARTMENT_ID ;


--이미지 문제 1&2
CREATE TABLE EMP01(
EMPNO NUMBER(4)NOT NULL,
ENAME VARCHAR(10)NOT NULL,
JOB VARCHAR(9),
MGR NUMBER(4),
HIREDATE DATE NOT NULL,
SAL NUMBER(7,2)NOT NULL,
COMM NUMBER(7,2)DEFAULT 0.0,
DEPTNO NUMBER(2)NOT NULL
);

INSERT INTO EMP01(EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO)VALUES(7369,'SMITH','CLEAK',7836,TO_DATE('80/12/17','YY/MM/DD'),800,NULL,20)
INSERT INTO EMP01(EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO)VALUES(7499,'ALLEN','SALEMAN',7369,TO_DATE('87/12/20','YY/MM/DD'),1600,300,30)
INSERT INTO EMP01(EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO)VALUES(7839,'KING','PRESIDENT',NULL,TO_DATE('81/02/08','YY/MM/DD'),5000,NULL,10);

INSERT ALL
INTO EMP01 (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMMISSION,DEPTNO)VALUES(7369,'SMITH','CLEAK',7836,TO_DATE('80/12/17','YY/MM/DD'),800,NULL,20)
INTO EMP01 (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMMISSION,DEPTNO)VALUES(7499,'ALLEN','SALEMAN',7369,TO_DATE('87/12/20','YY/MM/DD'),1600,300,30)
INTO EMP01 (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMMISSION,DEPTNO)VALUES(7839,'KING','PRESIDENT',NULL,TO_DATE('81/02/08','YY/MM/DD'),5000,NULL,10)
SELECT * FROM DUAL;

SELECT * FROM DUAL;

ALTER TABLE EMP01
ADD CONSTRAINT EMP01_PK PRIMARY KEY (EMPNO);
ALTER TABLE EMP01
ADD CONSTRAINT EMP01_UK UNIQUE (ENAME);
ALTER TABLE EMP01
ADD CONSTRAINT EMP01_CK CHECK(SAL BETWEEN 800 AND 20000);
ALTER TABLE EMP01
RENAME COLUMN COMM TO COMMISSION;
ALTER TABLE EMP01-- 안됨
ADD CONSTRAINT EMP01_DEPTMENT_FK FOREIGN KEY(DEPTNO) REFERENCES DEPTMENT(DEPTNO);
ALTER TABLE EMP01
DROP CONSTRAINT EMP01_UK;


DESC EMP01;
SELECT * FROM EMP01;
--이미지 문제 3
CREATE TABLE MEMBERS(
ID VARCHAR2(20)NOT NULL,
NAME VARCHAR2(20)NOT NULL,
REGNO VARCHAR2(13)NOT NULL,
HP VARCHAR2(13)NOT NULL,
ADDRESS VARCHAR2(100)NOT NULL,
CONSTRAINT MEMBERS_PK PRIMARY KEY (ID)
);
DESC MEMBERS;
--이미지 문제 4
CREATE TABLE BOOK(
CODE NUMBER(4)NOT NULL,
TITLE VARCHAR2(50)NOT NULL,
COUNT NUMBER(6)NOT NULL,
PRICE NUMBER(10)NOT NULL,
PUBLISH VARCHAR2(50)NOT NULL,
CONSTRAINT BOOKS_PK PRIMARY KEY (CODE)
);
DESC BOOK;

SELECT * FROM EMPLOYEES WHERE ROWNUM >=3 AND <= 5;
SELECT ROWID FROM EMPLOYEES;
SELECT ROWNUM FROM EMPLOYEES;
DESC EMPLOYEES;
SELECT COUNT(*) AS 직업의 갯수 FROM (SELECT JOB_ID FROM EMPLOYEES GROUP BY JOB_ID);

--ROWID, ROWNUM 테이블에 행, ROW,INSTANCE, RECORD, TUPLE 라인번호를 자동설정한다.
SELECT * FROM EMPLOYEES WHERE ROWNUM <= 3;
SELECT ROWID FROM EMPLOYEES;
SELECT ROWNUM FROM EMPLOYEES;
--TAB : TABLE 각 데이터베이스마다 테이블내용을 보고 싶으면 TAB 조사하면 된다.
SELECT * FROM TAB;
--테이블 복사: EMPLOYEES2 : EMPLOYEES (NOT NULL 제약조건 복사 이루어진다.)
--PK,FK,UK,CHECK (무결성 제약조건.)
CREATE TABLE EMPLOYEES2
AS
SELECT * FROM EMPLOYEES;

DESC EMPLOYEES2;
-- AGE 숫자 3까지만 인정, 체크제약조건 0 < 나이 < 150
ALTER TABLE EMPLOYEES2
ADD AGE NUMBER(3) CHECK (AGE BETWEEN 0 AND 150);
-- AGE 숫자 4까지만 증가
ALTER TABLE EMPLOYEES2
MODIFY AGE NUMBER(4);
--AGE 삭제
ALTER TABLE EMPLOYEES2
DROP COLUMN AGE;
--FIRST_NAME VARCHAR2(20) =. VARCHAR2(22);
ALTER TABLE EMPLOYEES2
MODIFY FIRST_NAME VARCHAR2(30);
DESC EMPLOYEES2;
SELECT * FROM EMPLOYEES2;

CREATE TABLE EMPLOYEES3
AS
SELECT * FROM EMPLOYEES2;
DESC EMPLOYEES3;
SELECT * FROM  EMPLOYEES3;
ALTER TABLE EMPLOYEES2
DROP COLUMN FIRST_NAME;

ALTER TABLE EMPLOYEES3
ADD FIRST_NAME VARCHAR2(20);

CREATE TABLE EMP02
AS
SELECT * FROM EMPLOYEES;

CREATE TABLE EMP03
AS
SELECT * FROM EMP02;

ALTER TABLE EMP03
DROP COLUMN FIRST_NAME;

ALTER TABLE EMP03
ADD FIRST_NAME VARCHAR2(18);

DESC EMP03;

SELECT * FROM EMP02;

ALTER TABLE EMP02
MODIFY FIRST_NAME VARCHAR2(18);

--EMPLOYEES2 테이블삭제하고 ROLLBACK 사용하지 않고 복구시키는 방법 (휴지통복구)
DROP TABLE EMPLOYEES2;
SELECT * FROM TAB;
ROLLBACK;
-- 휴지통 구조
DESC RECYCLEBIN;
SELECT *FROM RECYCLEBIN;
-- 휴지통 복구
FLASHBACK TABLE EMPLOYEES2 TO BEFORE DROP;
SELECT * FROM EMPLOYEES2;
--휴지통 비우기
PURGE RECYCLEBIN;
--컬럼사이즈를 줄여서 복사하는 방법
CREATE TABLE TESTA(
    NAME CHAR(20) NOT NULL,
    AGE NUMBER(3) NOT NULL
);
INSERT INTO TESTA VALUES('1234567890',34);
INSERT INTO TESTA VALUES('12345678',60);
SELECT * FROM TESTA;
ALTER TABLE TESTA
MODIFY NAME CHAR(10);

DROP TABLE TESTB;
CREATE TABLE TESTB(
    NAME2 CHAR(10) NOT NULL,
    AGE2 NUMBER(3) NOT NULL
);
DESC TESTB;
DROP TABLE EMP01;
INSERT INTO TESTB(NAME2,AGE2)
SELECT SUBSTR(NAME,1,10),AGE FROM TESTA;
SELECT * FROM TESTB;
-- 테이블명 변경하는 법
SELECT * FROM TAB;
RENAME EMPLOYEES2 TO EMPLOYEES4;
-- 테이블을 설계한다. EMP01(번호,이름,직업,부서) 제약조건 NULL
CREATE TABLE EMP01(
    EMP_NO NUMBER(10),
    EMP_NAME VARCHAR2(10),
    EMP_JOB VARCHAR2(10),
    DEP_ID VARCHAR2(10)
);
DESC EMP01;
INSERT INTO EMP01 VALUES(10,'YSH','개백수',NULL);
--INSTANCE에 있는 값을 모두 없애라.
TRUNCATE TABLE EMP01;
SELECT * FROM EMP01;
-- DEP_ID NOT NULL 제약조건을 설정하라.
ALTER TABLE EMP01
MODIFY DEP_ID VARCHAR(10) NOT NULL;
INSERT INTO EMP01(EMP_NO,EMP_NAME,EMP_JOB,DEP_ID) VALUES(11,'LOSER','패배자','경비원');
INSERT INTO EMP01 VALUES(12,'KKK','ITDEV','LG');
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'EMP01';
-- UNIQUE 제약조건을 줄 것
DROP TABLE EMP05;
CREATE TABLE EMP05(
    EMP_NO NUMBER(10),
    EMP_NAME VARCHAR2(10),
    EMP_JOB VARCHAR2(10),
    DEP_ID VARCHAR2(10)
);
DESC EMP05;
ALTER TABLE EMP05
MODIFY EMP_NAME VARCHAR2(10)UNIQUE;
ALTER TABLE EMP05
DROP CONSTRAINT SYS_C008435;

SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'EMP05';
ALTER TABLE EMP05
ADD CONSTRAINT EMP_NAME_UK UNIQUE(EMP_NAME);
--UNIQUE 제약조건 위배되는 사항
INSERT INTO EMP05(EMP_NO,EMP_NAME,EMP_JOB,DEP_ID) VALUES(11,'LOSER','패배자','경비원');
INSERT INTO EMP05(EMP_NO,EMP_NAME,EMP_JOB,DEP_ID) VALUES(12,NULL,'패배자','경비원');
INSERT INTO EMP05(EMP_NO,EMP_NAME,EMP_JOB,DEP_ID) VALUES(13,NULL,'패배자','경비원');
SELECT * FROM EMP05;
--데이터 DICTIONARY (USER,ALL,DBA)
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'EMPLOYEES';
SELECT * FROM USER_TABLES WHERE TABLE_NAME = 'EMPLOYEES';

--문제(1)
CREATE TABLE TB_CUSTOMER(
    CUSTOMER_CD CHAR(7)PRIMARY KEY,
    CUSTOMER_NM VARCHAR2(20)NOT NULL,
    MW_FLG CHAR(1)NOT NULL CHECK(MW_FLG IN('M','W')),
    BIRTH_DAY CHAR(8)NOT NULL,
    PHONE_NUMBER VARCHAR2(16),
    EMAIL VARCHAR2(30),
    TOTAL_POINT NUMBER(10)DEFAULT 0,
    REG_DTTM CHAR(14)
);
SELECT * FROM TB_CUSTOMER;
--문제(2)
SELECT * FROM EMP01;
ALTER TABLE EMP01
ADD CREDATE DATE;
SELECT * FROM EMP01;
--문제(3)
CREATE TABLE STU_SCORE(
    STU_NUM VARCHAR2(4),
    KOR NUMBER(3)DEFAULT 0 NOT NULL,
    ENG NUMBER(3)DEFAULT 0 NOT NULL,
    MATH NUMBER(3)DEFAULT 0 NOT NULL,
    TOTAL NUMBER(3)DEFAULT 0 NOT NULL,
    AVER NUMBER(5,2)DEFAULT 0 NOT NULL,
    LESSON_CODE NUMBER
);
DROP TABLE STU_LESSON;
ALTER TABLE STU_SCORE ADD CONSTRAINT LESSON_CODE_FK FOREIGN KEY (LESSON_CODE)
REFERENCES STU_LESSON(LESSON_CODE);
ALTER TABLE STU_SCORE ADD CONSTRAINT STU_NUM_PK PRIMARY KEY (STU_NUM);
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'STU_SCORE';

INSERT INTO STU_SCORE VALUES(1,80,25,20,125,41.6,1);
INSERT INTO STU_SCORE VALUES(2,85,100,40,225,75.5,2);

CREATE TABLE STU_LESSON(
    LESSON_CODE NUMBER,
    LESSON_NAME VARCHAR2(24) NOT NULL
);
ALTER TABLE STU_LESSON ADD CONSTRAINT LESSON_CODE_PK PRIMARY KEY (LESSON_CODE);

INSERT INTO STU_LESSON VALUES(1,'감귤포장학과');
INSERT INTO STU_LESSON VALUES(2,'박스제조학과');
INSERT INTO STU_LESSON VALUES(3,'해녀다이빙학과');

SELECT * FROM STU_SCORE;
SELECT * FROM STU_LESSON;

--DEPT1 테이블 생성 및 데이터 입력
-- 부모테이블에서 참조된 기본키 값을 변경하거나 삭제를 진행할 때에는
-- 자식테이블에 참조키를 NULL 수정하거나, 해당된 튜플(레코드,행(ROW))을 삭제하면 된다.
CREATE TABLE
DEPT01(
    DEPTNO NUMBER(2),
    DNAME VARCHAR2(14),
    LOC VARCHAR2(13),
    CONSTRAINT DEPT01_PK PRIMARY KEY(DEPTNO)
);
INSERT INTO DEPT01 VALUES(10,'ACCOUNTING','NEW YORK');
INSERT INTO DEPT01 VALUES(20,'RESEARCH','DALLAS');
INSERT INTO DEPT01 VALUES(30,'DEVELOPMENT','KOREA');
SELECT * FROM DEPT01;
DELETE FROM DEPT01 WHERE DEPTNO = 10;
UPDATE DEPT01 SET DEPTNO = 40 WHERE DEPTNO = 20;
DELETE FROM DEPT01 WHERE DEPTNO = 10;

ALTER TABLE DEPT01
DISABLE PRIMARY KEY CASCADE;

--EMP01 테이블 생성 데이터를 입력 (자식 : 아무문제없다.
--단 UPDATE할 때 없는 부모키를 참조하면 오류가 난다.(NULL,다른 부모키로 UPDATE하면 문제없다.)
CREATE TABLE EMP01(
    EMPNO NUMBER(4), --제약조건1 (PK)
    ENAME VARCHAR2(10) NOT NULL, --제약조건2 (NN)
    JOB VARCHAR2(9),
    DEPTNO NUMBER(4), --제약조건3 (FK): 참조테이블 주의 : UPDATE,DELETE
    PRIMARY KEY (EMPNO),
    FOREIGN KEY(DEPTNO) REFERENCES DEPT01(DEPTNO)
);
INSERT INTO EMP01 VALUES(7499,'ALLEN','SALESMAN',10);
INSERT INTO EMP01 VALUES(7369,'SMITH','CLERK',20);
ALTER TABLE EMP01 DROP CONSTRAINT SYS_C008474;
ALTER TABLE EMP01
ADD CONSTRAINT EMP01_DEPT01_DEPTNO_FK
FOREIGN KEY(DEPTNO)REFERENCES DEPT01(DEPTNO) ON DELETE CASCADE;

SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'EMP01';
DROP TABLE EMP01;

SELECT * FROM EMP01;
UPDATE EMP01 SET DEPTNO = 10 WHERE EMPNO = 7369;
DELETE FROM EMP01 WHERE DEPTNO = 10;

--CROSS JOIN (가로생성)
SELECT * FROM EMPLOYEES; --107
SELECT * FROM DEPARTMENTS; --27
SELECT * FROM EMPLOYEES,DEPARTMENTS;
-- INNER JOIN (PK:부모,참조 = FK:자식,기본,주)
SELECT * FROM EMPLOYEES;
SELECT * FROM DEPARTMENTS;
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'EMPLOYEES';

SELECT E.FIRST_NAME, D.DEPARTMENT_ID,D.DEPARTMENT_NAME FROM EMPLOYEES E , DEPARTMENTS D
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID AND UPPER(E.FIRST_NAME) = UPPER('SUSAN');
-- INNER JOIN (ANSI JOIN)
SELECT E.FIRST_NAME, D.DEPARTMENT_ID, D.DEPARTMENT_NAME FROM EMPLOYEES E INNER JOIN DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
WHERE UPPER(E.FIRST_NAME) = UPPER('SUSAN');
-- INNER JOIN (ANSI JOIN USING) 기본기 속성명과 참조키에 속명명 같을 경우
-- ON을 사용하지 말 것,별칭은 사용하되 속성명에는 별칭을 붙이지 말 것.
SELECT E.FIRST_NAME, DEPARTMENT_ID, D.DEPARTMENT_NAME FROM EMPLOYEES E INNER JOIN DEPARTMENTS D
USING (DEPARTMENT_ID)
WHERE UPPER(E.FIRST_NAME) = UPPER('SUSAN');

--NON EQUAL JOIN = 연산자를 사용하지 않는다.(FK가 없다. = 사용할 수 없다.)
CREATE TABLE SALARYGRADE(
    GRADE NUMBER,
    MINSALARY NUMBER,
    MAXSALARY NUMBER
);
INSERT INTO SALARYGRADE(GRADE,MINSALARY,MAXSALARY) VALUES(1,2000,3000);
INSERT INTO SALARYGRADE(GRADE,MINSALARY,MAXSALARY) VALUES(2,3000,4500);
INSERT INTO SALARYGRADE(GRADE,MINSALARY,MAXSALARY) VALUES(3,4000,6000);
INSERT INTO SALARYGRADE(GRADE,MINSALARY,MAXSALARY) VALUES(4,6000,8000);
INSERT INTO SALARYGRADE(GRADE,MINSALARY,MAXSALARY) VALUES(5,8000,10000);
INSERT INTO SALARYGRADE(GRADE,MINSALARY,MAXSALARY) VALUES(6,10000,13000);
INSERT INTO SALARYGRADE(GRADE,MINSALARY,MAXSALARY) VALUES(7,13000,20000);
INSERT INTO SALARYGRADE(GRADE,MINSALARY,MAXSALARY) VALUES(8,20000,30000);

SELECT E.FIRST_NAME, E.SALARY, S.GRADE "등급"
FROM EMPLOYEES E, SALARYGRADE S 
WHERE E.SALARY BETWEEN S.MINSALARY AND S.MAXSALARY;
--OUTER JOIN = INNER JOIN (PK, FK) = LEFT OUTER JOIN, RIGHT OUTER JOIN, BOTH OUTER JOIN
SELECT E.FIRST_NAME, D.DEPARTMENT_ID, D.DEPARTMENT_NAME
FROM EMPLOYEES E, DEPARTMENTS D
WHERE E.DEPARTMENT_ID(+) = D.DEPARTMENT_ID;
--OUTER JOIN (ANSI RIGHT OUTER JOIN)
SELECT E.FIRST_NAME, D.DEPARTMENT_ID, D.DEPARTMENT_NAME
FROM EMPLOYEES E RIGHT OUTER JOIN DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID;
--OUTER JOIN (ANSI RIGHT OUTER JOIN)
SELECT E.FIRST_NAME, D.DEPARTMENT_ID, D.DEPARTMENT_NAME
FROM EMPLOYEES E RIGHT OUTER JOIN DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID;

--2007년도 상반기에 입사한 사원을 조사하라.
SELECT E.EMPLOYEE_ID, E.FIRST_NAME, E.SALARY , E.HIRE_DATE, D.DEPARTMENT_NAME
FROM EMPLOYEES E,DEPARTMENTS D
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID
AND(HIRE_DATE BETWEEN '2007/01/01' AND '2007/06/30');
--SELF JOIN (한개 테이블을 두개 테이블로 만들어 조인한다고 생각할 것)PK, 선언되지 않는 FK 생각할 것.
SELECT * FROM EMPLOYEES;
SELECT E2.EMPLOYEE_ID 사원ID, E2.FIRST_NAME 상사이름 , E2.SALARY 상사월급, E1.EMPLOYEE_ID 사원아이디, E1.FIRST_NAME 사원이름,E1.SALARY 상사월급
FROM EMPLOYEES E1, EMPLOYEES E2
WHERE E1.EMPLOYEE_ID = E2.MANAGER_ID;

SELECT E2.FIRST_NAME || ' 사원의 상사 이름은 ' || E1.FIRST_NAME || ' 입니다.'
FROM EMPLOYEES E1, EMPLOYEES E2
WHERE E1.EMPLOYEE_ID = E2.MANAGER_ID;
--ROWNUM(번호 자동설정 진행이 된다) 10개만 보고싶다.
SELECT * FROM EMPLOYEES WHERE ROWNUM < 10;
--SALARY 제일 많이 받는 사람 순으로 10명만 출력하시오.
SELECT * FROM EMPLOYEES;
SELECT FIRST_NAME, SALARY FROM EMPLOYEES WHERE ROWNUM <= 10 ORDER BY SALARY DESC;
SELECT * FROM EMPLOYEES ORDER BY SALARY DESC;
SELECT FIRST_NAME, SALARY FROM (SELECT * FROM EMPLOYEES ORDER BY SALARY DESC)
WHERE ROWNUM <= 10;
----------------------------------------
--과제
--미래 대학교 수강신청
--학과 테이블
CREATE TABLE SUBJECT(
    S_NO NUMBER(2)NOT NULL,
    S_NUM NUMBER(2) NOT NULL,
    S_NAME VARCHAR2(30) NOT NULL
);
--NOT NULL, PK
INSERT INTO SUBJECT VALUES(01,01,'컴퓨터학과');
INSERT INTO SUBJECT VALUES(02,02,'교육학과');
INSERT INTO SUBJECT VALUES(03,03,'신문방송학과');
INSERT INTO SUBJECT VALUES(04,04,'인터넷비즈니스학과');
INSERT INTO SUBJECT VALUES(05,05,'기술경영학과');

--학생 테이블
CREATE TABLE STUDENT(
    SD_NO NUMBER(5) NOT NULL,
    SD_NUM NUMBER(8) NOT NULL,
    SD_NAME VARCHAR2(15) NOT NULL,
    SD_ID VARCHAR2(30) NOT NULL,
    SD_PW VARCHAR2(30) NOT NULL,
    S_NUM NUMBER(2) NOT NULL,
    SD_JUMIN VARCHAR2(14) NOT NULL,
    SD_PHONE VARCHAR2(13) NOT NULL,
    SD_ADDRESS VARCHAR2(100) NOT NULL,
    SD_EMAIL VARCHAR2(50) NOT NULL,
    SD_DATE DATE NOT NULL
);
DROP TABLE STUDENT;
TRUNCATE TABLE STUDENT;
SELECT * FROM STUDENT;
--학생들 데이터 삽입
INSERT INTO STUDENT VALUES(01,90010001,'윤수현','YSH0812',258456,01,'950812-1XXXXXX','010-2943-3814','구리시 갈매동','ysh2616@naver.com',TO_DATE('950812','YY/MM/DD'));
INSERT INTO STUDENT VALUES(02,95010002,'김정수','javjsp',258456,02,'950812-1XXXXXX','010-2943-3814','수원시 병점동','ysh2616@naver.com',TO_DATE('950812','YY/MM/DD'));
INSERT INTO STUDENT VALUES(03,98040001,'공지영','gonji',258456,03,'950812-1XXXXXX','010-2943-3814','대전시 은행동','ysh2616@naver.com',TO_DATE('950812','YY/MM/DD'));
INSERT INTO STUDENT VALUES(04,02050001,'조수영','water',258456,04,'950812-1XXXXXX','010-2943-3814','부산시 반송동','ysh2616@naver.com',TO_DATE('950812','YY/MM/DD'));
--과목 테이블
CREATE TABLE LESSON(
    L_NO NUMBER(2) NOT NULL,
    L_NUM NUMBER() NOT NULL,
    L_NAME VARCHAR2(24) NOT NULL,
);
--수강 테이블
CREATE TABLE TRAINEE(
    T_NO NUMBER(3) NOT NULL,
    SD_NUM NUMBER(8) NOT NULL,
    L_NUM NUMBER() NOT NULL,
    T_SECTION VARCHAR2(10) NOT NULL,
    T_DATE DATE NOT NULL
);

----------------------------------
--다른문제
1.
SELECT E.FIRST_NAME AS "사원의 이름", E.HIRE_DATE AS 입사일,D.DEPARTMENT_NAME AS 부서명
FROM EMPLOYEES E INNER JOIN DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID WHERE D.DEPARTMENT_NAME = 'Sales';
SELECT * FROM EMPLOYEES;
2.
SELECT E.FIRST_NAME AS "직원의 이름", E.COMMISSION_PCT AS 커미션, D.DEPARTMENT_NAME AS 부서명
FROM EMPLOYEES E, DEPARTMENTS D
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID AND E.COMMISSION_PCT IS NOT NULL;
3.
SELECT FIRST_NAME AS "직원의 이름", DEPARTMENT_ID AS 부서번호
FROM EMPLOYEES WHERE DEPARTMENT_ID = (SELECT DEPARTMENT_ID
FROM EMPLOYEES WHERE FIRST_NAME = 'Guy');
SELECT DEPARTMENT_ID
FROM EMPLOYEES WHERE FIRST_NAME = 'Guy';

--SUSAN 사원이 해당되는 사원의 이름,부서번호, 부서명을 출력하시오(INNER JOIN)(EMPLOYEES, DEPARTMENTS)
SELECT E.FIRST_NAME, E.DEPARTMENT_ID, D.DEPARTMENT_NAME FROM EMPLOYEES E, DEPARTMENTS D
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID AND UPPER(E.FIRST_NAME) = UPPER('SUSAN');

SELECT DEPARTMENT_NAME FROM DEPARTMENTS
WHERE DEPARTMENT_ID = (SELECT DEPARTMENT_ID
FROM EMPLOYEES
WHERE FIRST_NAME = 'Susan');

--서브쿼리 Susan 6500,lex 17000 두사람의 월급 출력 할 것.
SELECT SALARY
FROM EMPLOYEES
WHERE FIRST_NAME IN ('Susan','Lex');
--Susan 또는 lex와 월급이 같은 직원의 이름,업무,급여를 출력하는 SELECT문을 작성하시오. (SUSAN,LEX는 제외)
SELECT FIRST_NAME,JOB_ID,SALARY FROM EMPLOYEES WHERE SALARY
IN(SELECT SALARY FROM EMPLOYEES WHERE FIRST_NAME
IN('Susan','Lex')) AND NOT UPPER(FIRST_NAME) IN('SUSAN','LEX');

-- 30번 소속 직원들 중에서 급여를 가장 많이 받은 사원보다
-- 더많은 급여를 받는 사람의 이름,급여를 출력하는 쿼리문 작성
SELECT FIRST_NAME,SALARY FROM EMPLOYEES
WHERE SALARY > ALL (SELECT SALARY FROM EMPLOYEES WHERE DEPARTMENT_ID = 30);
SELECT DEPARTMENT_ID ,MAX(SALARY) FROM EMPLOYEES GROUP BY DEPARTMENT_ID;
-- 테이블의 구조만 복사하기
DROP TABLE EMP03;
CREATE TABLE EMP03
AS
SELECT * FROM EMPLOYEES WHERE 1 = 0;
DESC EMP03;
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'EMPLOYEES';
-- DEPARTMENTS 테이블을 DEPT01 이름으로 구조 복사하시오.
DROP TABLE DEPT01;
CREATE TABLE DEPT01
AS
SELECT * FROM DEPARTMENTS WHERE 1 = 0;
SELECT * FROM DEPT01;
INSERT INTO DEPT01 SELECT * FROM DEPARTMENTS;
-- 10번 부서의 지역번호를 40번 부서의 지역번호(2400)로 변경하기 위해서 서브 쿼리를 사용하시오.
SELECT DEPARTMENT_ID , LOCATION_ID FROM DEPARTMENTS WHERE DEPARTMENT_ID = 10;
SELECT DEPARTMENT_ID , LOCATION_ID FROM DEPARTMENTS WHERE DEPARTMENT_ID = 40;
UPDATE DEPARTMENTS SET LOCATION_ID = 1700 WHERE DEPARTMENT_ID = 40;
UPDATE DEPARTMENTS
SET LOCATION_ID = (SELECT LOCATION_ID FROM DEPARTMENTS WHERE DEPARTMENT_ID = 10)
WHERE DEPARTMENT_ID = 40;
--TRANSACTION 테스팅
COMMIT; --트랜잭션이 끝남과 동시에 새로 트랜잭션이 새로 시작함.
DROP TABLE DEPT02;
CREATE TABLE DEPT02
AS
(SELECT * FROM DEPARTMENTS);
SELECT * FROM DEPT02;
DELETE FROM DEPT02; --DROP,TRUNCATE,DELETE 차이점
SELECT * FROM DEPT02;
ROLLBACK;
SELECT * FROM DEPT02;
DELETE FROM DEPT02 WHERE DEPARTMENT_ID = 20;
SELECT * FROM DEPT02;
COMMIT;
ROLLBACK;
SELECT * FROM DEPT02;
-- VIEW
-- VIEW_EMP01 VIEW (가상테이블) 만든다. 실제테이블(사원),가상주체(사원아이디,사원이름,부서아이디 = 10)
CREATE VIEW VIEW_EMP01
AS
SELECT EMPLOYEE_ID,FIRST_NAME,DEPARTMENT_ID
FROM EMPLOYEES
WHERE DEPARTMENT_ID = 10 ORDER BY EMPLOYEE_ID ASC;

SELECT * FROM VIEW_EMP01;

CREATE TABLE TABLE_EMP01
AS
SELECT EMPLOYEE_ID,FIRST_NAME,DEPARTMENT_ID
FROM EMPLOYEES
WHERE DEPARTMENT_ID = 10 ORDER BY EMPLOYEE_ID ASC;

SELECT * FROM TABLE_EMP01;

SELECT * FROM EMPLOYEES;
SELECT * FROM WHERE 