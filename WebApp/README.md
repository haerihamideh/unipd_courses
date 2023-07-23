# Semicolon

#### `Web Applications A.Y. 2022-2023`

#### ` Master Degree in Computer Engineering & Master Degree in ICT for Internet and Multimedia `

### Objectives

The objective of the pharmacy website is to provide a secure and user-friendly platform for pharmacy staff to manage
drugs and materials, prescriptions, and orders efficiently,
and maintain accurate records of inventory, suppliers, and sales. The system is designed to generate reports that
provide better insights and facilitate decision-making for pharmacy staff.
By achieving these objectives, the system aims to enhance the productivity and effectiveness of the pharmacy staff while
ensuring the secure
and efficient management of drugs and materials.

### Getting Started

To get started with this project, you will need to have:

* Java Version 19
* Spring-boot version 3.0.5
* Postgresql

> Before running the project you need to set your db information in the application
> properties([link](src/main/resources/application.properties)) like url, username, password

### Contributing

| Last Name         | First Name | Badge Number |
|-------------------|------------|--------------|
| Baghishani        | Reihaneh   | 2072534      |
| Erfanianomidvar   | Ali        | 2080482      |
| Celik             | Irem Goksu | 2086040      |
| Haeri             | Hamideh    | 2080632      |
| Hajizadeh Chavari | Ferdos     | 2071542      |
| Hosseinpour       | Hossein    | 2080601      |
| Sirvanci          | Gulce      | 2087153      |
| Tahvildari        | Ali        | 2071563      |
| Torabi            | Mohammad   | 2080501      |

### REST API Summary

We used Swagger UI to document our REST API. Swagger is an open-source tool that can help developers design, build, and
document RESTful APIs. With Swagger UI, APIs can be easily tested and explored using a user-friendly interface.

