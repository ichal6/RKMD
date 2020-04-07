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


