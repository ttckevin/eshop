LOAD DATA INFILE 'C:\Users\T1me\Desktop\eshop\database\data.csv' 
INTO TABLE customers 
FIELDS TERMINATED BY ':' 
ENCLOSED BY ''
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

create table store_orders(
	order_id VARCHAR(128) not null,
    deliveryId VARCHAR(128) not null,
    name VARCHAR(32) not null,
	address VARCHAR(128) not null,
    email VARCHAR(128) not null,
	status VARCHAR(32) not null,
    orderDate date not null,
    primary key(orderId),
        constraint fk_name 
        foreign key(name) references customers(name)
);

create table order_status(
	order_id VARCHAR(128) not null,
    deliveryId VARCHAR(128) not null,
	status ENUM ('pending','dispatched'),
    staus_update DATETIME,
    primary key(deliveryId),
        constraint fk_order_id 
        foreign key(order_id) references store_orders(order_id)
);