create table tb_file(
    IDX number,
    BOARD_IDX number not null, --해당 파일이 어떤 게시글에 속해있는지 알기 위한 컬럼
    ORIGINAL_FILE_NAME varchar2(260 byte) not null, --원본 파일 이름
    STORED_FILE_NAME varchar2(36 byte) not null, --변경된 파일 이름
    FILE_SIZE number, --사이즈
    CREA_DTM date default sysdate not null, --생성 시간
    CREA_ID varchar2(30 byte) not null, --생성 아이디
    DEL_GB varchar2(1 byte) default 'N' not null, --첨부파일 삭제 여부
    primary key (IDX)
);

create sequence SEQ_TB_FILE_IDX --시퀀스
    start with 1
    increment by 1
    NOMAXVALUE
    nocache;