# JavaDB Final Project

## Entities of the program

* Customer
* Sale
* Merchandise
* Supplier
* Employee
* Mail List
* Contractor

## Functionality TODO:

### General
* ~Build general UI~
* ~Connect UI to database~
* Add authentication for user accounts and managers (Optional)


### Entity Specific

* Customer
  * Add Customer
  * Remove Customer
  * Update customer info

* Sale
  * Issue Sale
  * Refund Sale

* Merchandise
  * Check quantity in stock
  * Add new product
  * Remove existing product
  * Order new product
  * Update product info

* Supplier
  * Add a supplier
  * Remove a supplier
  * Update supplier info

* Employee
  * Add employee
  * Remove employee
  * Update employee info

* Mailing List
  * Add a new person to existing mailing list
  * Remove a person from an existing mailing list
  * Email recipients of mailing list something

### To Use Database With Ryerson's Servers:

1. Log in and port forward with `ssh -L 9876:oracle.scs.ryerson.ca:1521`
2. Set DB_URL to `jdbc:oracle:thin:@localhost:1234:orcl`
3. Set USER to your username
4. Set PASS to your password
5. Use `Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);` to open a connection.
