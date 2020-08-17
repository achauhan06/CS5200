const answerModel = require("../model/answer/answer.model.server")

const answerQuestion = (studentId, questionId, type, answer) => answerModel.create({
    _id : answer._id,
    student : studentId,
    question : questionId,
    [type] : answer.value
})
const deleteAnswer = id => answerModel.deleteOne({_id : id});
const findAllAnswers = () => answerModel.find();
const findAnswerById = id => answerModel.findById(id);
const findAnswersByStudent = studentId => answerModel.find({
    student : studentId
});

const findAnswersByQuestion = questionId => answerModel.find({
    question : questionId
});

const deleteAll = () => answerModel.deleteMany({});


module.exports = {
    answerQuestion,
    deleteAnswer,
    findAllAnswers,
    findAnswerById,
    findAnswersByQuestion,
    findAnswersByStudent,
    deleteAll
}