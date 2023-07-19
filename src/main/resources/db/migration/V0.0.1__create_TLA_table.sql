CREATE TABLE three_letter_abbreviation (
    name varchar(10) NOT NULL,
    meaning varchar(100) NOT NULL,
    alternative_meanings varchar(255),
    url varchar(255),
    status varchar(100) NOT NULL,
    PRIMARY KEY (name)
)