package com.step;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao { // Dao - data access object


    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/manager", "postgres", "aozhnl");
        return connection;
    }

    // create
    public void create(Employee employee) throws SQLException {
        Connection connection = getConnection();
        String insert = "INSERT INTO app.employee(name, surname) values(?,?)";
        PreparedStatement statement = connection.prepareStatement(insert);
        statement.setString(1, employee.getName());
        statement.setString(2, employee.getSurname());

        int row = statement.executeUpdate();
        if(row == 0) {
            System.out.println("Eroare: " + row + " randuri au fost modificate");
        }

        statement.close();
        connection.close();
    }


    // get all
    public List<Employee> readAll(String name) {
        String sql = "SELECT id, name, surname where name like '%?%'";
        return null;
    }

    public List<Employee> readAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();

        Connection connection = getConnection();
        String select = "SELECT id, name, surname FROM app.employee order by id asc";

        PreparedStatement statement = connection.prepareStatement(select);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            employees.add(new Employee(id, name, surname));
        }
        statement.close();
        connection.close();
        return employees;
    }

    // update
    public void update(int id, String name, String surname, String address) throws SQLException{
        Connection connection = getConnection();

        String update = "Update app.employee set name=?, surname = ?, address = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(update);
        statement.setString(1, name);
        statement.setString(2, surname);
        statement.setString(3, address);
        statement.setString(4, String.valueOf(id));

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated == 0) {
            System.out.println("Error: " + rowsUpdated + " randuri au fost modificate");
        }

        statement.close();
        connection.close();
    }

    public void update(Employee emp) throws SQLException{
        Connection connection = getConnection();

        String update = "Update app.employee set name=?, surname = ?, address = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(update);
        statement.setString(1, emp.getName());
        statement.setString(2, emp.getSurname());
        statement.setString(3, emp.getAddress());
        statement.setString(4, String.valueOf(emp.getId()));

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated == 0) {
            System.out.println("Error: " + rowsUpdated + " randuri au fost modificate");
        }

        statement.close();
        connection.close();
    }

    // delete
    public void delete(int id) throws SQLException{
        Connection connection = getConnection();

        String delete = "delete from app.employee where id = ?";
        PreparedStatement statement = connection.prepareStatement(delete);
        statement.setString(1, String.valueOf(id));

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated == 0) {
            System.out.println("Error: " + rowsUpdated + " randuri au fost sterse");
        }

        statement.close();
        connection.close();
    }
}