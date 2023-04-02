# Product Delivery

### Requirements first phase
1. 19 actions :ballot_box_with_check:
2. 26 classes :ballot_box_with_check:
3. Private/Protected attributes :ballot_box_with_check:
4. Collections used: List -> ArrayList, Set -> HashSet :ballot_box_with_check:
5. Inheritance :ballot_box_with_check:
6. 2 service classes :ballot_box_with_check:
7. Main class which calls services :ballot_box_with_check:

## Product (abstract)
- name
- price
- quantity
- weight
- measurements

    ### Drink
    - isAlcoholic
    - hasSugar
    - calories
  
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
  
    ### Medicine
    - ingredients
    - allergens
    - contraindications

## Shop (abstract)
- name
- owner
- deliveryEmployees (List -> ArrayList)
- address
- rating

    ### Restaurant
    - foodMenu (List -> ArrayList)
    - drinksMenu (List -> ArrayList)
  
    ### Bar
    - drinksMenu (List -> ArrayList)
  
    ### Library
    - books (List -> ArrayList)
  
    ### Florist
    - flowers (List -> ArrayList)
    - bouquets (List -> ArrayList)
  
    ### Pharmacy
    - medicines (List -> ArrayList)

## User
- name
- email
- password
- phoneNumber

    ### Customer
    - preferredAddress

    ### Owner
    - shop    

    ### DeliveryEmployee
    - carNumber
    - averageOrders

    ### Administrator
    - license

## Order
- customer
- shop
- deliveryEmployee
- totalPrice
- products (List -> ArrayList)
- address
- ETA

## IRegistration (Interface)
- logIn()
- logOut()
- signInAdmin()
- signInCustomer()
- signInDeliveryEmployee()

    ### Registration (Singleton)
    - users (Set -> HashSet)
    - currentUser

## IService (Interface)
- shops (List -> ArrayList)
- orders (List -> ArrayList)
- currentUser
- shopId
- orderId

    ### AdminService (Singleton)
    - listAllShops()
    - listAllProducts()
    - listAllOrders()
    - addShop()
    - deleteShop()
    - addProduct()
    - deleteProduct()

    ### CustomerService (Singleton)
    - listAllShops()
    - listAllProducts()
    - addOrder()
    - cancelOrder()
    - getPopularShops()
  
    ### DeliveryEmployeeService (Singleton)
    - listAllOrders()
    - takeOrder()

## Main
- calls for service class