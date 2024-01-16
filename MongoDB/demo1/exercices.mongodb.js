// hopital

use("hopital");


//question 1
 db.patient.insertMany([{firstname:"Jean-Pierre",lastname:"Martin",age:35,history:[{desease:"covid",treatment:"doliprane"},{desease:"rougeole",treatment:"aspirine"}]},
                       {firstname:"Robert",lastname:"Roger",age:75,history:[{desease:"gueule de bois",treatment:"eau"},{desease:"fracture de l'humerus",treatment:"doliprane"}]},
                       {firstname:"Jean-Claude",lastname:"Marconi",age:45,history:[{desease:"bluf",treatment:"Biales"}]}]);


// question 2
db.patient.updateOne({_id: ObjectId("65a663dd0f5297c9ca238d6a")}, {$inc: {age:1}});
db.patient.updateOne({_id: ObjectId("65a663dd0f5297c9ca238d6a")}, {$set: {lastname:"Bill"}});

db.patient.updateOne({_id: ObjectId("65a663dd0f5297c9ca238d6a")}, {$push: {history:{desease:"ebola",treatment:"aspirine"}}});

// question 3
db.patient.find({age:{$gt:29}});


//db.patient.find( {history : { $elemMatch: {desease : "ebola"},}});

//db.patient.find({ "history.treatment" : "doliprane"});

//db.patient.find()

//db.patient.find();


//question 4
db.patient.deleteMany({"history.desease" : "ebola"});

db.patient.find();