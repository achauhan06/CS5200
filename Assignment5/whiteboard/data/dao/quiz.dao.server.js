const quizModel = require("../model/quiz/quiz.model.server")

const createQuiz = quiz => quizModel.create(quiz);
const deleteQuiz = id => quizModel.deleteOne({_id : id});
const findAllQuizzes = () => quizModel.find();
const findQuizById = id => quizModel.findById(id);
const deleteAll = () => quizModel.deleteMany({});

module.exports = {
    createQuiz,
    deleteQuiz,
    findAllQuizzes,
    findQuizById,
    deleteAll
}
