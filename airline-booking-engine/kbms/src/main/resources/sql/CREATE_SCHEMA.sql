DROP SCHEMA IF EXISTS TESTDB;

CREATE SCHEMA IF NOT EXISTS TESTDB;

DROP TABLE IF EXISTS KBMS_AIRLINE;

CREATE TABLE KBMS_AIRLINE
(
  ID          NUMBER(19)         NOT NULL,
  CREATE_DATE TIMESTAMP(6),
  IP_ADDRESS  VARCHAR2(255 CHAR),
  UPDATE_DATE TIMESTAMP(6),
  USER_ID     NUMBER(19),
  VERSION     NUMBER(10)         NOT NULL,
  CODE        VARCHAR2(3 CHAR)   NOT NULL,
  ICAO_CODE   VARCHAR2(3 CHAR),
  NAME        VARCHAR2(255 CHAR) NOT NULL,
  STATUS      VARCHAR2(255 CHAR) NOT NULL,
  COUNTRY_ID  NUMBER(19)         NOT NULL
);


DROP TABLE IF EXISTS KBMS_AIRLINE_AUD;

CREATE TABLE KBMS_AIRLINE_AUD
(
  ID         NUMBER(19) NOT NULL,
  REV        NUMBER(10) NOT NULL,
  REVTYPE    NUMBER(3),
  CODE       VARCHAR2(3 CHAR),
  ICAO_CODE  VARCHAR2(3 CHAR),
  NAME       VARCHAR2(255 CHAR),
  STATUS     VARCHAR2(255 CHAR),
  COUNTRY_ID NUMBER(19)
);

DROP TABLE IF EXISTS KBMS_AIRPORT;

CREATE TABLE KBMS_AIRPORT
(
  ID          NUMBER(19)         NOT NULL,
  CREATE_DATE TIMESTAMP(6),
  IP_ADDRESS  VARCHAR2(255 CHAR),
  UPDATE_DATE TIMESTAMP(6),
  USER_ID     NUMBER(19),
  VERSION     NUMBER(10)         NOT NULL,
  CODE        VARCHAR2(3 CHAR)   NOT NULL,
  DESCRIPTION VARCHAR2(255 CHAR),
  ICAO_CODE   VARCHAR2(4 CHAR),
  NAME        VARCHAR2(255 CHAR) NOT NULL,
  STATUS      VARCHAR2(255 CHAR) NOT NULL,
  DIVISION_ID NUMBER(19)
);

DROP TABLE IF EXISTS KBMS_AIRPORT_AUD;

CREATE TABLE KBMS_AIRPORT_AUD
(
  ID          NUMBER(19) NOT NULL,
  REV         NUMBER(10) NOT NULL,
  REVTYPE     NUMBER(3),
  CODE        VARCHAR2(3 CHAR),
  DESCRIPTION VARCHAR2(255 CHAR),
  ICAO_CODE   VARCHAR2(3 CHAR),
  NAME        VARCHAR2(255 CHAR),
  STATUS      VARCHAR2(255 CHAR),
  DIVISION_ID NUMBER(19)
);

DROP TABLE IF EXISTS KBMS_DIVISION;
CREATE TABLE KBMS_DIVISION
(
  ID          NUMBER(19)         NOT NULL,
  CREATE_DATE TIMESTAMP(6),
  IP_ADDRESS  VARCHAR2(255 CHAR),
  UPDATE_DATE TIMESTAMP(6),
  USER_ID     NUMBER(19),
  VERSION     NUMBER(10)         NOT NULL,
  CODE        VARCHAR2(3 CHAR)   NOT NULL,
  NAME        VARCHAR2(255 CHAR) NOT NULL,
  STATUS      VARCHAR2(255 CHAR) NOT NULL,
  TYPE        VARCHAR2(255 CHAR) NOT NULL,
  COUNTRY_ID  NUMBER(19),
  PARENT_ID   NUMBER(19)
);

