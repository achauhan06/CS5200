const questionModel = require("../model/question/question.model.server")

const createQuestion = question => questionModel.create(question);
const deleteQuestion = id => questionModel.deleteOne({_id : id});
const findAllQuestions = () => questionModel.find();
const findQuestionById = id => questionModel.findById(id);

const deleteAll = () => questionModel.deleteMany({});

module.exports = {
    createQuestion,
    deleteQuestion,
    findAllQuestions,
    findQuestionById,
    deleteAll
}
