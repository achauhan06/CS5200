const mongoose = require('mongoose');
const studentSchema = mongoose.Schema({
    _id : Number,
    username : String,
    password : String, 
    firstName : String, 
    lastName : String,
    gradYear : Number,
    scholarship : Number,
    answers : [{
        type : mongoose.Schema.Types.Number,
        ref : "AnswerModel"
    }]
}, {collection : "students"})
module.exports = studentSchema