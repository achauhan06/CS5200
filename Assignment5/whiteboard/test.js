require("./data/db");
const assert = require('assert');
const universityDao = require("./data/dao/university.dao.server");
const testStudentsInitialCount = async() => {
    let students = await universityDao.findAllStudents();
    console.log("Initital Student Count = " + students.length);
    assert(2 == students.length, "Not equal")
}

const testQuestionsInitialCount = async() => {
    let questions = await universityDao.findAllQuestions();
    console.log("Initital Question Count = " + questions.length);
    assert(4 == questions.length, "Not equal")
}

const testAnswersInitialCount = async() => {
    let answers = await universityDao.findAllAnswers();
    console.log("Initital Answers Count = " + answers.length);
    assert(8 == answers.length, "Not equal")
}

const testDeleteAnswer = async() => {

    let answers = await universityDao.findAllAnswers();
    let answerId = -1; 
    for(let i=0; i<answers.length; i++){
        if(answers[i].student === 234 && answers[i].question === 654){
            answerId = answers[i]._id;
        }
    }

    if(answerId>-1){
        await universityDao.deleteAnswer(answerId);
        let answers = await universityDao.findAllAnswers();
        console.log("New Answers Count = " + answers.length);
        assert(7 == answers.length, "Not equal")

        let bobAnswers = await universityDao.findAnswersByStudent(234)
        console.log("Bob Answers Count = " + bobAnswers.length);
        assert(3 == bobAnswers.length, "Not equal")
    }


}

const testDeleteQuestion = async() => {

    let answers = await universityDao.findAllAnswers();
    for(let i=0; i<answers.length; i++){
        if(answers[i].question === 321){
            let answerId = answers[i]._id;
            await universityDao.deleteAnswer(answerId);
        }
    }

    await universityDao.deleteQuestion(321);

    let questions = await universityDao.findAllQuestions();
    console.log("New Question Count = " + questions.length);
    assert(3 == questions.length, "Not equal")
    
}

const testDeleteStudent = async() => {
    await universityDao.deleteStudent(234);
    let students = await universityDao.findAllStudents();
    console.log("Students Count after deletion = " + students.length);
    assert(1 == students.length, "Not equal")
}

const testAll = async  ()=>{
    await universityDao.truncateDatabase();
    await universityDao.populateDatabase();
    await testStudentsInitialCount();
    await testQuestionsInitialCount();
    await testAnswersInitialCount();
    await testDeleteAnswer();
    await testDeleteQuestion();
    await testDeleteStudent();
}


testAll();