const express = require('express');
const router = express.Router();
const Sneaker = require('../models/Sneaker');

// GET all sneakers
router.get('/', async (req, res) => {
  const sneakers = await Sneaker.find();
  res.json(sneakers);
});

// POST a new sneaker
router.post('/', async (req, res) => {
  const sneaker = new Sneaker(req.body);
  await sneaker.save();
  res.status(201).json(sneaker);
});

// PUT update sneaker by ID
router.put('/:id', async (req, res) => {
  const updated = await Sneaker.findByIdAndUpdate(req.params.id, req.body, { new: true });
  res.json(updated);
});

module.exports = router;
