CREATE TABLE Basics AS SELECT * FROM CSVREAD('/Users/adminn/workspace/gtm/title.basics.csv');
CREATE TABLE Ratings AS SELECT * FROM CSVREAD('/Users/adminn/workspace/gtm/title.ratings.csv');
CREATE TABLE Principals AS SELECT * FROM CSVREAD('/Users/adminn/workspace/gtm/title.principals.csv');

create unique index BASICS_TCONST_uindex on BASICS (TCONST);
create unique index Ratings_TCONST_uindex on Ratings (TCONST);
create index Principals_TCONST_uindex on Principals (TCONST);
create index Principals_NCONST_uindex on Principals (NCONST);