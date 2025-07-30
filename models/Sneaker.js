const mongoose = require('mongoose');

const sizeSchema = new mongoose.Schema({
  size: Number,
  price: Number
});

const sneakerSchema = new mongoose.Schema({
  name: String,
  pageUrl: String,
  sizes: [sizeSchema],
  gender: String,
  fresh: Boolean,
  category: String,
  image: String
});

module.exports = mongoose.model('Sneaker', sneakerSchema);
