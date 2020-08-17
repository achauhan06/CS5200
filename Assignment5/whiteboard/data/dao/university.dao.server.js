const studentDao = require("./student.dao.server")
const quizDao =  require("./quiz.dao.server")
const answerDao =  require("./answer.dao.server")
const questionDao =  require("./question.dao.server")

const truncateDatabase = async() => {

    await answerDao.deleteAll();
    await questionDao.deleteAll();
    await studentDao.deleteAll();
    await quizDao.deleteAll();

    console.log("Deleted all")

};

const populateDatabase = async() => {

    //create students 
    let student1 = {
        _id : 123,
        firstName : "Alice",
        lastName : "Wonderland",
        username : "alice",
        password : "alice",
        gradYear : 2020,
        scholarship : 15000
    }
    await createStudent(student1);


    let student2 = {
        _id : 234,
        firstName : "Bob",
        lastName : "Hope",
        username : "bob",
        password : "bob",
        gradYear : 2021,
        scholarship : 12000
    }
    await createStudent(student2);

    //create quiz 

    let quiz1 = {
        _id : 1,
        title : "Quiz1"
    }

    await createQuiz(quiz1)
    //create questions 

    let question1 = {
        _id : 321,
        question : "Is the following schema valid?",
        points : 10, 
        questionType : "TRUE_FALSE",
        quiz : 1,
        trueFalse : {
            isTrue : false
        }
    }

    await createQuestion(question1);

    let question2 = {
        _id : 432,
        question : "DAO stands for Dynamic Access Object.",
        points : 10, 
        questionType : "TRUE_FALSE",
        quiz : 1,
        trueFalse : {
            isTrue : false
        }
    }

    await createQuestion(question2);

    let question3 = {
        _id : 543,
        question : "What does JPA stand for?",
        points : 10, 
        questionType : "MULTIPLE_CHOICE",
        quiz : 1,
        multipleChoice : {
            choices : "Java Persistence API,Java Persisted Application,JavaScript Persistence API,JSON Persistent Associations",
            correct : 1
        }
    }

    await createQuestion(question3);

    let question4 = {
        _id : 654,
        question : "What does ORM stand for?",
        points : 10, 
        questionType : "MULTIPLE_CHOICE",
        quiz : 1,
        multipleChoice : {
            choices : "Object Relational Model,Object Relative Markup,Object Reflexive Model,Object Relational Mapping",
            correct : 4
        }
    }

    await createQuestion(question4);


    //creating answers 

    let answer1 = {
        _id : 123, 
        student : 123,
        question : 321,
        trueFalseAnswer : true
    }

    await answerQuestion(student1, question1, answer1);

    let answer2 = {
        _id : 234, 
        student : 123,
        question : 432,
        trueFalseAnswer : false
    }

    await answerQuestion(student1, question2, answer2);

    let answer3 = {
        _id : 345, 
        student : 123,
        question : 543,
        multipleChoiceAnswer : 1
    }

    await answerQuestion(student1, question3, answer3);

    let answer4 = {
        _id : 456, 
        student : 123,
        question : 654,
        multipleChoiceAnswer : 2
    }

    await answerQuestion(student1, question4, answer4);

    let answer5 = {
        _id : 567, 
        student : 234,
        question : 321,
        trueFalseAnswer : false
    }

    await answerQuestion(student2, question1, answer5);

    let answer6 = {
        _id : 678, 
        student : 234,
        question : 432,
        trueFalseAnswer : true
    }

    await answerQuestion(student2, question2, answer6);

    let answer7 = {
        _id : 789, 
        student : 234,
        question : 543,
        multipleChoiceAnswer : 3
    }

    await answerQuestion(student2, question3, answer7);

    let answer8 = {
        _id : 890, 
        student : 234,
        question : 654,
        multipleChoiceAnswer : 4
    }
    
    await answerQuestion(student2, question4, answer8);

    console.log("Populated collections")


}

const createStudent = async(student) => studentDao.createStudent(student);
const deleteStudent = async(id) => studentDao.deleteStudent(id);
const createQuestion = async(question) => questionDao.createQuestion(question);
const deleteQuestion = async(id) => questionDao.deleteQuestion(id);
const answerQuestion = async(studentId, questionId, answer) => {
    let question = questionDao.findQuestionById(questionId);
    let questionType = question.questionType;
    return answerDao.answerQuestion(studentId, questionId, questionType, answer)
}

const deleteAnswer = async(id) => answerDao.deleteAnswer(id);

const findAllStudents = async() => studentDao.findAllStudents(); 
const findStudentById = async(id) => studentDao.findStudentById(id);
const findAllQuestions = async() => questionDao.findAllQuestions();
const findQuestionById = async(id) => questionDao.findQuestionById(id);
const findAllAnswers = async() => answerDao.findAllAnswers();
const findAnswerById = async(id) => answerDao.findAnswerById(id); 
const findAnswersByStudent = async(studentId) => answerDao.findAnswersByStudent(studentId);
const findAnswersByQuestion = async(questionId) => answerDao.findAnswersByQuestion(questionId);
const createQuiz = async(quiz) => quizDao.createQuiz(quiz);

module.exports = {
    truncateDatabase,
    populateDatabase,
    createStudent,
    createQuestion,
    deleteStudent,
    deleteQuestion,
    answerQuestion, 
    deleteAnswer,
    findAllStudents,
    findStudentById,
    findAllQuestions,
    findQuestionById,
    findAllAnswers,
    findAnswerById,
    findAnswersByStudent,
    findAnswersByQuestion
}
