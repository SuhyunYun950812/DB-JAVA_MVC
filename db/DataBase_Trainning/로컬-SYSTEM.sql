ALTER SESSION SET "_ORACLE_SCRIPT"=true;

-- 기존 사용자 삭제(MODEL)
drop user Model cascade;
-- 새로운 사용자 생성
create user javaid identified by 123456
    default tablespace users
    temporary tablespace temp;
    
-- 권한설정
grant connect, resource, dba to javaid;
-- 테이블 스페이스 (데이타베이스 저장될 공간 생성)
CREATE TABLESPACE FIRSTDATA
DATAFILE 'D:\oraclexe\oradata\XE\FIRST01.DBF' SIZE 10M;

-- 기존 사용자 삭제(MODEL)
drop user KIOSK cascade;
-- 새로운 사용자 생성
create user KIOSK identified by 1234
    default tablespace users
    temporary tablespace temp;
    
-- 권한설정
grant connect, resource, dba to KIOSK;

-- 테이블 스페이스 (데이타베이스 저장될 공간 추가) 1m
ALTER TABLESPACE FIRSTDATA
ADD DATAFILE 'D:\oraclexe\oradata\XE\FIRST02.DBF' SIZE 1M;

-- 테이블 스페이스 (데이타베이스 저장될 공간 크기 수정하는 방법: 작게는 안된다.)
ALTER DATABASE DATAFILE 'D:\oraclexe\oradata\XE\FIRST02.DBF' RESIZE 10M;

-- 테이블 스페이스 (데이타베이스 저장될 공간 자동 설정하는 방법)
ALTER DATABASE
DATAFILE 'D:\oraclexe\oradata\XE\FIRST01.DBF'
AUTOEXTEND ON
NEXT 1M
MAXSIZE 20M;

--1)시스템 권한을 가진자가 계정을 설정 2) TEST 비밀번호 1234 3)CONNECT와 RESOURCE와 DBA권한
CREATE USER TEST IDENTIFIED BY 1234
DEFAULT TABLESPACE USERS
TEMPORARY TABLESPACE TEMP;

GRANT CONNECT, RESOURCE, DBA TO TEST;