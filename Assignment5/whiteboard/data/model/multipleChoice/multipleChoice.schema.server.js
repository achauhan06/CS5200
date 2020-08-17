const mongoose = require("mongoose")
const multipleChoiceSchema = mongoose.Schema({
    _id : Number,
     choices : String,
     correct : Number
}, {collection : "multipleChoice"})
module.exports = multipleChoiceSchema