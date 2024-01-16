 use("user");
 db.user.find();

// // 1
// db.user.insertOne({name:"Chuck Norris",age:77,hobbies:["Karate","Kung-fu","Ruling the world"]});

// // 2.1
// db.user.find({name:"Chuck Norris"});

// // 2.2
// db.user.find({name:"Chuck Norris"},{_id:0});

// //2.3
// db.user.find({$and: [ {age : {$gt:20}},{age : {$lt:25}}  ]});

// //2.4
// db.user.find({$and: [ {age: {$in :[30,40]}, {gender:"male"} ]}});

// //2.5
// db.user.find({"address.state":"Louisiana"});

// //2.6
// db.user.find().sort({age:-1}).limit(20);

// //2.7
// db.user.find({ $and : [ {gender:"female"} ,{age: 30 }]}).count();

// //3.1
// db.user.updateMany({},{$unset : {phone : 0} });

// //3.2
// db.user.updateOne({name:"Chuck Norris"},{$set : {age:"infinity"}});

// //3.3
// db.user.updateMany({age:{$gte:50}},{$push: {hobbies:"jardinage"}});