const mongoose = require('mongoose')
const MultipleChoiceSchema = require("../multipleChoice/multipleChoice.schema.server")
const TrueFalseSchema = require("../trueFalse/trueFalse.schema.server")
const questionSchema = mongoose.Schema({
    _id : Number,
    title: {type: String, default: 'New Question'},
    question: String,
    points: Number,
    questionType: {type: String, enum: ['TRUE_FALSE', 'MULTIPLE_CHOICE']},
    quiz: {type: mongoose.Schema.Types.Number, ref: "QuizModel"},
    multipleChoice: MultipleChoiceSchema,
    trueFalse: TrueFalseSchema,
    answers : [{
        type : mongoose.Schema.Types.Number,
        ref : "AnswerModel"
    }]
}, {collection: 'questions'})
module.exports = questionSchema