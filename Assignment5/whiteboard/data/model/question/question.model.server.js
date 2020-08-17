  
const mongoose = require('mongoose')
const questionsSchema = require("./question.schema.server")
const questionsModel = mongoose.model("QuestionModel", questionsSchema)
module.exports = questionsModel