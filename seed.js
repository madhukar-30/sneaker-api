
require("dotenv").config(); // MUST be at the very top

const mongoose = require("mongoose");
const dotenv = require("dotenv");
const Sneaker = require("./models/Sneaker");
const sneakerData = require("./data/sneakerData.json");

dotenv.config();

async function seedDB() {
  try {
    console.log("Mongo URI:", process.env.MONGO_URI);
await mongoose.connect(process.env.MONGO_URI);

    await mongoose.connect(process.env.MONGO_URI);
    await Sneaker.deleteMany(); // optional: clears existing
    await Sneaker.insertMany(sneakerData);
    console.log("Data seeded successfully");
    process.exit();
  } catch (error) {
    console.error("Error seeding data:", error);
    process.exit(1);
  }
}

seedDB();
