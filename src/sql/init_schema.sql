create  database pancake_database;

use pancake_database;

CREATE TABLE user_details
( user_id varchar (40) not null,
user_name varchar(20) not null,
user_password varchar(100),
primary key (user_id) );


CREATE TABLE pancake
( pancake_id varchar (40) not null,
strawberry bool ,
blueberry bool,
 order_id varchar (40) not null,
creation_timestamp varchar(100),
primary key (pancake_id) );


CREATE TABLE orders
( order_id varchar (40) not null,
user_id varchar (40) not null,
creation_timestamp varchar(100),
primary key (order_id) );

CREATE TABLE cart
( cart_id varchar (40) not null,
strawberry bool ,
blueberry bool,
pancake bool,
creation_timestamp varchar(100),
primary key (cart_id) );
GRANT ALL PRIVILEGES ON * . * TO 'mysqluser'@'localhost';
CREATE USER 'mysqluser'@'localhost' IDENTIFIED BY 'pass123';


ALTER USER 'mysqluser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'pass123';
Insert into user_details (user_id,user_name,user_password) VALUES ("a","abc","pass");

