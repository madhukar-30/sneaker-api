import connectDB from '../utils/db.js';
import Sneaker from '../models/Sneaker.js';

export default async function handler(req, res) {
  await connectDB();

  if (req.method === 'GET') {
    const sneakers = await Sneaker.find();
    res.status(200).json(sneakers);
  } else if (req.method === 'POST') {
    const sneaker = new Sneaker(req.body);
    await sneaker.save();
    res.status(201).json(sneaker);
  } else {
    res.status(405).json({ message: 'Method not allowed' });
  }
}
