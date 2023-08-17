-- Database Configuration
DROP DATABASE IF EXISTS bankjava;
CREATE DATABASE bankjava;

\c bankjava;


-- Create UUID Extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


-- Officer
-- Division Table
CREATE TABLE Division (
  DivisionId UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  DivisionName VARCHAR(100) NOT NULL,
  DivisionDesc VARCHAR(500) NOT NULL
);

-- Role Table
CREATE TABLE Role (
  RoleId UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  DivisionId UUID,
  RoleName VARCHAR(100) NOT NULL,
  RoleDesc VARCHAR(500) NOT NULL,

  FOREIGN KEY (DivisionId) REFERENCES Division(DivisionId)
    ON DELETE RESTRICT
);
-- End Officer


-- User
-- Customer Table
CREATE TABLE Customer (
  CustomerId UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  CustomerFullname VARCHAR(250) NOT NULL,
  CustomerNickname VARCHAR(50) NOT NULL,
  CustomerBirthDate DATE NOT NULL,
  CustomerEmail VARCHAR(50) NOT NULL UNIQUE,
  CustomerPhoneNumber VARCHAR(50) NOT NULL UNIQUE,
  CustomerPassword VARCHAR(64) NOT NULL,
  CustomerIsActive BOOLEAN
);

-- Employer Table
CREATE TABLE Employer (
  EmployerId UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  CustomerId UUID,
  RoleId UUID,
  Incomes BIGINT,

  FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)
    ON DELETE RESTRICT,

  FOREIGN KEY (RoleId) REFERENCES Role(RoleId)
    ON DELETE RESTRICT
);

-- Wallet Table
CREATE TABLE Wallet (
  WalletId UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  CustomerId UUID,
  WalletAddress VARCHAR(19) NOT NULL UNIQUE,
  Balance BIGINT NOT NULL,

  FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)
    ON DELETE RESTRICT
);
-- End User


-- Transaction
-- Transaction Type Table
CREATE TABLE TransactionType (
  TransactionTypeId UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  TransactionTypeName VARCHAR(100) NOT NULL,
  TransactionTypeDesc VARCHAR(500) NOT NULL
);

-- Transaction Table
CREATE TABLE Transaction (
  TransactionId UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  TransactionTypeId UUID,
  CustomerId UUID,
  DestinationId UUID,
  TransactionDesc VARCHAR(500) NOT NULL,
  TransactionAmount INT NOT NULL,
  TransactionDate DATE NOT NULL,

  FOREIGN KEY (TransactionTypeId) REFERENCES TransactionType(TransactionTypeId)
    ON DELETE RESTRICT,

  FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)
    ON DELETE RESTRICT,

  FOREIGN KEY (DestinationId) REFERENCES Customer(CustomerId)
    ON DELETE RESTRICT
);
-- End Transaction