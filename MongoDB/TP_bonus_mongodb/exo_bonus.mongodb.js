use("tp3");

//db.sportifs.find();
//db.gymnases.find();

// 1. Quels sont les sportifs (identifiant, nom et prénom) qui ont un âge entre 20 et 30 ans ?
//db.sportifs.find({$and: [ {Age : {$gte:20}},{Age : {$lte:25}}]}, {_id:1,Nom:1,Prenom:1});


// 2. Quels sont les gymnases de ville “Villetaneuse” ou de “Sarcelles” qui ont une surface de plus de 400 m2 ?
//db.gymnases.find({ $and : [{$or : [ {Ville : "VILLETANEUSE"}, {Ville : "SARCELLES"} ]  }, {Surface : {$gte :4}} ]});

// 3. Quels sont les sportifs (identifiant et nom) qui pratiquent du handball ?
//db.sportifs.find({"Sports.Jouer" : "Hand ball"}, {_id:1,Nom:1});

// 4. Quels sportifs (identifiant et nom) ne pratiquent aucun sport ?
//db.sportifs.find({"Sports.Jouer" : {$exists : false}});
//db.sportifs.aggregate([{ $match: {"Sports.Jouer" : {$exists : false}}}, {$project:{Nom:1, Prenom:1, _id:0 }}]);

// 5. Quels gymnases n’ont pas de séances le dimanche ?
//db.gymnases.find({"Seances.Jour":{ $not : { $regex: /^dimanche$/i }}})

// 6. Quels gymnases ne proposent que des séances de basket ball ou de volley ball ?
//db.gymnases.find({"Seances.Libelle": { $all : [ "Basket ball" , "Volley ball" ]}});

//db.gymnases.find( { "$nor": [ { "Seances.Libelle": { "$ne": "Basket ball"}}, { "Seances.Libelle": { "$ne": "Volley ball" }} ] }, { "_id": 0,"NomGymnase": 1, "Ville": 1, "Seances.Libelle": 1 })

// 7. Quels sont les entraîneurs qui sont aussi joueurs ?
//db.sportifs.find({ $and : [{"Sports.Jouer" : {$exists : true}}, {"Sports.Entrainer" : {$exists : true}} ]});

// 8. Pour le sportif “Kervadec” quel est le nom de son conseiller ?
//db.sportifs.find({Nom : "KERVADEC"});
/*
db.sportifs.aggregate([ {$lookup: {
  from: "sportifs",
  localField: "IdSportifConseiller",
  foreignField: "IdSportif",
  as: "conseiller"
}}, {$match: {Nom : "KERVADEC"}} , {$project: {"conseiller.Nom":1} }  ]);
*/

// 9. Quelle est la moyenne d’âge des sportives qui pratiquent du basket ball ?
//db.sportifs.aggregate([{$match:{Sexe:"F","Sports.Jouer":"Basket ball"}}, { $group: { _id: "Sexe", "moyenne d'age": {$avg: "$Age"}}}]);


// 10.Quels entraîneurs n’entraînent que du hand ball ou du basket ball ?
// marche pas 
//db.sportifs.aggregate({$match : {"Sports.Entrainer": { $elemMatch : { $in : ["Hand ball", "Basket ball"] }}}});
// marche pas db.sportifs.aggregate({$match : {"Sports.Entrainer": { $not : { $nin : ["Hand ball", "Basket ball"] }}}});
// marche pas db.sportifs.aggregate({$match : {"Sports.Entrainer": { $not : { $elemMatch : { $nin :["Hand ball", "Basket ball"]} }}}});
// marche pas db.sportifs.aggregate({$match : {"Sports.Entrainer": { $elemMatch: { $nin :["Hockey", "Badmington", "Ping pong", "Boxe", "Volley ball", "Tennis"] }}}});
// marche un peu 
//db.sportifs.find({ $and : [{"Sports.Entrainer": { $nin :["Hockey", "Badmington", "Ping pong", "Boxe", "Volley ball", "Tennis"] }} , {"Sports.Entrainer": { $exists :true}} ]});

// marche pas db.sportifs.find( { "Sports.Entrainer" : { $size:1 , $all : ["Hand ball", "Basket ball"] }});

// marche pas db.sportifs.find({"Sports.Entrainer":{ $elemMatch : { $nin : ["Hand ball", "Basket ball"] }}});


// 11. Pour chaque sportif donner le nombre de sports qu’il arbitre
//db.sportifs.aggregate([{$unwind : "$Sports.Arbitrer"}, {$group :{ _id : "$Nom", arbitrage: {$sum:1} }}]);
