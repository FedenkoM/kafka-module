# kafka-module
## Run:
- go to root folder and run `docker compose up -d`
- start services:
  1) config-server
  2) discovery-service
  3) client-app, palmetto-app, courier-app

- heat POST `/api/order` endpoint 

###### body example `
                {
                    "customer": {
                        "name": "John Doe",
                        "phone": "568-35-22"
                    },
                    "status": "PENDING",
                    "comment": "Please deliver ASAP",
                    "orderItems": [
                        {
                            "itemName": "Margherita Pizza",
                            "quantity": 2,
                            "price": 12.99
                        },
                        {
                            "itemName": "Pepperoni Pizza",
                            "quantity": 1,
                            "price": 14.99
                        }
                    ]
                }
`
