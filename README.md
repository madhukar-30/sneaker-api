# Sneaker REST API 👟

A high-performance REST API for sneaker e-commerce applications, built with Node.js and MongoDB. Manages product data with full CRUD operations and supports features like size-based pricing and new arrival flags.

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
![GitHub repo size](https://img.shields.io/github/repo-size/madhukar-30/sneaker-api)

## 🌟 Features
- **Product Management**
  - `GET /api/sneakers`: Retrieve all sneakers
  - `POST /api/sneakers`: Add new sneaker
  - `PUT /api/sneakers/:id`: Update existing sneaker
- **Rich Product Schema**
  - Name, description & Cloudinary-hosted images
  - Size-specific pricing
  - Gender classification (Men/Women/Unisex)
  - Category (Running/Lifestyle/Basketball)
  - `isFresh` flag for new arrivals
- **Scalable Architecture**
  - MVC pattern implementation
  - MongoDB Atlas cloud database
  - Environment configuration support

## 🛠 Tech Stack
| Area        | Technologies              |
|-------------|---------------------------|
| **Backend** | Node.js, Express.js       |
| **Database**| MongoDB (Mongoose ODM)    |
| **Tools**   | Postman, Thunder Client   |

## 📁 Project Structure
```bash
sneaker-api/
├── models/          # Mongoose schemas
│   └── sneaker.js
├── routes/          # API endpoints
│   └── sneakers.js
├── data/            # Sample JSON data
├── seed.js          # Database seeder
├── server.js        # Entry point
├── .env.example     # Environment template
└── .gitignore
```
## 🚀 Quick Start

Get up and running in 5 minutes:

```bash
# 1. Clone repository
git clone https://github.com/madhukar-30/sneaker-api.git
cd sneaker-api

# 2. Install dependencies
npm install

# 3. Configure environment
cp .env.example .env
# Edit .env with your MongoDB URI
nano .env

# 4. Seed database (optional)
node seed.js

# 5. Start server
node server.js
```

## 📊 Sample Data Model
```json
{
  "name": "Nike Air Max 90",
  "image": "https://res.cloudinary.com/.../airmax90.jpg",
  "link": "/products/air-max-90",
  "sizes": [
    { "size": 8, "price": 120 },
    { "size": 9, "price": 125 }
  ],
  "gender": "Men",
  "category": "Lifestyle",
  "isFresh": true
}



```

## 🧪 Testing Endpoints

### 1. Create New Sneaker
```http
POST /api/sneakers
Content-Type: application/json

{
  "name": "New Balance 550",
  "image": "https://res.cloudinary.com/.../nb550.jpg",
  "sizes": [
    {"size": 7, "price": 110},
    {"size": 8, "price": 115}
  ],
  "gender": "Unisex",
  "category": "Lifestyle"
}
```
####  Sample Response:
```
{
  "status": "success",
  "data": {
    "_id": "662a1b2e4c3d5f0017a8b9c0",
    "name": "New Balance 550",
    "image": "https://res.cloudinary.com/.../nb550.jpg",
    "sizes": [
      {"size": 7, "price": 110, "_id": "663b1c5a8d2e4f0018d4e7a1"},
      {"size": 8, "price": 115, "_id": "663b1c5a8d2e4f0018d4e7a2"}
    ],
    "gender": "Unisex",
    "category": "Lifestyle",
    "isFresh": true
  }
}
```
### 2. Update Sneaker Sizes
```
PUT /api/sneakers/662a1b2e4c3d5f0017a8b9c0
Content-Type: application/json

{
  "sizes": [
    {"size": 9, "price": 125},
    {"size": 10, "price": 130}
  ]
}
```
#### Sample Response:
```
{
  "status": "success",
  "message": "Sneaker sizes updated",
  "data": {
    "sizes": [
      {"size": 9, "price": 125, "_id": "663b1d7f8d2e4f0018d4e7b3"},
      {"size": 10, "price": 130, "_id": "663b1d7f8d2e4f0018d4e7b4"}
    ]
  }
}
```
### 3. Get All Sneakers
```
GET /api/sneakers
```
#### Sample Response:
```
{
  "status": "success",
  "results": 15,
  "data": [
    {
      "_id": "662a1b2e4c3d5f0017a8b9c0",
      "name": "Nike Air Max 90",
      "image": "https://res.cloudinary.com/.../airmax90.jpg",
      "sizes": [
        {"size": 8, "price": 120, "_id": "663b1c5a8d2e4f0018d4e7a1"},
        {"size": 9, "price": 125, "_id": "663b1c5a8d2e4f0018d4e7a2"}
      ],
      "gender": "Men",
      "category": "Lifestyle",
      "isFresh": true
    },
    // ... other sneakers
  ]
}
```
### 4. Get Specific Sneaker
```
GET /api/sneakers/662a1b2e4c3d5f0017a8b9c0
```
#### Sample Response:
```
{
  "status": "success",
  "data": {
    "_id": "662a1b2e4c3d5f0017a8b9c0",
    "name": "Adidas Ultraboost",
    "image": "https://res.cloudinary.com/.../ultraboost.jpg",
    "sizes": [
      {"size": 8, "price": 180, "_id": "663b1e9a8d2e4f0018d4e7c5"},
      {"size": 9, "price": 185, "_id": "663b1e9a8d2e4f0018d4e7c6"}
    ],
    "gender": "Women",
    "category": "Running",
    "isFresh": false
  }
}
```

## 🤝 Contribution
We welcome contributions! Follow these steps:
```bash
1. Fork the repository
2. Create feature branch: git checkout -b feature/your-feature
3. Commit changes: git commit -m 'Add amazing feature'
4. Push to branch: git push origin feature/your-feature
5. Open a Pull Request
```


