#!/usr/bin/env bash


##########
#Remove all data
curl -X GET "http://localhost:8070/user/deleteall" -H  "accept: */*"
curl -X GET "http://localhost:8070/product/deleteall" -H  "accept: */*"
curl -X DELETE "http://localhost:8070/cart" -H  "accept: */*"

##Users
#Add User
userid1=$(curl -X POST "http://localhost:8070/user" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"name\":\"Arun\",\"address\":\"KA\"}" | jq '.userId')
userid2=$(curl -X POST "http://localhost:8070/user" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"name\":\"Kumar\",\"address\":\"KA\"}" | jq '.userId')
userid3=$(curl -X POST "http://localhost:8070/user" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"name\":\"Ajay\",\"address\":\"TN\"}" | jq '.userId')

#Get All Users
curl -X GET "http://localhost:8070/user/all" -H  "accept: */*"

#Update user
curl -X PUT "http://localhost:8070/user" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"userId\":1,\"name\":\"Arun\",\"address\":\"Karnataka\",\"memberSince\":\"2021-09-29T06:40:16.137Z\"}"

#Get User by ID
curl -X GET "http://localhost:8070/user/2" -H  "accept: */*"

#Delete by ID
curl -X DELETE "http://localhost:8070/user/$userid3" -H  "accept: */*"


##Products
#Add Product
productid1=$(curl -X POST "http://localhost:8070/product" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"productName\":\"Parle G\",\"manufacturer\":\"Parle\",\"price\":5}"  | jq '.productId')
productid2=$(curl -X POST "http://localhost:8070/product" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"productName\":\"Xperia\",\"manufacturer\":\"Sony\",\"price\":15000}"  | jq '.productId')
productid3=$(curl -X POST "http://localhost:8070/product" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"productName\":\"HP Pavillion\",\"manufacturer\":\"HP\",\"price\":50000}"  | jq '.productId')
productid4=$(curl -X POST "http://localhost:8070/product" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"productName\":\"MAC book pro\",\"manufacturer\":\"Apple\",\"price\":115000}"  | jq '.productId')
productid5=$(curl -X POST "http://localhost:8070/product" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"productName\":\"Bravia 53 inch\",\"manufacturer\":\"Sony\",\"price\":150000}"  | jq '.productId')
productid6=$(curl -X POST "http://localhost:8070/product" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"productName\":\"Maggie\",\"manufacturer\":\"Nestle\",\"price\":12}"  | jq '.productId')
productid7=$(curl -X POST "http://localhost:8070/product" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"productName\":\"Some item\",\"manufacturer\":\"Some manufaturer\",\"price\":500}"  | jq '.productId')

#Get All products
curl -X GET "http://localhost:8070/product/all" -H  "accept: */*"

#Update Product
curl -X PUT "http://localhost:8070/product" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"productId\":6,\"productName\":\"Parle G\",\"manufacturer\":\"Parle\",\"price\":6}"

#Get by product id
curl -X GET "http://localhost:8070/product/7" -H  "accept: */*"

#Delete by id
curl -X DELETE "http://localhost:8070/product/$productid7" -H  "accept: */*"

##Cart
#Add item to cart
curl -X POST "http://localhost:8070/cart" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"userId\":$userid1,\"productId\":$productid1,\"quantity\":10,\"discount\":13}"
curl -X POST "http://localhost:8070/cart" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"userId\":$userid1,\"productId\":$productid2,\"quantity\":100,\"discount\":10}"
curl -X POST "http://localhost:8070/cart" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"userId\":$userid1,\"productId\":$productid3,\"quantity\":2,\"discount\":23}"
curl -X POST "http://localhost:8070/cart" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"userId\":$userid1,\"productId\":$productid4,\"quantity\":1,\"discount\":40}"

#Get Cart items
curl -X GET "http://localhost:8070/cart" -H  "accept: */*"

#Delete Cart Item
curl -X DELETE "http://localhost:8070/cart/$productid4" -H  "accept: */*"

#Buy Cart Items
orderId=$(curl -X POST "http://localhost:8070/cart/buy" -H  "accept: */*" -d ""  | jq '.')

##Order
#Get Order Details
curl -X GET "http://localhost:8070/order/{orderid}?orderid=$orderId" -H  "accept: */*"

#Delete order
curl -X DELETE "http://localhost:8070/order" -H  "accept: */*"


#Get again
curl -X GET "http://localhost:8070/user/all" -H  "accept: */*"
curl -X GET "http://localhost:8070/product/all" -H  "accept: */*"
curl -X GET "http://localhost:8070/cart" -H  "accept: */*"
