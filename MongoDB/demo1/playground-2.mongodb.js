
use("book");

//db.books.find();


// READ

// db.books.find({_id:45});

 // db.books.find().limit(5);

//db.books.find({authors:{$size:2}}); // trouver les livres avec exactement 2 auteurs



// comparateur > >= < <= : gt, gte, lt, lte 

//db.books.find({pageCount: {$gte: 400}}); // livre dont le nombre de page est supérieur ) "greater than or e egal"

//db.books.find({$and: [{_id: {$gt:25}},{_id : {$lt:50}}] }); = id > 25 && id < 50


//db.books.find({_id:{$in:[15,20,2]}}); // recherche liste d'éléments

//db.books.find({_id:80});  // élément non trouvé = tableau vide



// .skip(10) = saute 10 éléments

//db.books.find({authors:{$size:2}}).skip(10).sort({title:1,_id:-1}).limit(4) ;

//db.books.find({authors:{$size:2}}).sort({title:1,_id:-1}).skip(10).limit(4);



// projection

//db.books.find({_id: {$gt:45}},{_id:1, authors:1}); // n'affiche que les id et auteurs des livres dont l'id est > 45
//db.books.find({_id: {$gt:45}},{authors:0}); // affiche toutes les info sauf les auteurs des livres dont l'id est > 45


//db.books.find({categories: {$in :['Java',"Computer Graphics"]} })

//db.books.find({categories: {$all :['Java',"Computer Graphics"]} })

//db.books.find({$or: [{_id:19},{_id:80}]});

// REGEX commence par ... 
// db.books.find({longDescription: {$regex: "^ext",$options:"i"}}); 

// REGEX contient ...
//db.books.find({longDescription: {$regex: "distribut",$options:"i"}});

// REGEX qui termine par
//db.books.find({title: {$regex: "Perl$",$options:"i"}});



























