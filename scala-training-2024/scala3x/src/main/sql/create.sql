create table IF NOT EXISTS PRODUCTS (ID INTEGER NOT NULL,NAME VARCHAR NOT NULL,STOCK INTEGER NOT NULL);
create table IF NOT EXISTS SALES (ID INTEGER NOT NULL,DATE DATE NOT NULL,PRODUCT_ID INTEGER NOT NULL,QUANTITY INTEGER NOT NULL);

insert into PRODUCTS (ID, NAME, STOCK) values (1, 'coke', 10);
insert into PRODUCTS (ID, NAME, STOCK) values (2, 'pepsi', 20);
insert into PRODUCTS (ID, NAME, STOCK) values (3, 'fanta', 30);

insert into SALES (ID, DATE, PRODUCT_ID, QUANTITY) values (1, '2024-12-12', 2, 1);


create table IF NOT EXISTS TOLL (ID INTEGER NOT NULL, DATE DATE NOT NULL, CAR_TYPE VARCHAR NOT NULL, LICENSE_PLATE VARCHAR NOT NULL, TOLL_AMOUNT INTEGER NOT NULL);

insert into TOLL (ID, DATE, CAR_TYPE, LICENSE_PLATE, TOLL_AMOUNT) values (1, '2024-12-12', 'SUV', 'ABC123', 13);
insert into TOLL (ID, DATE, CAR_TYPE, LICENSE_PLATE, TOLL_AMOUNT) values (1, '2024-12-12', 'Scooter', 'CBX443', 7);
insert into TOLL (ID, DATE, CAR_TYPE, LICENSE_PLATE, TOLL_AMOUNT) values (1, '2024-12-12', 'CyberTruck', 'TXT001', 20);