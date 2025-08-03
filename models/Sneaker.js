import mongoose from 'mongoose';

const SneakerSchema = new mongoose.Schema({
  name: String,
  pageUrl: String,
  sizes: [
    {
      size: Number,
      price: Number
    }
  ],
  gender: String,
  fresh: Boolean,
  category: String,
  image: String
});

export default mongoose.models.Sneaker || mongoose.model('Sneaker', SneakerSchema);
