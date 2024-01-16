// aggregation :

use("demo_aggr");


// récupère tous les resto dont le ratting est à 5
db.restaurant.aggregate({$match: {rating: 5}},{$count:"comptage"}); 