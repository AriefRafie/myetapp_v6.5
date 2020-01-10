CREATE OR REPLACE TRIGGER TBLINBOXNOTIFIKASI_BI    
before insert on     TBLINBOXNOTIFIKASI    for each row
begin    if :new.ID_INBOXNOTIFIKASI    is null    then SELECT (SELECT kod_negeri FROM tbllookupstate) || TO_CHAR (SYSDATE, 'YY') || TBLINBOXNOTIFIKASI_SEQ.NEXTVAL into 
:new.ID_INBOXNOTIFIKASI            from dual;    end if;    end;
/
