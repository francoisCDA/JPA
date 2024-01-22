use("tp_aggr");


db.livres.aggregate({ $match: { authors: "Toru Ishida", type: "Article" } }); // récupère les articles de Toru Ishida

//Trier les publications de « Toru Ishida » par titre de livre et par page de début.
db.livres.aggregate([{ $match: { authors: "Toru Ishida", type: "Article" } }, { $sort: { booktitle: 1, "pages.start": 1 } }]);

// Projeter le résultat sur le titre de la publication, et les pages. 
db.livres.aggregate([{ $match: { authors: "Toru Ishida", type: "Article" } }, { $sort: { booktitle: 1, "pages.start": 1 } }, { $project: { title: 1, pages: 1 } }]);

// Compter le nombre de ses publications. 
db.livres.aggregate([{ $match: { authors: "Toru Ishida", type: "Article" } }, { $count: "nombre" }]);

//Compter le nombre de publications depuis 2011 et par type. 
db.livres.aggregate([{ $match: { year: { $gte: 2011 } } }, { $group: { _id: "$type", count: { $sum: 1 } } }]);

//Compter le nombre de publications par auteur et trier le résultat par ordre croissant. 
db.livres.aggregate([{ $unwind: "$authors" }]);

db.livres.aggregate([{ $unwind: "$authors" }, { $group: { _id: "$authors", participation: { $sum: 1 } } }, { $sort: { participation: -1 } }]);

db.livres.distinct("authors");

db.livres.find();


// 1. Afficher la liste des restaurants mais limitez l’affichage à 10.
db.restaurants.find().limit(10);

// 2. Afficher la liste des 10 premiers restaurants mais en triant cette liste par ordre alphabétique.
db.restaurants.find().limit(10).sort({name:1});

// 3. Afficher la liste des 10 premiers restaurants mais en triant cette liste par ordre alphabétique, mais uniquement ceux sur “Brooklyn” (champs : borough)..
db.restaurants.aggregate([{$limit:10 }, { $match: {borough:"Brooklyn"} }, {$sort : {name:1}}]);

// 4. Afficher la liste des 10 premiers restaurants mais on affiche que le nom du restaurant et son quartier.
db.restaurants.aggregate([{$limit:10 }, {$project : {name :1 , borough:1, _id:0}}]);

// 5. Afficher la liste des 10 premiers restaurants mais on affiche tout sauf adresse et le grade.
db.restaurants.aggregate([{$limit:10 }, {$project : {address :0 , grades:0}}]);

// 6. Afficher la liste des 10 premiers restaurants avec un nouveau champ qui va afficher le nombre d’avis (grades) par restaurant.
db.restaurants.aggregate([{$limit:10 }, { $unwind: "$grades" }, {$group: { _id: "$name", nmbre_avis : {$sum: 1}} }]);

// 7. Afficher la liste des 10 premiers restaurants avec un nouveau champ qui va afficher le nombre d’avis (grades) par restaurant et il faudra faire le tri par nombre d’avis.
db.restaurants.aggregate([{$limit:10 }, { $unwind: "$grades" }, {$group: { _id: "$name", nmbre_avis : {$sum: 1}} }, {$sort : {nmbre_avis:1}}]);

// 8. On souhaite toujours afficher la liste des 10 premiers restaurants en affichant le nom du restaurant en majuscule et le quartier du restaurant.
db.restaurants.aggregate([{$limit:10 }, {$project: { _id:0, name: {$toUpper:"$name"}, borough:1  }} ])

// 9. On souhaite toujours afficher la liste des 10 premiers restaurants en affichant le nom du restaurant en majuscule et les 3 premières lettres du quartier.
db.restaurants.aggregate([{$limit:10 }, {$project: { _id:0, name: {$toUpper:"$name"}, borough: {$substr: ["$borough",0,3]}  }} ])

// 10. On souhaite avoir le nombre total de restaurants toujours avec agrégation.
db.restaurants.aggregate({ $group: {_id:null,  nbre_resto: {$sum:1}} });

// 11. On souhaite avoir le nombre de restaurants par quartier (borough)
db.restaurants.aggregate({ $group: {_id:"$borough",  nbre_resto: {$sum:1}} });









