# Product Delivery

### Requirements second phase

- Database: MySQL
- Workbench: MySQL Workbench

1. DatabaseConfiguration class:
	- getDatabaseConnection()
	- closeDatabaseConnection()

2. RepositoryHelper class:
	- executeSql(Connection connection, String sql)
	- executeUpdateSql(Connection connection, String sql)
	- executeQuerySql(Connection connection, String sql)

3. DatabaseSetup class:
	- createTables()
    - addRows()
    - deleteAllRows()
    - dropAllTables()
    - showTable(String tableName)

4. FoodRepository, DrinkRepository, BookRepository, FlowerRepository, MedicineRepository:
	- product adding (INSERT :ballot_box_with_check:)
    - product reading by name (READ :ballot_box_with_check:)
    - product price updating by name (UPDATE :ballot_box_with_check:)
    - product deleting by name (DELETE :ballot_box_with_check:)
    - mapper function

*SQL scripts are in the following files: QueryAddRows.txt, QueryCreateTables.txt, QueryDeleteAllRows.txt, QueryDropAllTables.txt*

5. AuditService class:
	- logAction(String action)
    - Audit.csv file

### Requirements first phase

1. 19 actions (min. 8 actions :ballot_box_with_check:)
2. 21 classes + 6 interfaces (min. 10 classes :ballot_box_with_check:)
3. Private/Protected attributes :ballot_box_with_check:
4. Collections used: List -> ArrayList, Set -> HashSet :ballot_box_with_check:
5. Inheritance :ballot_box_with_check:
6. 4 service classes (min. 1 service class :ballot_box_with_check:)
7. Main class which calls services :ballot_box_with_check:

## Product (abstract)
- name
- price
- weight
- measurements

  ### Drink
	- isAlcoholic
	- hasSugar
	- calories
    - ingredients

  ### Food
	- ingredients
	- calories
	- allergens

  ### Book
	- author
	- numberOfPages
	- releaseDate

  ### Flower
	- species
	- color

  ### Bouquet
	- speciesUsed (List -> ArrayList)
	- scentPallete
    - wrapping

  ### Medicine
	- ingredients
	- allergens
	- contraindications
	- contraindications

## User (abstract)
- name
- email
- password
- phoneNumber

  ### Customer
	- preferredAddress
    - preferredPaymentMethod

  ### Owner
	- shop

  ### DeliveryEmployee
	- carNumber
	- averageOrdersPerDay
    - averageDeliveryTime

  ### Administrator
	- licenseNumber

## Shop (abstract)
- name
- owner
- address
- rating
- deliveryEmployees (List -> ArrayList)
- products (List -> ArrayList)

  ### Restaurant
	- foodMenu (List -> ArrayList)
	- drinksMenu (List -> ArrayList)

  ### Bar
	- drinksMenu (List -> ArrayList)

  ### Library
	- books (List -> ArrayList)

  ### Florist
	- flowers (Set -> HashSet)
	- bouquets (Set -> HashSet)

  ### Pharmacy
	- medicines (List -> ArrayList)

## Shared.Order
- orderId
- customer
- shop
- deliveryEmployee
- orderStatus
- orderDate
- deliveryDate
- deliveryAddress
- paymentMethod
- totalPrice
- products (List -> ArrayList)

## IRegistration (Interface)
- logIn()
- logOut()
- registerAdmin()
- registerOwner()
- registerCustomer()
- registerDeliveryEmployee()

  ### Registration (Singleton)
	- users (Set -> HashSet)
	- currentUser

## Data (Singleton)
- shops (List -> ArrayList)
- products (List -> ArrayList)
- orders (List -> ArrayList)

## IService (Interface)
- showMenu()
- useMenu()

	### IAdminService (Interface)
	- listAllShops()
    - listAllProducts()
    - listAllOrders()
    - addShop()
    - removeShop()
    - addProduct()
    - removeProduct()
    - removeOrder()
	
	#### AdminService (Singleton)
	- data
	
	### IOwnerService (Interface)
    - listAllShops()
	- listAllProducts()
	- listAllOrders()
	- addProduct()
	- removeProduct()
	- removeOrder()
    
	#### OwnerService (Singleton)
    - data
    - orders (List -> ArrayList)
    - shops (List -> ArrayList)

	### ICustomerService (Interface)
	- listAllShops()
    - listAllProducts()
    - addOrder()
    - removeOrder()
	
	#### CustomerService (Singleton)
    - data
	
	### IDeliveryEmployeeService (Interface)
    - listAllOrders()
	- takeOrder()
    - deliverOrder()
	
	#### DeliveryEmployeeService (Singleton)
	- data

## Main
- calls for service class