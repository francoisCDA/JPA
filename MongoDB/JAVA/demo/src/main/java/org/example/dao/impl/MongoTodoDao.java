package org.example.dao.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.model.TodoItem;

import java.util.ArrayList;
import java.util.List;

public class MongoTodoDao implements org.example.dao.impl.TodoDAO {

    private MongoCollection<Document> collection;


    public MongoTodoDao(MongoDatabase mongoDatabase) {
        this.collection = mongoDatabase.getCollection("todos");
    }

    @Override
    public void addTodo(TodoItem todoItem) {

        Document doc = new Document("title", todoItem.getTitle())
                .append("description",todoItem.getDescription())
                .append("completed",todoItem.isCompleted());
        collection.insertOne(doc);

    }

    @Override
    public List<TodoItem> getAllTodos() {
        List<TodoItem> todos = new ArrayList<>();

        for (Document doc : collection.find()) {
            todos.
        }

        return null;
    }

    private TodoItem documentToDoItem(Document doc) {
        if (doc == null) return null;

        TodoItem item = new TodoItem(doc.getObjectId("_id").toString(), doc.getString("title")),doc.getString()

    }

}
