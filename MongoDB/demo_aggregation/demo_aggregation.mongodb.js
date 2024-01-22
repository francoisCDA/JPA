use("demo_agrr");

db.restaurant.find();


//récupère tous les resto dont le ratting est à 5
db.restaurant.aggregate({ $match: { rating: 5 } });
db.restaurant.aggregate({ $match: { rating: 5 } }, { $count: "comptage" });

db.restaurant.aggregate({ $match: { rating: 5 } }, { $project: { URL: 1, name: 1 } }); // optenir que les éléments URL et name

db.restaurant.aggregate({ $group: { _id: "$type_of_food" } });

db.restaurant.aggregate({ $group: { _id: "$type_of_food", count: { $sum: 1 } } });
db.restaurant.aggregate({ $group: { _id: "$type_of_food", counter: { $sum: 1 } } });

//enchainer les filtres dans un Array
db.restaurant.aggregate([{ $group: { _id: "$type_of_food", counter: { $sum: 1 } } }, { $match: { counter: { $gt: 10 } } }, { $sort: { counter: -1 } }]);

db.restaurant.aggregate({ $group: { _id: "$postcode", nombre_restau: { $sum: 2 } } }); // compte 2 par 2

db.restaurant.aggregate({ $match: { type_of_food: "Thai", rating: { $gte: 4 } } });


db.restaurant.aggregate([{ $match: { type_of_food: "Thai", rating: { $gte: 4 } } }, { $project: { _id: 0, type_of_food: 1, rating: 1 } }, { $limit: 3 }]);


db.restaurant.aggregate({ $match: { type_of_food: "Caribbean" } }, { $count: "nbre_resto_caribbean" });

db.restaurant.aggregate({ $group: { _id: "$type_of_food", note_moyenne: { $avg: "$rating" } } }); // score moyen


db.restaurant.aggregate([{ $match: { rating: { $ne: "Not yet rated" } } }, { $group: { _id: "$type_of_food" }, total_rating: { $sum: "$rating" }, avg_rating: { $avg: "$rating" }, max_rating: { $max: "$rating" }, min_rating: { $min: "$rating" } }]);







