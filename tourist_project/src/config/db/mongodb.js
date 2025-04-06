// Using Node.js `require()`
const mongoose = require('mongoose');

async function connect(){
    try {
        await mongoose.connect('mongodb://127.0.0.1:27017/andatabase')
        .then(() => console.log('MongoDB Connected!'));

    } catch (error) {
        console.log('MongoDB connect Failure!');
    }
}

module.exports = { connect }

