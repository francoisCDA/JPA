use("book");

//db.books.updateOne({_id:46},{$set:{status:"CANCELED", pageCount:6000}});

//db.books.updateOne({pageCount:6000},{$set:{isbn:"12547896451"}});

//db.books.find({pageCount:6000});


//db.books.updateMany({_id:{$in: [55,75]}},{$set:{status:"CANCELED"}});

//db.books.find({_id: {$in: [55,75]}});


// increment
//db.books.updateOne({_id:46},{$inc: {pageCount:1500}} );

//db.books.find({_id: 46});


/// unset
//db.books.updateOne({_id:45},{$unset:{authors: []}});

//db.books.updateOne({_id:45},{$unset:{thumbnailUrl: ''}});

//db.books.updateOne({_id:45},{$unset:{pageCount: 0}});
//db.books.updateOne({_id:45},{$set:{pageCount: 1250}});

//rename
//db.books.updateOne({_id:45},{$rename: {status : "position"}});


// max
//db.books.updateOne({_id:45},{$max: {pageCount : 5000}});

// min
//db.books.updateOne({_id:45},{$min: {pageCount : 1000}});


//db.books.findOneAndDelete({_id:45});


//db.books.find({_id: 45});

db.books.deleteMany({});