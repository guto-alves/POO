DROP TABLE IF EXISTS Author;
DROP TABLE IF EXISTS Author_Book;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Publisher;
DROP TABLE IF EXISTS Loan;
DROP TABLE IF EXISTS Address;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS Phone;

CREATE TABLE Publisher (
    Name VARCHAR (30) PRIMARY KEY NOT NULL,
    Address VARCHAR (50),
    Phone VARCHAR (15)
);

CREATE TABLE Book (
    ISBN VARCHAR (20) PRIMARY KEY NOT NULL,
    Title VARCHAR (100) NOT NULL,
    Description VARCHAR (200),
    EditionNumber INT NOT NULL,
    Year VARCHAR (4) NOT NULL,
    PublisherName VARCHAR (30) NOT NULL,
    FOREIGN KEY (PublisherName) 
        REFERENCES Publisher (Name)
);

CREATE TABLE Author (
    FirstName VARCHAR (20) NOT NULL,
    LastName VARCHAR (30) NOT NULL,
    PRIMARY KEY (FirstName, LastName)
);    

CREATE TABLE Author_Book (
    AuthorFirstName VARCHAR (20) NOT NULL,
    AuthorLastName VARCHAR (30) NOT NULL,
    BookISBN VARCHAR (20) NOT NULL,
    PRIMARY KEY (AuthorFirstName, AuthorLastName, BookISBN),
    FOREIGN KEY (AuthorFirstName, AuthorLastName) 
        REFERENCES Author (FirstName, LastName),
    FOREIGN KEY (BookISBN) 
        REFERENCES Book (ISBN)
);

CREATE TABLE Address (
    ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    Street VARCHAR (30) NOT NULL,
    Number INT NOT NULL,
    Complement VARCHAR (50),
    PostalCode CHAR (8) NOT NULL
);

CREATE TABLE Customer (
    RG VARCHAR (9) PRIMARY KEY NOT NULL,
    CPF VARCHAR (11) NOT NULL,
    Name VARCHAR (80) NOT NULL,
    Email VARCHAR (255),
    AddressId INTEGER NOT NULL,
    FOREIGN KEY (AddressId) 
        REFERENCES Address (ID)
);

CREATE TABLE Phone (
    Number INT NOT NULL,
    CustomerRG  VARCHAR (9) NOT NULL,
    PRIMARY KEY (Number, CustomerRG),
    FOREIGN KEY (CustomerRg) 
        REFERENCES Customer (RG)
);

CREATE TABLE Employee (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Name VARCHAR (80) NOT NULL,
    Phone INT,
    Role VARCHAR (30) NOT NULL,
    Email VARCHAR (255) NOT NULL UNIQUE,
    Password VARCHAR (8) NOT NULL
);

CREATE TABLE Loan (
    LoanDate DATE NOT NULL,
    ReturnDate DATE NOT NULL,
    DateReturned DATE,
    CustomerRG VARCHAR (9) NOT NULL,
    BookISBN VARCHAR (20) NOT NULL,
    EmployeeId INTEGER NOT NULL,
    PRIMARY KEY (LoanDate, CustomerRG, BookISBN),
    FOREIGN KEY (CustomerRG)
        REFERENCES Customer (RG),
    FOREIGN KEY (BookISBN)
        REFERENCES Book (ISBN),
    FOREIGN KEY (EmployeeId) 
        REFERENCES Employee (ID)
);


SELECT 
   Customer.Name, Phone.Number
FROM 
    Phone LEFT JOIN Customer
        ON Phone.CustomerRG = Customer.RG
WHERE
    Customer.Name = 'G';


SELECT 
    Customer.RG, Customer.CPF, Customer.Name, Customer.Email,  
    Address.ID, Address.Street, Address.Number, Address.Complement, Address.PostalCode 
FROM 
    Customer INNER JOIN Address
        ON Customer.AddressID = Address.ID 
WHERE 
    Customer.Name = 'A';

SELECT 
    *
FROM
    Loan;
    
    
SELECT 
    Customer.Name, Loan.LoanDate, Loan.ReturnDate, Book.Title
FROM
    Customer INNER JOIN Loan
        ON Customer.RG = Loan.CustomerRG
    INNER JOIN Book
        ON Loan.BookISBN = Book.ISBN;
        
        
SELECT 
    Book.Title, Loan.LoanDate
FROM
    Loan INNER JOIN Book
        ON Loan.BookISBN = Book.ISBN
WHERE
    Loan.CustomerRG = 5555;


SELECT 
    Author.FirstName, Author.LastName
FROM 
    Book INNER JOIN Author_Book
        ON Book.ISBN = Author_Book.BookISBN
    INNER JOIN Author 
        ON Author_Book.AuthorFirstName = Author.FirstName AND
         Author_Book.AuthorLastName = Author.LastName
WHERE
    Book.ISBN = '555-555-55';
