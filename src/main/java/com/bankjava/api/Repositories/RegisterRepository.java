package com.bankjava.api.Repositories;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bankjava.api.Configurations.DatabaseConfig;

import com.bankjava.contracts.RegisterRequest;



public interface RegisterRepository {

    boolean saveUser(RegisterRequest registerRequest);
    
}


class RegisterRepositoryImpl implements RegisterRepository {

    private HikariDataSource databaseConfig;


    RegisterRepositoryImpl() {
        this.databaseConfig = new DatabaseConfig().postgresConfig();
    }

    public boolean saveUser(RegisterRequest registerRequest) {

        try (Connection connection = this.databaseConfig.getConnection()) {

            String queryString = "INSERT INTO users (id, name, email) VALUES (?, ?, ?)";

            PreparedStatement statementConnection = connection.prepareStatement(queryString);

            statementConnection.setString(1, registerRequest.getFullname());
            statementConnection.setString(2, registerRequest.getNickname());
            statementConnection.setDate(3, registerRequest.getBirthDate());
            statementConnection.setString(4, registerRequest.getEmail());
            statementConnection.setString(5, registerRequest.getPhoneNumber());
            statementConnection.setString(6, registerRequest.getPassword());
            statementConnection.setBoolean(7, true);

            
        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            // Close the connection pool
            if (this.databaseConfig != null) {
                this.databaseConfig.close();
            }

            this.databaseConfig.close();
        }

        return false;

    }

}
