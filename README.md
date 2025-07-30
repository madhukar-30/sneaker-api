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
