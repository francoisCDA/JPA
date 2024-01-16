//3bis.a
use("info");

//3bis.b
db.produits.insertOne({nom:"Macbook Pro",fabricant: "Apple",prix: 11435.99, options: ["Intel Core i5","Retina Display","Long life battery"]});

//3bis.c
db.produits.insertOne({nom:"Macbook Air",fabricant: "Apple",prix: 125794.73, options: ["Intel Core i7","SSD","Long life battery"]});

//3bis.d
db.produits.insertOne({nom:"Thinkpad X230",fabricant: "Lenovo",prix: 114358.74, options: ["Intel Core i5","SSD","Long life battery"]});


//3bis.A
db.produits.find();

//3bis.B
db.produits.find().limit(1);

//3bis.C
db.produits.find({nom: {$regex: "thinkpad",$options:"i"}});
db.produits.find({_id:  ObjectId('65a68e079a85bdd9c5e2a62e')});

//3bis.D
db.produits.find({prix: {$gte: 13723}});



//db.produits.updateMany({options: {$in :["SSD"]}}, {$set :{ultrabook:true}});

//3bis.E
db.produits.findOne({ultrabook: true});

//3bis.F
db.produits.findOne({nom: {$regex: "macbook",$options:"i"}});

//3bis.G
db.produits.find({nom: {$regex: "^macbook",$options:"i"}});

//3bis.H
db.produits.deleteMany({fabricant:"Apple"});

//3bis.I
db.produits.deleteOne({_id: ObjectId('65a68e079a85bdd9c5e2a62e')});



//db.produits.find();




