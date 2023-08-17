package com.bankjava.api.Configurations;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class DatabaseConfig extends AppConfig {


    public HikariDataSource postgresConfig() {

        // Configure the HikariCP connection pool
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(
            "jdbc:postgresql://" +
            this.getProperty("DB_POSTGRE.HOST") + ":" +
            this.getProperty("DB_POSTGRE.PORT") + "/" +
            this.getProperty("DB_POSTGRE.DBNAME")
        );

        hikariConfig.setUsername(this.getProperty("DB_POSTGRE.USERNAME"));
        hikariConfig.setPassword(this.getProperty("DB_POSTGRE.PASSWORD"));

        hikariConfig.setMaximumPoolSize(
            Integer.parseInt(
                this.getProperty("DB_POSTGRE.MAXPOOLSIZE")
            )
        );


        return (HikariDataSource) new HikariDataSource(hikariConfig);

    }
    
}
