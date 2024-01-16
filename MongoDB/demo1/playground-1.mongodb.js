// show dbs // lister les bases de données dans le shell

use("demo");

//db.products.insertOne({name:"BPhone",price: 32});

//db.products.insertOne({name:"APhone",price: 45});

//db.products.find();

// db.createCollection("notes");

//db.notes.insertOne({matiere: "Java", date: new Date("2015-05-21"),note: 15});



//db.products.find();


// Crud
//Insertion ou creation

// db.products.insertOne({nom:"Table",dimension:15,poid:500,prix:50});
// db.products.insertMany([{nom:"Table2",dimension:18,poid:550,prix:55},
//                         {nom:"Chaise",dimension:10,poid:80,prix:40},
//                         {nom:"Chaise2",dimension:11,poid:90,prix:50},
//                         {nom:"Armoire",dimension:25,poid:400,prix:80,couleur:["bleu","blanc","rouge"]},
//                         {nom:"Table",dimension:15,poid:500,prix:50}])

// db.products.insertOne({nom:"Voiture",marque:"Renault",conducteur:[{nom:"Roger",age:50},{nom:"Robert",age:55,permis:false}]})

//db.products.insertOne({_id:2500, name:"Avion",carburant:"essence"});

db.products.find();