DROP TABLE IF EXISTS KBMS_DIVISION_AIRPORTS;
CREATE TABLE KBMS_DIVISION_AIRPORTS
(
  KBMS_DIVISION_ID NUMBER(19) NOT NULL,
  AIRPORTS_ID      NUMBER(19) NOT NULL
);
DROP TABLE IF EXISTS KBMS_DIVISION_AIRPORTS_AUD;
CREATE TABLE KBMS_DIVISION_AIRPORTS_AUD
(
  REV              NUMBER(10) NOT NULL,
  KBMS_DIVISION_ID NUMBER(19) NOT NULL,
  AIRPORTS_ID      NUMBER(19) NOT NULL,
  REVTYPE          NUMBER(3)
);


DROP TABLE IF EXISTS KBMS_DIVISION_AUD;
CREATE TABLE KBMS_DIVISION_AUD
(
  ID         NUMBER(19) NOT NULL,
  REV        NUMBER(10) NOT NULL,
  REVTYPE    NUMBER(3),
  CODE       VARCHAR2(3 CHAR),
  NAME       VARCHAR2(255 CHAR),
  STATUS     VARCHAR2(255 CHAR),
  COUNTRY_ID NUMBER(19),
  PARENT_ID  NUMBER(19)
);
DROP TABLE IF EXISTS KBMS_COUNTRY;
CREATE TABLE KBMS_COUNTRY
(
  ID          NUMBER(19)         NOT NULL,
  CREATE_DATE TIMESTAMP(6),
  IP_ADDRESS  VARCHAR2(255 CHAR),
  UPDATE_DATE TIMESTAMP(6),
  USER_ID     NUMBER(19),
  VERSION     NUMBER(10)         NOT NULL,
  CODE        VARCHAR2(255 CHAR) NOT NULL,
  NAME        VARCHAR2(255 CHAR) NOT NULL,
  STATUS      VARCHAR2(255 CHAR) NOT NULL,
  REGION_ID   NUMBER(19)         NOT NULL
);

DROP TABLE IF EXISTS KBMS_COUNTRY_AUD;
CREATE TABLE KBMS_COUNTRY_AUD
(
  ID      NUMBER(19) NOT NULL,
  REV     NUMBER(10) NOT NULL,
  REVTYPE NUMBER(3),
  CODE    VARCHAR2(255 CHAR),
  NAME    VARCHAR2(255 CHAR),
  STATUS  VARCHAR2(255 CHAR),
  REGION  NUMBER(19)
);
DROP TABLE IF EXISTS KBMS_FLIGHT_STATUS;
CREATE TABLE KBMS_FLIGHT_STATUS
(
  ID               NUMBER(19)         NOT NULL,
  CREATE_DATE      TIMESTAMP(6),
  IP_ADDRESS       VARCHAR2(255 CHAR),
  UPDATE_DATE      TIMESTAMP(6),
  USER_ID          NUMBER(19),
  VERSION          NUMBER(10)         NOT NULL,
  AIDX_STATUS_TYPE VARCHAR2(255 CHAR) NOT NULL,
  CODE             VARCHAR2(255 CHAR) NOT NULL,
  DESCRIPTION      VARCHAR2(255 CHAR),
  NAME             VARCHAR2(255 CHAR)
);

DROP TABLE IF EXISTS KBMS_FLIGHT_STATUS_AUD;
CREATE TABLE KBMS_FLIGHT_STATUS_AUD
(
  ID               NUMBER(19) NOT NULL,
  REV              NUMBER(10) NOT NULL,
  REVTYPE          NUMBER(3),
  AIDX_STATUS_TYPE VARCHAR2(255 CHAR),
  CODE             VARCHAR2(255 CHAR),
  DESCRIPTION      VARCHAR2(255 CHAR),
  NAME             VARCHAR2(255 CHAR)
);

