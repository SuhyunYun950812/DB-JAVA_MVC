CREATE TABLE cart (
    cart_index       INTEGER NOT NULL,
    cart_total       INTEGER,
    cart_count       INTEGER NOT NULL,
    cart_date        DATE NOT NULL,
    menu_menu_index  INTEGER NOT NULL,
    member_member_id NCHAR(15) NOT NULL,
    menu_name        NCHAR(20) NOT NULL
)
LOGGING;

ALTER TABLE cart ADD CONSTRAINT cart_pk PRIMARY KEY ( cart_index );

CREATE TABLE member (
    member_id      NCHAR(15) NOT NULL,
    member_pw      NCHAR(15) NOT NULL,
    member_name    NCHAR(5) NOT NULL,
    member_phone   INTEGER NOT NULL,
    member_manager INTEGER NOT NULL
)
LOGGING;

COMMENT ON COLUMN member.member_manager IS
    '1 = 관리자
2 = 회원';

ALTER TABLE member ADD CONSTRAINT member_id_pk PRIMARY KEY ( member_id );

CREATE TABLE menu (
    menu_name  NCHAR(20) NOT NULL,
    menu_price INTEGER NOT NULL,
    menu_inven INTEGER
)
LOGGING;

ALTER TABLE menu ADD CONSTRAINT menu_pk PRIMARY KEY ( menu_name );

ALTER TABLE cart
    ADD CONSTRAINT member_id
        FOREIGN KEY ( member_member_id )
            REFERENCES member ( member_id )
            NOT DEFERRABLE;

ALTER TABLE cart
    ADD CONSTRAINT menu_index
        FOREIGN KEY ( menu_name )
            REFERENCES menu ( menu_name )
            NOT DEFERRABLE;