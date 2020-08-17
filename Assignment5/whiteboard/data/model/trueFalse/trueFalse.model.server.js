const mongoose = require("mongoose")
const trueFalseSchema = require("./trueFalse.schema.server")
const trueFalseModel = mongoose.model("TrueFalseModel", trueFalseSchema)
module.exports = trueFalse