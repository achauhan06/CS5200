const mongoose = require('mongoose')
//const questionSchema = require('./question.schema.server')
const quizSchema = mongoose.Schema({
 _id : Number,
 title : String,
 questions: [{
   type: mongoose.Schema.Types.Number,
   ref: 'QuestionModel'
 }]
}, {collection: 'quizzes'})

module.exports = quizSchema