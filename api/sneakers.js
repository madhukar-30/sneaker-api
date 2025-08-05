import connectDB from '../utils/db.js';
import Sneaker from '../models/Sneaker.js';

export default async function handler(req, res) {
    res.setHeader("Access-Control-Allow-Origin", "*");
  res.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
  res.setHeader("Access-Control-Allow-Headers", "Content-Type");

  // ✅ Step 2: Handle preflight OPTIONS request
  if (req.method === "OPTIONS") {
    res.status(200).end(); // Respond to CORS preflight
    return;
  }
  await connectDB();

  if (req.method === 'GET') {
    const { id } = req.query;

    if (id) {
      try {
        const sneaker = await Sneaker.findById(id);
        if (!sneaker) {
          return res.status(404).json({ message: 'Sneaker not found' });
        }
        return res.status(200).json(sneaker);
      } catch (error) {
        return res.status(500).json({ message: 'Invalid ID format', error });
      }
    }

    const sneakers = await Sneaker.find();
    return res.status(200).json(sneakers);
  }

  else if (req.method === 'POST') {
    try {
      const sneaker = new Sneaker(req.body);
      await sneaker.save();
      res.status(201).json(sneaker);
    } catch (error) {
      res.status(400).json({ message: 'Failed to create sneaker', error });
    }
  }

  else {
    res.status(405).json({ message: 'Method not allowed' });
  }
}
