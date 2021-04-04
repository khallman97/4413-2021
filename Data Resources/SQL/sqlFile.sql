drop table if exists BookStore2021.Review;
drop table if exists BookStore2021.Event;
DROP TABLE if exists BookStore2021.POItem;
DROP TABLE if exists BookStore2021.Book;
DROP TABLE if exists BookStore2021.Orders;
DROP TABLE if exists BookStore2021.Address;
drop table if exists BookStore2021.Users;
CREATE TABLE BookStore2021.Book (
    bid VARCHAR(20) NOT NULL,
    title VARCHAR(60) NOT NULL,
    price INT NOT NULL,
    category ENUM('Biography','Non-Fiction', 'Science' , 'Science-Fiction' , 'Textbook' , 'Cookbook' , 'Horror' , 'Fantasy', 'Children' ,'Adventure' ,'Drama' , 'Historical-Fiction') NOT NULL,
    author VARCHAR(60),
    picture_link VARCHAR(100),
    PRIMARY KEY(bid)
);

INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('b001', 'A Promised Land', 29.99, 'Biography', 'Barack Obama', 'https://images-na.ssl-images-amazon.com/images/I/41L5qgUW2fL._SX327_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b002','The Answer is... Reflections on My Life', 27.71, 'Biography');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b003','Building a Web Site For Dummies' ,28.22,'Textbook');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b004','A Game of Thrones: A Song of Ice and Fire' ,13.37,'Fantasy');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b005','The Hobbit' ,29.69,'Fantasy');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b006','Cosmos' ,24,'Science');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b007','Interstellar' ,10.49,'Science-Fiction');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b008','Enders Game' ,9.89,'Science-Fiction');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b009','Calculus: Early Transcendentals' ,210.15,'Textbook');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b010','The Bobs Burgers Burger Book: Real Recipes for Joke Burgers' ,24.74,'Cookbook');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b011','The Stand' ,11.99,'Horror');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b012','Harry Potter and the Philosophers Stone' ,10.99,'Fantasy');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b013','Star Wars The High Republic: Into The Dark' ,22.99,'Science-Fiction');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b014','The Midnight Library' ,30.54,'Science-Fiction');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b015','Ground Zero' ,10.99,'Children');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b016','Java: A Beginners Guide, Eighth Edition' ,44.84,'Textbook');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b017','Infinity Gauntlet' ,20.29,'Fantasy');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b018','LORE' ,19.80,'Fantasy');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b019','How To Avoid A Climate Disaster: The Solutions We Have And The Breakthroughs We Need' ,25,'Non-Fiction');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b020','The Evening And The Morning' ,33.6,'Historical-Fiction');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b021','Heart of Darkness' ,15,'Drama');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b022','Frankenstein' ,39.99,'Horror');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b023','THE ROSE CODE: A NOVEL' ,15,'Drama');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b024','CROSSROADS: MY STORY OF TRAGEDY' ,25,'Historical-Fiction');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b025','THE PUSH' ,15,'Historical-Fiction');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b026','A COURT OF SILVER FLAMES' ,26.6,'Fantasy');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b027','WE BEGIN AT THE END' ,15,'Biography');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b028','DOG MAN: MOTHERING HEIGHTS: FROM THE CREATOR OF CAPTAIN' ,12,'Children');
INSERT INTO BookStore2021.Book (bid, title, price, category) VALUES ('b029','THE BEAUTY OF LIVING TWICE' ,25,'Biography');


create table BookStore2021.Review(
	reviewID int not null auto_increment,
	bid varchar(20) not null, 
    review varchar(150),
    rating int not null,
    primary key(reviewID),
    foreign key(bid) references BookStore2021.Book(bid)
);
insert into BookStore2021.Review(bid, review, rating) values('b001', 'Hello World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('b002', 'Hello World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('b003', 'Hello World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('b004', 'Hello World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('b005', 'Hello World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('b001', 'bye World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('b001', 'hi World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('b001', 'hai World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('b005', 'help World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('b005', 'hi World', 5); 


CREATE TABLE BookStore2021.Address (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
street VARCHAR(100) NOT NULL,
province VARCHAR(20) NOT NULL,
country VARCHAR(20) NOT NULL,
zip VARCHAR(20) NOT NULL,
PRIMARY KEY(id)
);

INSERT INTO BookStore2021.Address (street, province, country, zip) VALUES ('123 Yonge St', 'ON',
'Canada', 'K1E 6T5' );

create table BookStore2021.Users(
    user_name varchar(20) not null ,
    name varchar(20) not null, 
    addrId int NOT NULL,
    password int not null,
    type varchar(20),
    primary key(user_name)
);

insert into BookStore2021.Users(user_name, name, addrId, password, type) values('admin','admin',1, '123456', 'admin');




CREATE TABLE BookStore2021.Event (
day varchar(8) NOT NULL,
bid varchar(20) not null,
eventtype varchar(20) NOT NULL,
FOREIGN KEY(bid) REFERENCES BookStore2021.Book(bid)
);

INSERT INTO BookStore2021.Event (day, bid, eventtype) VALUES ('12202015', 'b001', 'VIEW');

CREATE TABLE BookStore2021.Orders (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
user_name varchar(20) not null,
status ENUM('ORDERED','PROCESSED','DENIED') NOT NULL,
addressId INT UNSIGNED NOT NULL,
poItems int, /* number of items in cart */
orderTotalCost double,
PRIMARY KEY(id),
INDEX (addressId),
FOREIGN KEY (user_name) REFERENCES BookStore2021.Users (user_name) ON DELETE CASCADE,
FOREIGN KEY (addressId) REFERENCES BookStore2021.Address (id) ON DELETE CASCADE
);

INSERT INTO BookStore2021.Orders (user_name, status, addressId, poItems, orderTotalCost) VALUES ('admin', 'PROCESSED', 1, 2 , 40.00);

CREATE TABLE BookStore2021.POItem (
orderId       INT UNSIGNED NOT NULL,
bid      VARCHAR(20) NOT NULL,
price    INT NOT NULL,
quantity INT NOT NULL,
PRIMARY KEY(orderId,bid),
INDEX (orderId),
FOREIGN KEY(orderId) REFERENCES Orders(id) ON DELETE CASCADE,
INDEX (bid),FOREIGN KEY(bid) REFERENCES Book(bid) ON DELETE CASCADE
);

INSERT INTO BookStore2021.POItem (orderId, bid, price, quantity) VALUES (1,'b001',  '20', '1');
INSERT INTO BookStore2021.POItem (orderId, bid, price, quantity) VALUES (1,'b002',  '20', '1');
