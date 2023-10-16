package com.example.sample1.dao;

import com.example.sample1.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class TodoDao {

    @Autowired
    private DataSource dataSource;

    public List<Todo> list() throws  Exception {
        String sql = "SELECT * FROM todo ORDER BY id DESC";
        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Todo> list = new ArrayList<>();
        try(con; stmt; rs;){
            while (rs.next()){
                Todo todo = new Todo();
                todo.setId(rs.getInt("id"));
                todo.setTodo(rs.getString("todo"));
                todo.setInserted(rs.getTimestamp("inserted").toLocalDateTime());

                list.add(todo);
            }
        }
        return null;
    }

    public void insert(Todo todo) {

    }
}
