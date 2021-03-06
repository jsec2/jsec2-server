--TABLE DE APPLICATIONS
CREATE TABLE APPLICATION(
	ID NUMBER(19,0) NOT NULL,
	NAME VARCHAR2(50) NOT NULL,
	DESCRIPTION VARCHAR2(128) NOT NULL,
	CONSTRAINT PK_APP_ID PRIMARY KEY ("ID"),
	CONSTRAINT UQ_APP_NAME UNIQUE ("NAME")
);
CREATE sequence "SEQ_APPLICATION";

-- TABLE DE REGRAS
CREATE TABLE RULE(
	ID NUMBER(19,0) NOT NULL,
	EXP_SQL VARCHAR2(2048) NOT NULL,
	DESCRIPTION VARCHAR2(64) NOT NULL,
	CONSTRAINT PK_RUL_ID PRIMARY KEY("ID")
);
CREATE sequence "SEQ_RULE";

-- TABLE DE PAPEIS
CREATE TABLE ROLE(
	ID NUMBER(19,0) NOT NULL,
	NAME VARCHAR2(24) NOT NULL,
	CONSTRAINT PK_ROL_ID PRIMARY KEY("ID"),
	CONSTRAINT UQ_ROL_NAME UNIQUE ("NAME")
);
CREATE sequence "SEQ_ROLE";

--TABLE DE LOTACAO
CREATE TABLE LOCATION(
	ID NUMBER(19,0) NOT NULL,
	NAME VARCHAR2(128) NOT NULL,
	LOC_PROPERTIES XMLTYPE,
	LOCATION_SUP_ID NUMBER(19,0),
	CONSTRAINT PK_LOC_ID PRIMARY KEY("ID"),
	CONSTRAINT UQ_LOC_NAME UNIQUE ("NAME")
);
CREATE sequence "SEQ_LOCATION";
COMMENT ON COLUMN LOCATION.LOCATION_SUP_ID IS 'ID DA LOTAÇÃO SUPERIOR DA LOTAÇÃO CORRENTE';

--TABLE DE USUARIO
CREATE TABLE JSEC2USER(
	ID NUMBER(19,0) NOT NULL,
	LOGIN VARCHAR2(24) NOT NULL,
	NAME VARCHAR2(128) NOT NULL,
	PASSWD VARCHAR2(16),
	USR_PROPERTIES XMLTYPE,
	ENABLED NUMBER(1,0),
	CONSTRAINT PK_USR_ID PRIMARY KEY("ID"),
	CONSTRAINT UQ_USR_LOGIN UNIQUE ("LOGIN"),
	CONSTRAINT UQ_USR_NAME UNIQUE ("NAME")
);
CREATE sequence "SEQ_JSEC2USER";

CREATE TABLE POLICY(
	ID NUMBER(19,0) NOT NULL,
	APPLICATION_ID NUMBER(19,0) NOT NULL,
	ROLE_ID NUMBER(19,0) NOT NULL,
	RULE_ID NUMBER(19,0) NOT NULL,
	ENABLED NUMBER(1,0),
	CONSTRAINT PK_PO_ID PRIMARY KEY("ID")
);
CREATE sequence "SEQ_POLICY";
ALTER TABLE "POLICY" ADD CONSTRAINT "APPLICATION_FK" FOREIGN KEY ("APPLICATION_ID") REFERENCES APPLICATION("ID");
ALTER TABLE "POLICY" ADD CONSTRAINT "ROLE_FK" FOREIGN KEY ("ROLE_ID") REFERENCES ROLE("ID");
ALTER TABLE "POLICY" ADD CONSTRAINT "RULE_FK" FOREIGN KEY ("RULE_ID") REFERENCES RULE("ID");

CREATE TABLE JSEC2USER_LOCATION(
	JSEC2USER_ID NUMBER(19,0) NOT NULL,
	LOCATION_ID NUMBER(19,0) NOT NULL,
	PRIMARY_LOCATION NUMBER(1,0),
	ENABLED NUMBER(1,0),
	CONSTRAINT PK_JSEC2USRLOC_ID PRIMARY KEY("JSEC2USER_ID", "LOCATION_ID")
);
ALTER TABLE "JSEC2USER_LOCATION" ADD CONSTRAINT "JSEC2USRLOC_USR_FK" FOREIGN KEY ("JSEC2USER_ID") REFERENCES JSEC2USER("ID");
ALTER TABLE "JSEC2USER_LOCATION" ADD CONSTRAINT "JSEC2USRLOC_LOC_FK" FOREIGN KEY ("LOCATION_ID") REFERENCES LOCATION("ID");


