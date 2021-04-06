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

INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9781524763169', 'A Promised Land', 29.99, 'Biography', 'Barack Obama', 'https://images-na.ssl-images-amazon.com/images/I/41L5qgUW2fL._SX327_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780221076992','The Answer is... Reflections on My Life', 27.71, 'Biography', 'Alex Trebe', 'https://images-na.ssl-images-amazon.com/images/I/41agWXl9gBL._SX331_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780470149287','Building a Web Site For Dummies' ,28.22,'Textbook', 'David A. Crowder','https://images-na.ssl-images-amazon.com/images/I/51Rj5uyFTIL._SX396_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780006479888','A Game of Thrones: A Song of Ice and Fire' ,13.37,'Fantasy', 'George R. R. Martin', 'https://images-na.ssl-images-amazon.com/images/I/51aTnsl0MPL._SX303_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780048232731','The Hobbit' ,29.69,'Fantasy', 'J. R. R. Tolkien','https://images-na.ssl-images-amazon.com/images/I/51n+ej8DlnL._SX378_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780345331359','Cosmos' ,24,'Science', 'Carl Sagan', 'https://images-na.ssl-images-amazon.com/images/I/51dAmkybCEL._SX322_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9781783293698','Interstellar' ,10.49,'Science-Fiction', 'Greg Keyes', 'https://images-na.ssl-images-amazon.com/images/I/51H4jXmToqL._SX300_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780765317384','Enders Game' ,9.89,'Science-Fiction', 'Orson Scott Card', 'https://images-na.ssl-images-amazon.com/images/I/51sfTXF6eUL._SX302_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780716769118','Calculus: Early Transcendentals' ,210.15,'Textbook', 'James Stewart', 'https://images-na.ssl-images-amazon.com/images/I/51xUmFAz+VL._SX423_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780789331144','The Bobs Burgers Burger Book: Real Recipes for Joke Burgers' ,24.74,'Cookbook', 'Loren Bouchard', 'https://images-na.ssl-images-amazon.com/images/I/51joZSTuB5L._SX349_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780307743688','The Stand' ,11.99,'Horror', 'Stephen King', 'https://m.media-amazon.com/images/I/41M5nekhDNL.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780439554930','Harry Potter and the Philosophers Stone' ,10.99,'Fantasy', 'J.K. Rowling', 'https://m.media-amazon.com/images/I/51UoqRAxwEL.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9781760508111','Star Wars The High Republic: Into The Dark' ,22.99,'Science-Fiction', 'Claudia Gray', 'https://dynamic.indigoimages.ca/books/1368057284.jpg?scaleup=true&width=614&maxheight=614&quality=85&lang=en');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9781786892706','The Midnight Library' ,30.54,'Science-Fiction', 'Matt Haig', 'https://images-na.ssl-images-amazon.com/images/I/419X9dVWmJL._SX353_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9781338747690','Ground Zero' ,10.99,'Children', 'Alan Gratz', 'https://images-na.ssl-images-amazon.com/images/I/51OK5z7CCcL._SX329_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9781260440218','Java: A Beginners Guide, Eighth Edition' ,44.84,'Textbook', 'Herbert Schildt', 'https://images-na.ssl-images-amazon.com/images/I/51KDndebj7L._SX403_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780785123491','Infinity Gauntlet' ,20.29,'Fantasy', 'Jim Starlin (Contributor), George Perez (Illustrator), Ron Lim (Illustrator)', 'https://images-na.ssl-images-amazon.com/images/I/51UlKuVWViL._SX324_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9781432885793','LORE' ,19.80,'Fantasy', 'Alexandra Bracken', 'https://images-na.ssl-images-amazon.com/images/I/51GzewxBJrL._SX329_BO1,204,203,200_.jpg');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780241448304','How To Avoid A Climate Disaster: The Solutions We Have And The Breakthroughs We Need' ,25,'Non-Fiction', 'Bill Gates', 'https://dynamic.indigoimages.ca/books/0735280444.jpg?scaleup=true&width=614&maxheight=614&quality=85&lang=en');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9783785727003','The Evening And The Morning' ,33.6,'Historical-Fiction','Ken Follett','https://dynamic.indigoimages.ca/books/0525954988.jpg?scaleup=true&width=614&maxheight=614&quality=85&lang=en');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780140180909','Heart of Darkness' ,15,'Drama','Joseph Conrad', 'https://dynamic.indigoimages.ca/books/184931103x.jpg?maxheight=200&width=200&quality=85&sale=74&lang=en');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780141393391','Frankenstein' ,39.99,'Horror','Mary Shelley','https://dynamic.indigoimages.ca/books/198214615x.jpg?maxheight=200&width=200&quality=85&sale=9&lang=en');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780063059412','THE ROSE CODE: A NOVEL' ,15,'Drama','Kate Quinn','https://dynamic.indigoimages.ca/books/0063060442.jpg?width=200&quality=85&maxheight=200&sale=39&lang=en');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9781443462877','CROSSROADS: MY STORY OF TRAGEDY' ,25,'Historical-Fiction','Kaleb Dahlgren','https://dynamic.indigoimages.ca/books/144346287x.jpg?width=200&quality=85&maxheight=200&sale=24&lang=en');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780241434567','THE PUSH' ,15,'Historical-Fiction','Ashley Audrain','https://dynamic.indigoimages.ca/books/0735239894.jpg?width=200&quality=85&maxheight=200&sale=39&lang=en');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9781681196282','A COURT OF SILVER FLAMES' ,26.6,'Fantasy','Sarah J. Maas','https://dynamic.indigoimages.ca/books/168119628x.jpg?width=200&quality=85&maxheight=200&sale=30&lang=en');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9781250759665','WE BEGIN AT THE END' ,15,'Biography','Chris Whitaker','https://dynamic.indigoimages.ca/books/1250793769.jpg?width=200&quality=85&maxheight=200&sale=39&lang=en');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780545935180','DOG MAN: MOTHERING HEIGHTS: FROM THE CREATOR OF CAPTAIN' ,12,'Children','Dav Pilkey','https://dynamic.indigoimages.ca/books/0525656766.jpg?width=200&quality=85&maxheight=200&sale=32&lang=en');
INSERT INTO BookStore2021.Book (bid, title, price, category, author, picture_link) VALUES ('9780525656760','THE BEAUTY OF LIVING TWICE' ,25,'Biography', 'Sharon Stone','https://dynamic.indigoimages.ca/books/1338680455.jpg?width=200&quality=85&maxheight=200&sale=24&lang=en');


create table BookStore2021.Review(
	reviewID int not null auto_increment,
	bid varchar(20) not null, 
    review varchar(150),
    rating int not null,
    primary key(reviewID),
    foreign key(bid) references BookStore2021.Book(bid)
);
insert into BookStore2021.Review(bid, review, rating) values('9781524763169', 'Hello World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('9780221076992', 'Hello World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('9780470149287', 'Hello World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('9780006479888', 'Hello World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('9780048232731', 'Hello World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('9781524763169', 'bye World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('9781524763169', 'hi World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('9781524763169', 'hai World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('9780048232731', 'help World', 5); 
insert into BookStore2021.Review(bid, review, rating) values('9780048232731', 'hi World', 5); 


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

INSERT INTO BookStore2021.Event (day, bid, eventtype) VALUES ('12202015', '9781524763169', 'VIEW');

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

INSERT INTO BookStore2021.POItem (orderId, bid, price, quantity) VALUES (1,'9781524763169',  '20', '1');
INSERT INTO BookStore2021.POItem (orderId, bid, price, quantity) VALUES (1,'9780221076992',  '20', '1');
