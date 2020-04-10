DROP TABLE IF EXISTS Bike_product CASCADE;
DROP TABLE IF EXISTS Order_table CASCADE;
DROP TABLE IF EXISTS Order_product CASCADE;
DROP TABLE IF EXISTS User_table CASCADE;
DROP TABLE IF EXISTS AccountDetails CASCADE;
DROP TABLE IF EXISTS Customer_order CASCADE;

CREATE TABLE Bike_product(
   Product_ID serial PRIMARY KEY,
	Name varchar(25) NOT NULL,
	Price float NOT NULL,
	Color varchar(25) NOT NULL,
	Type_of_frame varchar(25) NOT NULL,
	Quantity integer NOT NULL
);
CREATE TABLE Order_table (
   Order_ID serial PRIMARY KEY,
	Order_Value float NOT NULL,
	Date date NOT NULL   
);
CREATE TABLE Order_product(
	Order_ID serial,
	Bike_product_ID serial,
	Quantity integer NOT NULL,
	PRIMARY KEY (Order_ID, Bike_product_ID),
CONSTRAINT Order_product_Order_ID_fkey FOREIGN KEY (Order_ID)
      REFERENCES Order_table (Order_ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT Order_product_Bike_product_ID_fkey FOREIGN KEY (Bike_product_ID)
      REFERENCES Bike_product (Product_ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE AccountDetails(
	AccountDetails_ID serial PRIMARY KEY,
	Date_of_creation DATE NOT NULL,
	Password VARCHAR(25) not null,
	Login varchar(25) not null
);

CREATE TABLE User_table (
   	User_ID serial unique,
	First_name varchar(25) NOT NULL,
	Last_name varchar(25) NOT NULL,
	Password varchar(25) NOT NULL,
	Login varchar(25) NOT NULL,
	Admin_user BIT NOT NULL,
	Account_Details_ID serial,
PRIMARY KEY (User_ID, Account_Details_ID),
CONSTRAINT User_table_Account_Details_ID_fkey FOREIGN KEY (Account_Details_ID)
      REFERENCES AccountDetails (AccountDetails_ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION

);

CREATE TABLE Customer_order(
	Customer_order_ID serial,
	User_ID serial,
	Order_ID serial,
	PRIMARY KEY (Customer_order_ID, User_ID, Order_ID),
CONSTRAINT Customer_order_User_ID_fkey FOREIGN KEY (User_ID)
      REFERENCES User_table (User_ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
CONSTRAINT Customer_order_Order_ID_fkey FOREIGN KEY (Order_ID)
      REFERENCES Order_Table (Order_ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);




