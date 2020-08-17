const mongoose = require("mongoose")
const multipleChoiceSchema = require("./multipleChoice.schema.server")
const multipleChoiceModel = mongoose.model("MultipleChoiceModel", multipleChoiceSchema)
module.exports = multipleChoiceModel