DROP TABLE IF EXISTS KBMS_REGION;
CREATE TABLE KBMS_REGION
(
  ID          NUMBER(19)         NOT NULL,
  CREATE_DATE TIMESTAMP(6),
  IP_ADDRESS  VARCHAR2(255 CHAR),
  UPDATE_DATE TIMESTAMP(6),
  USER_ID     NUMBER(19),
  VERSION     NUMBER(10)         NOT NULL,
  CODE        VARCHAR2(255 CHAR) NOT NULL,
  NAME        VARCHAR2(255 CHAR) NOT NULL,
  STATUS      VARCHAR2(255 CHAR) NOT NULL,
  PARENT_ID   NUMBER(19)
);

DROP TABLE IF EXISTS KBMS_REGION_AUD;
CREATE TABLE KBMS_REGION_AUD
(
  ID        NUMBER(19) NOT NULL,
  REV       NUMBER(10) NOT NULL,
  REVTYPE   NUMBER(3),
  CODE      VARCHAR2(255 CHAR),
  NAME      VARCHAR2(255 CHAR),
  STATUS    VARCHAR2(255 CHAR),
  PARENT_ID NUMBER(19)
);

DROP TABLE IF EXISTS REVINFO;
CREATE TABLE REVINFO
(
  REV      NUMBER(10) NOT NULL,
  REVTSTMP NUMBER(19)
);

DROP TABLE IF EXISTS KBMS_AIDX_CODE_SET;

CREATE TABLE KBMS_AIDX_CODE_SET
(
  ID          NUMBER(19)       NOT NULL,
  CREATE_DATE TIMESTAMP(6),
  IP_ADDRESS  VARCHAR2(255 CHAR),
  UPDATE_DATE TIMESTAMP(6),
  USER_ID     NUMBER(19),
  VERSION     NUMBER(10)       NOT NULL,
  CODE        VARCHAR2(4 CHAR) NOT NULL,
  TYPE        VARCHAR(255)     NOT NULL,
  DESCRIPTION VARCHAR(255 CHAR),
  CODE_SET    VARCHAR(10 CHAR) NOT NULL

);

DROP TABLE IF EXISTS KBMS_AIDX_CODE_SET_AUD;
CREATE TABLE KBMS_AIDX_CODE_SET_AUD
(
  ID          NUMBER(19)        NOT NULL,
  REV         NUMBER(10)        NOT NULL,
  REVTYPE     NUMBER(3),
  CODE        VARCHAR2(4 CHAR)  NOT NULL,
  TYPE        VARCHAR2(255 CHAR),
  DESCRIPTION VARCHAR2(255 CHAR),
  CODE_SET    VARCHAR2(10 CHAR) NOT NULL,
);

COMMIT;

CREATE UNIQUE INDEX UK_1T4RTL92FDPBG1T5JCMI61WT6 ON KBMS_COUNTRY
(NAME);

CREATE UNIQUE INDEX UK_2HRG9VJPONHY2XJHU2YGNX6O7 ON KBMS_DIVISION_AIRPORTS
(AIRPORTS_ID);

CREATE UNIQUE INDEX UK_7OCAC0CRFGP03XI44MQR57SMR ON KBMS_FLIGHT_STATUS
(CODE);

CREATE UNIQUE INDEX UK_9PF4D4CNLA1ETW300BMGX1AUT ON KBMS_AIRPORT
(CODE);

CREATE UNIQUE INDEX UK_L68H4CN2WMECV13ST7031179Q ON KBMS_DIVISION
(CODE);

-- CREATE UNIQUE INDEX UK_LW05CE3S48C9BME204590VBQ ON KBMS_AIRPORT
-- (ICAO_CODE);

CREATE UNIQUE INDEX UK_NQJEWBKJ6P18XLNLPBMQ6UNAA ON KBMS_REGION
(CODE);

CREATE UNIQUE INDEX UK_OWIOJ1HWYTBO1STUI3PGWMHEV ON KBMS_REGION
(NAME);

CREATE UNIQUE INDEX UK_PM1NQEPAC0318IVASNSFRBC0D ON KBMS_COUNTRY
(CODE);

