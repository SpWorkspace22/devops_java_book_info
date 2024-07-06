package com.pluralsight.services;

import com.pluralsight.domain.Book;
import com.pluralsight.repository.AbstractConnection;
import com.pluralsight.repository.Dao;

import java.sql.*;
import java.util.*;

public class BookService extends AbstractConnection implements Dao<Book> {
    private final String GET_ALL = "SELECT * FROM Books";
    private final String SAVE = "INSERT INTO Books(name) VALUES (?)";
    private  final String GET_BY_ID = "SELECT * FROM Books WHERE ID=?";

    @Override
    public Optional<Book> findById(int id) {
        Optional book = Optional.empty();

        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);

        ){
            preparedStatement.setInt(1,id);

            try( ResultSet resultSet = preparedStatement.executeQuery() ){

                if(resultSet.next()){
                    Book rsBook = new Book();
                    rsBook.setId(resultSet.getInt(1));
                    rsBook.setName(resultSet.getString(2));
                    book = Optional.of(rsBook);
                }
            }
            return  book;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = Collections.emptyList();
        Connection connection = getConnection();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(GET_ALL)
        ){

            books = new ArrayList<Book>();

            while (resultSet.next()){
                Book b = new Book();
                b.setId(resultSet.getInt(1));
                b.setName(resultSet.getString(2));

                books.add(b);
            }

            return books;

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }

    }

    @Override
    public Book save(Book book) {

        try(
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SAVE,Statement.RETURN_GENERATED_KEYS)
        ) {


            preparedStatement.setString(1,book.getName());
            preparedStatement.executeUpdate();

            try( ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if(resultSet.next()){
                    book.setId(resultSet.getInt(1));
                }
            }
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
