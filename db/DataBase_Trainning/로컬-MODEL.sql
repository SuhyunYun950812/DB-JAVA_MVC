CREATE TABLE buytbl (
    no       INTEGER NOT NULL,
    prodname NCHAR(10) NOT NULL,
    price    INTEGER NOT NULL,
    amount   INTEGER NOT NULL,
    mobile   NCHAR(12) NOT NULL
);

ALTER TABLE buytbl ADD CONSTRAINT buytbl_pk PRIMARY KEY ( no );

CREATE TABLE usertbl (
    mobile    NCHAR(12) NOT NULL,
    username  NCHAR(4) NOT NULL,
    birthyear INTEGER,
    addr      NCHAR(20)
);

ALTER TABLE usertbl ADD CONSTRAINT usertbl_pk PRIMARY KEY ( mobile );

ALTER TABLE buytbl
    ADD CONSTRAINT buytbl_usertbl_fk FOREIGN KEY ( mobile )
        REFERENCES usertbl ( mobile );