-- CREATE UNIQUE INDEX UK_RC9J4EBE1WQ87SUKKQ8JUAXC4 ON KBMS_AIRLINE
-- (ICAO_CODE);

ALTER TABLE KBMS_AIRLINE ADD
PRIMARY KEY (ID);
-- ALTER TABLE KBMS_AIRLINE ADD CONSTRAINT UK_RC9J4EBE1WQ87SUKKQ8JUAXC4
-- UNIQUE (ICAO_CODE);

ALTER TABLE KBMS_AIRLINE_AUD ADD
PRIMARY KEY
  (ID, REV);

ALTER TABLE KBMS_AIRPORT ADD
PRIMARY KEY (ID);
ALTER TABLE KBMS_AIRPORT ADD CONSTRAINT UK_9PF4D4CNLA1ETW300BMGX1AUT
UNIQUE (CODE);

-- ALTER TABLE KBMS_AIRPORT ADD CONSTRAINT UK_LW05CE3S48C9BME204590VBQ
-- UNIQUE (ICAO_CODE);

ALTER TABLE KBMS_AIRPORT_AUD ADD
PRIMARY KEY
  (ID, REV);

ALTER TABLE KBMS_DIVISION ADD
PRIMARY KEY (ID);
ALTER TABLE KBMS_DIVISION ADD CONSTRAINT UK_L68H4CN2WMECV13ST7031179Q
UNIQUE (CODE);

ALTER TABLE KBMS_DIVISION_AIRPORTS ADD
PRIMARY KEY
  (KBMS_DIVISION_ID, AIRPORTS_ID);
ALTER TABLE KBMS_DIVISION_AIRPORTS ADD CONSTRAINT UK_2HRG9VJPONHY2XJHU2YGNX6O7
UNIQUE (AIRPORTS_ID);

ALTER TABLE KBMS_DIVISION_AIRPORTS_AUD ADD
PRIMARY KEY
  (REV, KBMS_DIVISION_ID, AIRPORTS_ID);

ALTER TABLE KBMS_DIVISION_AUD ADD
PRIMARY KEY
  (ID, REV);

ALTER TABLE KBMS_COUNTRY ADD
PRIMARY KEY (ID);
ALTER TABLE KBMS_COUNTRY ADD CONSTRAINT UK_1T4RTL92FDPBG1T5JCMI61WT6
UNIQUE (NAME);

ALTER TABLE KBMS_COUNTRY ADD CONSTRAINT UK_PM1NQEPAC0318IVASNSFRBC0D
UNIQUE (CODE);

ALTER TABLE KBMS_COUNTRY_AUD ADD
PRIMARY KEY
  (ID, REV);

ALTER TABLE KBMS_FLIGHT_STATUS ADD
PRIMARY KEY (ID);
ALTER TABLE KBMS_COUNTRY ADD CONSTRAINT UK_7OCAC0CRFGP03XI44MQR57SMR
UNIQUE (CODE);

ALTER TABLE KBMS_FLIGHT_STATUS_AUD ADD
PRIMARY KEY
  (ID, REV);

ALTER TABLE KBMS_REGION ADD
PRIMARY KEY (ID);
ALTER TABLE KBMS_COUNTRY ADD CONSTRAINT UK_NQJEWBKJ6P18XLNLPBMQ6UNAA
UNIQUE (CODE);

ALTER TABLE KBMS_COUNTRY ADD CONSTRAINT UK_OWIOJ1HWYTBO1STUI3PGWMHEV
UNIQUE (NAME);

ALTER TABLE KBMS_REGION_AUD ADD
PRIMARY KEY
  (ID, REV);

ALTER TABLE REVINFO ADD
PRIMARY KEY
  (REV);

ALTER TABLE KBMS_AIRLINE ADD
CONSTRAINT FK_91S0EBAIEMR6IO0B6EW5D62WJ
FOREIGN KEY (COUNTRY_ID)
REFERENCES KBMS_COUNTRY (ID);