> To opne the swagger you need to run the project in your localhost and going to
> this ( [Link : localhost:8081/swagger-ui/index.html#/](localhost:8081/swagger-ui/index.html#/) )

Additionally, it can generate interactive documentation that can be shared with other developers. The following table
shows the URIs, HTTP methods, and descriptions for each endpoint in

| URI                                 | Method | Description                                                                                                                                              |
|-------------------------------------|--------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| user/{id}                           | GET    | This API endpoint retrieves the details of a specific user based on their unique ID.                                                                     |
| user/{id}                           | PUT    | This API endpoint allows for updating the information of a specific user based on their unique ID.                                                       |
| user                                | GET    | This API endpoint retrieves a list of all users stored in the system.                                                                                    |
| user                                | POST   | This API endpoint allows for the creation of a new user in the system.                                                                                   |
| user/change-status/{id}/{newStatus} | PATCH  | This API endpoint allows for changing the status of a specific user based on their unique ID.                                                            |
| user/delete/{id}                    | DELETE | This API endpoint allows for deleting a specific user based on their unique ID.                                                                          |
| supplier/{id}                       | GET    | This API endpoint retrieves thedetails of a specific supplier based on their unique ID.                                                                  |
| supplier/{id}                       | PUT    | This API endpoint allows for updating the information of a specific supplier based on their unique ID.                                                   |
| supplier/{id}                       | DELETE | This API endpoint allows for deleting a specific supplier based on their unique ID.                                                                      |
| supplier                            | POST   | This API endpoint allows for the creation of a new supplier in the system.                                                                               |
| storage/{id}                        | GET    | This API endpoint retrieves the details of a specific storage based on its unique ID.                                                                    |
| storage/{id}                        | PUT    | This API endpoint allows for updating the information of a specific storage based on its unique ID.                                                      |
| storage/{id}                        | DELETE | This API endpoint allows for deleting specific storage based on its unique ID.                                                                           |
| storage                             | GET    | This API endpoint retrieves the list of all storage available in the system.                                                                             |
| storage                             | POST   | This API endpoint allows for creating new storage in the system.                                                                                         |
| receipt/{id}                        | GET    | This API endpoint retrieves the details of a specific receipt identified by the given ID.                                                                |
| receipt/{id}                        | PUT    | This API endpoint updates the details of a specific receipt identified by the given ID.                                                                  |
| receipt                             | POST   | This API endpoint allows for creating a new receipt in the system.                                                                                       |
| pharmacy/{id}                       | GET    | This API endpoint retrieves the details of a specific pharmacy based on its unique ID.                                                                   |
| pharmacy/{id}                       | PUT    | This API endpoint allows for updating the information of a specific pharmacy based on its unique ID.                                                     |
| pharmacy/{id}                       | DELETE | This API endpoint allows for deleting a specific pharmacy based on its unique ID.                                                                        |
| pharmacy/{id}/add-staff             | PUT    | This API endpoint allows for adding a new staff member to a specific pharmacy based on its unique ID.                                                    |
| pharmacy                            | GET    | This API endpoint retrieves a list of all pharmacies stored in the system.                                                                               |
| pharmacy                            | POST   | This API endpoint allows for the creation of a new pharmacy in the system.                                                                               |
| pharmacy/{pharmacyId}               | PATCH  | This API endpoint allows for updating the information of a specific pharmacy activation status based on its unique ID. It also changes the staff status. |
| pharmacy/delete-staff               | DELETE | This API endpoint allows for deleting a specific staff member from a pharmacy based on their unique ID.                                                  |
| material/{id}                       | GET    | This API endpoint retrieves the details of a specific material based on its unique ID.                                                                   |
| material/{id}                       | PUT    | This API endpoint allows for updating the information of a specific material based on its unique ID.                                                     |
| material                            | GET    | ThisAPI endpoint retrieves a list of all materials stored in the system.                                                                                 |
| material                            | POST   | This API endpoint allows for the creation of new material in the system.                                                                                 |
| drug/{id}                           | GET    | This API endpoint retrieves the details of a specific drug based on its unique ID.                                                                       |
| drug/{id}                           | PUT    | This API endpoint allows for updating the information of a specific drug based on its unique ID.                                                         |
| drug                                | GET    | This API endpoint retrieves a list of all drugs stored in the system.                                                                                    |
| drug                                | POST   | This API endpoint allows for the creation of a new drug in the system.                                                                                   |
| order/save                          | POST   | This API endpoint allows for saving a new order in the system.                                                                                           |
| order/status/{id}/{status}          | PATCH  | This API endpoint allows for updating the status of a specific order based on its unique ID.                                                             |
| order/getById/{id}                  | GET    | This API endpoint retrieves the details of a specific order based on its unique ID.                                                                      |
| order/get-all                       | GET    | This API endpoint retrieves a list of all orders stored in the system.                                                                                   |
| order/delete/{id}                   | DELETE | This API endpoint allows for deleting a specific order based on its unique ID.                                                                           |
| account/{token}                     | POST   | This API endpoint allows for creating a new accountin the system or logging in to an existing account.                                                   |
| account                             | GET    | This API endpoint retrieves the details of the authenticated user's account.                                                                             |

## REST Error Codes

The following table describes the error codes that may be returned by our REST API:

| Error Code | HTTP Status Code      | Description                                |
|------------|-----------------------|--------------------------------------------|
| 400        | BAD_REQUEST           | Account Access Not Found                   |
| 404        | NOT_FOUND             | User Name Or Password Not Exists Exception |
| 500        | INTERNAL_SERVER_ERROR | Stack Overflow Error                       |
| 400        | BAD_REQUEST           | Invalid Parameter Exception                |
| 403        | FORBIDDEN             | Invalid Token Exception                    |
| 404        | NOT_FOUND             | Not Found Exception                        |
| 403        | FORBIDDEN             | Permission Denied                          |
| 405        | METHOD_NOT_ALLOWED    | Pharmacy Exists Before                     |
| 404        | NOT_FOUND             | User Do Not Exist                          |
| 400        | BAD_REQUEST           | Create Pharmacy Data Not Found             |
| 404        | NOT_FOUND             | Entity Not Found Exception                 |
| 400        | BAD_REQUEST           | Illegal Argument Exception                 |
| 424        | FAILED_DEPENDENCY     | Illegal State Exception                    |
| 404        | NOT_FOUND             | Entity Not Found Exception                 |

### REST API Details

#### CREATE DRUG

The following endpoint allows the creation of a new drug in the pharmacy system.

* URL: `/drug`
* Method: `POSTP`
* Data Parameters:

```json
{
  "name": "proteine",
  "supplier": 2,
  "expirationDate": "2024−06−30",
  "shape": "tablet",
  "gender": "FEMALE",
  "ageGroup": "ADULTS",
  "isSensitive": true,
  "needPrescription": false,
  "description": "protein is a needed.",
  "limitation": 20,
  "price": 7.99,
  "countryOFProduction": "AF"
}
```

* Success Response:

```json
{
  "msg": "OK",
  "data": {}
}
```

* Error Response:

```json

{
  "msg": "drug_exists_before",
  "data": "com.unipd.semicolon.business.exception.DrugExistsException"
}
```

#### EDIT DRUG

The following endpoint allows the modification of an existing drug in the pharmacy system.

* URL: `/drug/{id}`
* Method: `PUT`
* Data Parameters:

```json
{
  "name": "Ibuprofen",
  "expirationDate": "2024-06-30",
  "supplier": 1,
  "shape": "tablet",
  "gender": "FEMALE",
  "ageGroup": "ADULTS",
  "needPrescription": false,
  "description": "Ibuprofen is a nonsteroidal anti-inflammatory drug (NSAID) used to relieve pain and reduce fever.",
  "limitation": 20,
  "price": 7.99,
  "countryOFProduction": "PR",
  "lastModifiedDate": null,
  "sensitive": false
}
```

* Success Response:

```json
{
  "msg": "OK",
  "data": {
    "id": 5,
    "name": "Ibuprofen",
    "expirationDate": "2024-06-30",
    "image": null,
    "shape": "tablet",
    "gender": "FEMALE",
    "ageGroup": "ADULTS",
    "needPrescription": false,
    "description": "Ibuprofen is a nonsteroidal anti-inflammatory drug (NSAID) used to relieve pain and reduce fever.",
    "limitation": 20,
    "price": 7.99,
    "countryOFProduction": "PR",
    "lastModifiedDate": null,
    "sensitive": true
  }
}
```

#### GET DRUG

The following endpoint retrieves the details of a specific drug based on its unique ID.

* URL: `/drug/{id}`
* Method: `GET`
* Success Response:

```json
{
  "msg": "OK",
  "data": {
    "id": null,
    "name": "proteine",
    "supplier": {
      "id": 2,
      "name": "sup1",
      "address": "here in padova",
      "email": "su1@pd.it",
      "telephoneNumber": "099999",
      "drugs": [
        {
          "id": 4,
          "name": "proteine",
          "expirationDate": "2024-06-30",
          "image": null,
          "shape": "tablet",
          "gender": "FEMALE",
          "ageGroup": "ADULTS",
          "needPrescription": false,
          "description": "protein is a needed.",
          "limitation": 20,
          "price": 7.99,
          "countryOFProduction": "AF",
          "lastModifiedDate": null,
          "sensitive": true
        }
      ],
      "materials": [
      ]
    },
    "expirationDate": "2024-06-30",
    "image": null,
    "shape": "tablet",
    "gender": "FEMALE",
    "ageGroup": "ADULTS",
    "needPrescription": false,
    "description": "protein is a needed.",
    "limitation": 20,
    "price": 7.99,
    "countryOFProduction": "AF",
    "lastModifiedDate": null,
    "orders": null,
    "receipts": null,
    "sensitive": true
  }
}
```

#### GET ALL DRUG

The following endpoint allows the creation of a new drug in the pharmacy system.

* URL: `/drug`
* Method: `GET`

* Success Response:

```json
{
  "msg": "OK",
  "data": [
    {
      "id": null,
      "name": "Ibuprofen",
      "supplier": {
        "id": 1,
        "name": "sup0",
        "address": "pd",
        "email": "su0@pd.it",
        "telephoneNumber": "00000",
        "drugs": [
          {
            "id": 3,
            "name": "Ibuprofen",
            "expirationDate": "2024-06-30",
            "image": null,
            "shape": "tablet",
            "gender": "MALE",
            "ageGroup": "ADULTS",
            "needPrescription": false,
            "description": "Ibuprofen is a nonsteroidal anti-inflammatory drug (NSAID) used to relieve pain and reduce fever.",
            "limitation": 20,
            "price": 5.99,
            "countryOFProduction": "PR",
            "lastModifiedDate": null,
            "sensitive": false
          }
        ],
        "materials": []
      },
      "expirationDate": "2024-06-30",
      "image": null,
      "shape": "tablet",
      "gender": "MALE",
      "ageGroup": "ADULTS",
      "needPrescription": false,
      "description": "Ibuprofen is a nonsteroidal anti-inflammatory drug (NSAID) used to relieve pain and reduce fever.",
      "limitation": 20,
      "price": 5.99,
      "countryOFProduction": "PR",
      "lastModifiedDate": null,
      "orders": null,
      "receipts": null,
      "sensitive": false
    }
  ]
}
```

#### CREATE MATERIAL

The following endpoint allows the creation of a new material in the pharmacy system.

* URL: `/material`
* Method: `POST`
* Data Parameters:

```json
{
  "name": "Example Product",
  "supplier": 2,
  "countryOfProduction": "PR",
  "expirationDate": "2022-12-31",
  "gender": "FEMALE",
  "price": 50.0,
  "ageGroup": "ADULTS",
  "description": "This is an example product description. It can be as long or as short as you like."
}
```

* Success Response:

```json
{
  "msg": "OK",
  "data": {
    "id": 6,
    "name": "Example Product",
    "countryOfProduction": "PR",
    "expirationDate": "2022-12-31",
    "image": null,
    "gender": "FEMALE",
    "price": 50.0,
    "ageGroup": "ADULTS",
    "lastModifiedDate": null,
    "description": "This is an example product description. It can be as long or as short as you like."
  }
}
```

#### EDIT MATERIAL

The following endpoint allows the modification of an existing material in the pharmacy system.

* URL: `/material/{id}`
* Method: `PUT`
* Data Parameters:

```json
{
  "name": "Example Product",
  "supplier": 2,
  "countryOfProduction": "PR",
  "expirationDate": "2022-12-31",
  "gender": "FEMALE",
  "price": 50.0,
  "ageGroup": "ADULTS",
  "description": "This is an example product description. It can be as long or as short as you like."
}

```

* Success Response:

```json
{
  "msg": "OK",
  "data": true
}
```

* Error Response:

```json
{
  "msg": "Material not found - 2334",
  "data": "com.unipd.semicolon.business.exception .IllegalStateException"
}
```

#### GET MATERIAL

The following endpoint retrieves the details of a specific material based on its unique ID.

* URL: `/material/{id}`
* Method: `GET`
* Success Response:

```json
{
  "msg": "OK",
  "data": {
    "id": 6,
    "name": "Example Product",
    "countryOfProduction": "PR",
    "expirationDate": "2022-12-31",
    "image": null,
    "gender": "FEMALE",
    "price": 50.0,
    "ageGroup": "ADULTS",
    "lastModifiedDate": null,
    "description": "This is an example product description. It can be as long or as short as you like."
  }
}
```

#### GET ALL MATERIAL

The following endpoint retrieves a list of all materials stored in the pharmacy system.

* URL: `/material`
* Method: `GET`

* Success Response:

```json
{
  "msg": "OK",
  "data": [
    {
      "id": null,
      "name": "Example Product",
      "supplier": {
        "id": 2,
        "name": "sup1",
        "address": "here in padova",
        "email": "su1@pd.it",
        "telephoneNumber": "099999",
        "drugs": [],
        "materials": [
          {
            "id": 6,
            "name": "Example Product",
            "countryOfProduction": "PR",
            "expirationDate": "2022-12-31",
            "image": null,
            "gender": "FEMALE",
            "price": 50.0,
            "ageGroup": "ADULTS",
            "lastModifiedDate": null,
            "description": "This is an example product description. It can be as long or as short as you like."
          }
        ]
      },
      "expirationDate": "2022-12-31",
      "image": null,
      "gender": "MALE",
      "ageGroup": "ADULTS",
      "price": 50.0,
      "lastModifiedDate": null,
      "description": "This is an example product description. It can be as long or as short as you like.",
      "countryOfProduction": "PR",
      "orders": null,
      "receipts": null
    }
  ]
}
```

#### CREATE RECEIPT

The following endpoint allows the creation of a new receipt in the pharmacy system.

* URL: `/receipt`
* Method: `POST`
* Data Parameters:

```json
{
  "id": 0,
  "list_drug_id": [
    1,
    2
  ],
  "list_material_id": null,
  "image": [
    1,
    0,
    0,
    0,
    1,
    1
  ],
  "date": "2023-04-27T07:08:30.267Z",
  "paymentMethod": "CASH"
}
```

* Success Response:

```json
{
  "msg": "OK",
  "data": {
    "id": 1,
    "receiptDrugs": [
      {
        "id": 1,
        "name": "new drug",
        "expirationDate": "2023-04-23T10:44:38.843+00:00",
        "image": null,
        "shape": "shape",
        "gender": "MALE",
        "ageGroup": "ADULTS",
        "needPrescription": true,
        "description": "string",
        "limitation": 10,
        "price": 60,
        "countryOFProduction": "AF",
        "lastModifiedDate": null,
        "sensitive": true
      },
      {
        "id": 2,
        "name": "new drug",
        "expirationDate": "2023-04-23T10:44:38.843+00:00",
        "image": null,
        "shape": "shape",
        "gender": "MALE",
        "ageGroup": "ADULTS",
        "needPrescription": true,
        "description": "string",
        "limitation": 10,
        "price": 60,
        "countryOFProduction": "AF",
        "lastModifiedDate": null,
        "sensitive": true
      }
    ],
    "receiptMaterials": [],
    "image": "AQAAAAEB",
    "date": "2023-04-27T07:08:30.267+00:00",
    "paymentMethod": "CASH"
  }
}
```

#### EDIT RECEIPT

The following endpoint allows the modification of an existing receipt in the pharmacy system.

* URL: `/receipt/{id}`
* Method: `PUT`
* Data Parameters:

```json
{
  "id": 1,
  "list_drug_id": [
    1,
    2,
    3
  ],
  "list material id": null,
  "material id": 0,
  "image": null,
  "date": "2023-04-27T07:42:56.226Z",
  "paymentMethod": "PAYPAL"
}

```

* Success Response:

```json
{
  "msg": "OK",
  "data": true
}
```

#### GET RECEIPT

The following endpoint retrieves the details of a specific receipt based on its unique ID.

* URL: `/receipt/{id}`
* Method: `GET`
* Success Response:

```json
{
  "msg": "OK",
  "data": {
    "id": 1,
    "receiptDrugs": [
      {
        "id": 1,
        "name": "new drug",
        "expirationDate": "2023-04-23T10:44:38.843+00:00",
        "image": null,
        "shape": "shape",
        "gender": "MALE",
        "ageGroup": "ADULTS",
        "needPrescription": true,
        "description": "string",
        "limitation": 10,
        "price": 60,
        "countryOfProduction": "AF",
        "lastModifiedDate": null,
        "sensitive": true
      },
      {
        "id": 2,
        "name": "new drug",
        "expirationDate": "2023-04-23T10:44:38.843+00:00",
        "image": null,
        "shape": "shape",
        "gender": "MALE",
        "ageGroup": "ADULTS",
        "needPrescription": true,
        "description": "string",
        "limitation": 10,
        "price": 60,
        "countryOFProduction": "AF",
        "lastModifiedDate": null,
        "sensitive": true
      },
      {
        "id": 3,
        "name": "new drug",
        "expirationDate": "2023-04-23T10:44:38.843+00:00",
        "image": null,
        "shape": "shape",
        "gender": "MALE",
        "ageGroup": "ADULTS",
        "needPrescription": true,
        "description": "string",
        "limitation": 10,
        "price": 60,
        "countryOFProduction": "AF",
        "lastModifiedDate": null,
        "sensitive": true
      }
    ],
    "receiptMaterials": [
    ],
    "image": "AQAAAAEB",
    "date": "2023-04-27T07:42:56.226+00:00",
    "paymentMethod": "PAYPAL"
  }
}
```

#### PHARMACY ACTIVATION

The following endpoint create a pharmacy in the system.

* URL: `/pharmacy/{id}`
* Method: `PATCH`

* Data Parameters:

```json
{
  "status": "ACTIVE"
}
```

* Success Response:

```json
{
  "msg": "OK",
  "data": {
    "id": 1,
    "name": "pharmacy1",
    "address": "padova",
    "telephoneNumber": "09999999",
    "time_table": [],
    "logo": null,
    "storage": [],
    "staff": [
      {
        "id": 1,
        "name": "jeremy",
        "lastName": "jervis",
        "gender": "MALE",
        "birthDate": null,
        "phoneNumber": "08888888",
        "address": "unipd",
        "role": null,
        "email": "hi@uni.com",
        "accountStatus": "ACTIVE",
        "profilePicture": null
      }
    ],
    "status": "ACTIVE",
    "orders": []
  }
}
```

#### PHARMACY CREATION

The following endpoint create a pharmacy in the system.

* URL: `/pharmacy`
* Method: `POST`

* Data Parameters:

```json
{
  "name": "Example Pharmacy1",
  "address": "123 Main St",
  "tellNumber": "3389929820",
  "timeTable": [],
  "logoPath": [],
  "storage": [],
  "staff": [],
  "status": "ACTIVE"
}
```

* Success Response:

```json
{
  "msg": "OK",
  "data": {
    "id": 2,
    "name": "Example Pharmacy1",
    "address": "123 Main St",
    "telephoneNumber": "3389929820",
    "time_table": null,
    "logo": "",
    "storage": null,
    "staff": null,
    "status": "ACTIVE",
    "orders": null
  }
}
```

* Error Response:

```json
{
  "msg": "Telephone number should contain only digits",
  "data": "com.unipd.semicolon.business.exception.IllegalArgumentException"
}
```

#### PHARMACY EDIT

The following endpoint modification a pharmacy in the system.

* URL: `/pharmacy/{id}`
* Method: `PUT`

* Data Parameters:

```json
{
  "name": "Hpp's Pharmacy2222",
  "tell_number": "3389338922"
}
```

* Success Response:

```json
{
  "msg": "OK",
  "data": true
}
```

* Error Response:

```json
{
  "msg": "Telephone number should contain only digits",
  "data": "com.unipd.semicolon.business.exception.IllegalArgumentException"
}
```

#### PHARMACY CREATION

The following endpoint create a pharmacy in the system.

* URL: `/pharmacy`
* Method: `POST`

* Data Parameters:

```json
{
  "name": "Example Pharmacy1",
  "address": "123 Main St",
  "tellNumber": "3389929820",
  "timeTable": [],
  "logoPath": [],
  "storage": [],
  "staff": [],
  "status": "ACTIVE"
}
```

* Success Response:

```json
{
  "msg": "OK",
  "data": {
    "id": 2,
    "name": "Example Pharmacy1",
    "address": "123 Main St",
    "telephoneNumber": "3389929820",
    "time_table": null,
    "logo": "",
    "storage": null,
    "staff": null,
    "status": "ACTIVE",
    "orders": null
  }
}
```

* Error Response:

```json
{
  "msg": "Telephone number should contain only digits",
  "data": "com.unipd.semicolon.business.exception.IllegalArgumentException"
}
```

#### PHARMACY DELETE

The following endpoint delete a pharmacy in the system.

* URL: `/pharmacy/{id}`
* Method: `DELETE`

* Success Response:

```json
{
  "msg": "OK",
  "data": true
}
```

#### PHARMACY GET

The following endpoint delete a pharmacy in the system.

* URL: `/pharmacy/{id}`
* Method: `GET`

* Success Response:

```json
{
  "msg": "OK",
  "data": {
    "id": 1,
    "name": "Hpp's Pharmacy2222",
    "address": "123 Main St",
    "telephoneNumber": "3389338922",
    "time_table": [],
    "logo": "",
    "storage": [],
    "staff": [
      {
        "id": 4,
        "name": "John",
        "lastName": "Doe",
        "gender": "MALE",
        "birthDate": "1995-06-30T00:00:00",
        "phoneNumber": "3358962012",
        "address": "123 Main St.",
        "role": {
          "id": 2,
          "role": "user"
        },
        "email": "john.doe@example.com",
        "accountStatus": "ACTIVE",
        "profilePicture": null
      }
    ],
    "status": "ACTIVE",
    "orders": []
  }
}
```

#### ACCOUNT LOGIN

The following endpoint allows users to log in to their account in the pharmacy system.

* URL: `/account`
* Method: `GET`

* Data parameters:

```json
{
  "username": "STRING",
  "password": "STRING"
}
```

* Success Response:

```json
{
  "msg": "OK",
  "data": {
    "username": "STRING",
    "lastLoginDate": "2023-04-27T00:48:27",
    "user": {
      "id": "LONG",
      "name": "STRING",
      "lastName": "STRING",
      "gender": "MALE",
      "birthDate": "LOCALDATE",
      "phoneNumber": "STRING",
      "address": "STRING",
      "role": {
        "id": "LONG",
        "role": "STRING"
      },
      "email": "STRING",
      "accountStatus": "STRING",
      "profilePicture": "byte[]"
    },
    "token": "STRING"
  }
}
```

#### ACCOUNT LOGOUT

The following endpoint allows users to log out of their account in the pharmacy system.

* URL: `/account/{token}`
* Method: `POST`
*
* Success Response:

```json
{
  "msg": "OK",
  "data": true
}
```

### Exception Handler

#### 500 Internal Server Error :

* Wrong method response Exception :

```Json
{
  "status": "500 Internal Server Error",
  "msg": "Request method 'POST' is not supported",
  "data": "org.springframework.web.HttpRequestMethodNotSupportedException"
}
```

* Other Exception :

```Json
{
  "status": "500 Internal Server Error",
  "msg": "Error message",
  "data": "class name"
}
```

#### 404 Not Found:

* Username or password not exists Exception :

```Json
{
  "status": "404 Not Foundr",
  "msg": "user_name_or_password_not_exists",
  "data": "Username: STRING Password : STRING"
}
```

* Entity not found Exception :

```Json
{
  "status": "404 Not Foundr",
  "msg": "MESSAGE BASE THE FUNCTION CAN BE DIFFERENT",
  "data": "com.unipd.semicolon.business.exception.EntityNotFoundException"
}
```

#### 400 Bad Request :

```Json
{
  "status": "400 bad request",
  "msg": "MESSAGE BASE THE FUNCTION CAN BE DIFFERENT",
  "data": "com.unipd.semicolon.business.exception.IllegalArgumentException"
}
```

#### 424 Failed Dependency :

```Json
{
  "status": "424 Failed Dependency",
  "msg": "MESSAGE BASE THE FUNCTION CAN BE DIFFERENT",
  "data": "com.unipd.semicolon.business.exception.IllegalStateException"
}
```

#### 403 Forbidden:

* Invalid Token Exception :

```Json
{
  "status": "403 Forbidden",
  "msg": "Invalid_Token_Exception",
  "data": "TOKEN VALUE"
}
```

* Permission denied:

```Json
{
  "msg": "permission denied",
  "data": "Token :eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiUm9sZSI6InVzZXIiLCJpYXQiOjE2ODI2MzczMDcsImV4cCI6MTcxODYzNzMwN30.OCsiF_pXCHjhZMTfkyTn7sNDnzVP5qUeDV8M3UavmVo"
}
```

#### 424 Failed Dependency :

```Json
{
  "status": "424 Failed Dependency",
  "msg": "MESSAGE BASE THE FUNCTION CAN BE DIFFERENT",
  "data": "com.unipd.semicolon.business.exception.IllegalStateException"
}
```

### Group Members Contribution

Throughout the project lifecycle, the team conducted multiple meetings to ensure that the project was executed
successfully. The initial stages involved selecting a pertinent subject and devising an appropriate implementation
strategy. The team engaged in extensive deliberations to determine the project requirements and defined all relevant
classes and entities. Subsequently, tasks were assigned to individual team members using the Trello platform. Throughout
the project duration, each team member fulfilled specific responsibilities that were critical to the success of the
endeavor. Through collaborative efforts, the team developed a dependable and robust system that met the expectations of
both users and stakeholders. Their commitment to working together allowed for effective completion of various tasks,
leading to the successful execution of the project. In the following paragraphs, a detailed account of each team
member’s responsibilities is provided.

#### `Reihaneh Baghishani`
##### `HW1`
She created Sequence Diagrams and built entities like Drug for the project. She implemented
servlets, JSPs, and RESTful APIs using Spring Boot framework, designed DAO and service layers, and created filters to
enhance the functionality of Drugs and Materials modules. Additionally, she contributed to exception handling,
documented APIs, modified the designs to conform to RESTful API standards, and helped implement certain methods for the
Drug and Material entities.

##### `HW2`

#### `Ali Erfanian Omidvar`
##### `HW1`
He had a diverse set of responsibilities, such as defining the system structure and creating a
response helper for APIs. Additionally, he was responsible for fixing any issues related to the relationship between
entities and creating a security service for the system. He also contributed to refactoring the order entity, service,
and repository and created a log service to ensure system accuracy. Finally, he reviewed the merge request on git and
refactored the URLs.
##### `HW2`

#### `Irem Goksu Celik`
##### `HW1`
In her role, she was responsible for implementing the supplier and login entity and repository.
Additionally, she developed the order CRUD API and prepared the class diagram. She also contributed to the
implementation of storage reports for the system.
##### `HW2`

#### `Hamideh Haeri`
##### `HW1`
Her role encompassed various responsibilities, including creating the Entity Relationship diagram,
defining the attributes and methods of the Pharmacy entity, and developing the Receipt API. She was also responsible for
creating the project document and fixing API issues, as well as adding validation checks to ensure system accuracy.
Additionally, she contributed to the implementation of some methods in the User module.
##### `HW2`
she made user list, table component, some part of modal component, profile page, receipt page and list and some part 
of error handling in the front side

#### `Ferdos Hajizadeh Chavari`
##### `HW1`
Her role encompassed various tasks, such as implementing and designing the material entity and
repository, developing the drug API, and implementing the payment method. Additionally, she was responsible for editing
the project document to ensure system accuracy.
##### `HW2`
Her duty in the front part was to prepare order pages and receipt reports.
#### `Hossein Hosseinpour`
##### `HW1`
As part of his role, he was responsible for defining the attributes and methods of the Storage
entity and developing the Pharmacy API. He also created the sequence diagram for the Pharmacy save API and was
responsible for fixing any related API issues and adding validation checks to the system. Additionally, he contributed
to the implementation of some methods in the User module.

#### `Gulce Sirvanci`
##### `HW1`
She had multiple responsibilities, including implementing the user entity and repository, supplier
repository, and supplier CRUD API. Furthermore, she was responsible for the implementation of storage reports for the
amount of each drug and material, as well as the threshold parameters.
##### `HW2`

#### `Ali Tahvildari`
##### `HW1`
He had a diverse set of responsibilities, which included designing and implementing the order entity and
fixing any related issues. He also collaborated with others to implement Swagger and design and imple- ment the
wireframe for the system layout and contributed to the implementation of the material and user API methods, as well as
some methods in the User module.
##### `HW2`

### `Mohammad Torabi`
##### `HW1`
As part of his responsibilities, he created the Receipt entity and implemented the Storage module of the back-end system
using Spring Boot. To achieve this, he had to design and implement the API, repository, and service layers for the Storage entity. 
In addition, he contributed to the implementation of some methods in the User and Receipt modules and edited the project documentation.
##### `HW2`
He created the HTML, CSS, and JavaScript code for the login page and designed the HTML and CSS for the order report page. 
He also helped with implementing JavaScript for the order report.