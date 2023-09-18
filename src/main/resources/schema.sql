CREATE TABLE IF NOT EXISTS PLAYERS(
     ID INT AUTO_INCREMENT PRIMARY KEY
    ,NAME              VARCHAR(20) NOT NULL
    ,SURNAME           VARCHAR(20) NOT NULL
    ,NATIONALITY       INTEGER  NOT NULL
    ,TEAM_ID           INTEGER  NOT NULL
);
CREATE TABLE IF NOT EXISTS TEAMS(
     ID INT AUTO_INCREMENT PRIMARY KEY
    ,NAME              VARCHAR(20) NOT NULL
    ,ABBREVIATION      VARCHAR(3) NOT NULL
);
CREATE TABLE IF NOT EXISTS STADIUMS(
     ID INT AUTO_INCREMENT PRIMARY KEY
    ,NAME              VARCHAR(20) NOT NULL
    ,LOCATION          VARCHAR(10) NOT NULL
    ,TEAM_ID           INTEGER  NOT NULL
);