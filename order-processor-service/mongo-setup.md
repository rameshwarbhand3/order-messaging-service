## How to connect your application to mongodb container using terminal

1. First lets install mongodb-compass as a client to check locallly .
```
wget https://downloads.mongodb.com/compass/mongodb-compass_1.40.4_amd64.deb
```
```
sudo dpkg -i mongodb-compass_1.40.4_amd64.deb
```
```
mongodb-compass
```
This will install mongodb-compass on your machine.

2. Now to run MongoDB server locally we used docker mongodb image.
```
docker run -d -p 27017:27017 --name mongodb -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=root mongo:latest
```
3. Now check your mongodb container connected with mongodb-compass client by entering username and password.
4. Add following properties in application.yaml
```
spring:
  data:
    mongodb:
      uri: mongodb://root:root@localhost:27017
      database: orderDb
```
5. Run our application to connect mongodb and send post request from postman using http://localhost:8081/api/orders/retry it will store order in orderDb database.