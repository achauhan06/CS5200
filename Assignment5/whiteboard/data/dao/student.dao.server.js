const studentModel = require("../model/student/student.model.server")

const createStudent = student => studentModel.create(student);
const deleteStudent = id => studentModel.deleteOne({_id : id});
const findAllStudents = () => studentModel.find();
const findStudentById = id => studentModel.findById(id);

const deleteAll = () => studentModel.deleteMany({});

module.exports = {
    createStudent,
    deleteStudent,
    findAllStudents,
    findStudentById,
    deleteAll
}
