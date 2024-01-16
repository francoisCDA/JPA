use("tp3");


//q1
db.book.find({type:"Book"});


//q2
db.book.find({year: {$gte:2012}});

//q3
db.book.find({ $and :[{year:{$gte:2014}},{type:"Book"} ]});
db.book.find({$gte:2014},{type:"Book"});

//q4
db.book.find({authors : {$in: ["Toru Ishida"]}});
db.book.find({authors : "Toru Ishida"});


//db.book.find({publisher:""});

//q5
db.runCommand({distinct:"book",key:"publisher"});
db.book.distinct("publisher");

//db.book.find();

//q6
db.runCommand({distinct:"book",key:"authors"});
db.book.distinct(authors);