ALTER TABLE KBMS_AIRLINE_AUD ADD
CONSTRAINT FK_870V5G28U6AQX239H3YUDA6TV
FOREIGN KEY (REV)
REFERENCES REVINFO (REV);

ALTER TABLE KBMS_AIRPORT ADD
CONSTRAINT FK_AV4PRCLHIMAUY9IEYGSNOOFI9
FOREIGN KEY (DIVISION_ID)
REFERENCES KBMS_DIVISION (ID);

ALTER TABLE KBMS_AIRPORT_AUD ADD
CONSTRAINT FK_3YH0T7DWU5NU7M67A54X8WDC
FOREIGN KEY (REV)
REFERENCES REVINFO (REV);

ALTER TABLE KBMS_DIVISION ADD
CONSTRAINT FK_8B01TT3NRYP1NCBHWQ0FI888E
FOREIGN KEY (PARENT_ID)
REFERENCES KBMS_DIVISION (ID);
ALTER TABLE KBMS_DIVISION ADD CONSTRAINT FK_INVL2VPPPWURLX93NLCR6SI4P
FOREIGN KEY (COUNTRY_ID)
REFERENCES KBMS_COUNTRY (ID);

ALTER TABLE KBMS_DIVISION_AIRPORTS ADD
CONSTRAINT FK_2HRG9VJPONHY2XJHU2YGNX6O7
FOREIGN KEY (AIRPORTS_ID)
REFERENCES KBMS_AIRPORT (ID);
ALTER TABLE KBMS_DIVISION_AIRPORTS ADD CONSTRAINT FK_DGXWN5MQVH0S09RDSDC2UB0EH
FOREIGN KEY (KBMS_DIVISION_ID)
REFERENCES KBMS_DIVISION (ID);

ALTER TABLE KBMS_DIVISION_AIRPORTS_AUD ADD
CONSTRAINT FK_6Q2EJK9RD316ECA9F83A223KH
FOREIGN KEY (REV)
REFERENCES REVINFO (REV);

ALTER TABLE KBMS_DIVISION_AUD ADD
CONSTRAINT FK_B6J5M8GLB8G70HXMQ1AHKA5YW
FOREIGN KEY (REV)
REFERENCES REVINFO (REV);

ALTER TABLE KBMS_COUNTRY_AUD ADD
CONSTRAINT FK_EE38H1D3SXA8BFLD9N16SCJ6A
FOREIGN KEY (REV)
REFERENCES REVINFO (REV);

ALTER TABLE KBMS_FLIGHT_STATUS_AUD ADD
CONSTRAINT FK_MCLRXP1KRFBFS0V93KDLDUFXB
FOREIGN KEY (REV)
REFERENCES REVINFO (REV);

ALTER TABLE KBMS_REGION ADD
CONSTRAINT FK_IEWA88TESLWXK9Y370E12UY9B
FOREIGN KEY (PARENT_ID)
REFERENCES KBMS_REGION (ID);

ALTER TABLE KBMS_REGION_AUD ADD
CONSTRAINT FK_A5KBSLOR19BD33PBIKF3CP943
FOREIGN KEY (REV)
REFERENCES REVINFO (REV);


ALTER TABLE KBMS_AIDX_CODE_SET ADD
CONSTRAINT KBMS_AIDX_CODE_SET_PK
PRIMARY KEY
  (ID);

ALTER TABLE KBMS_AIDX_CODE_SET
ADD CONSTRAINT KBMS_AIDX_CODE_SET_CODE_U
UNIQUE (CODE, CODE_SET);

ALTER TABLE KBMS_AIDX_CODE_SET_AUD ADD
PRIMARY KEY
  (ID, REV, CODE, CODE_SET);

ALTER TABLE KBMS_AIDX_CODE_SET_AUD ADD
CONSTRAINT FK_AIDX_CODE_SET_AUD
FOREIGN KEY (REV)
REFERENCES REVINFO (REV);
