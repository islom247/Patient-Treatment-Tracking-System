import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://dijkstra.ug.bcc.bilkent.edu.tr/islomiddin";
        String username = "islomiddin";
        String password = "1cf78fqf";
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new SQLException("Wrong URL or username or password. Please, check those and try again.");
        }
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS appointment");
        statement.executeUpdate("DROP TABLE IF EXISTS pharmacist");
        statement.executeUpdate("DROP TABLE IF EXISTS patient");
        statement.executeUpdate("DROP TABLE IF EXISTS doctor");
        statement.executeUpdate("DROP TABLE IF EXISTS user");
        statement.executeUpdate("DROP TABLE IF EXISTS hospital");
        statement.executeUpdate("DROP TABLE IF EXISTS address");
        statement.executeUpdate("DROP TABLE IF EXISTS test");
        statement.executeUpdate("DROP TABLE IF EXISTS symptom");
        statement.executeUpdate("DROP TABLE IF EXISTS disease");
        statement.executeUpdate("DROP TABLE IF EXISTS prescription");
        statement.executeUpdate("DROP TABLE IF EXISTS drug");
        statement.executeUpdate("DROP TABLE IF EXISTS transaction");
        statement.executeUpdate("DROP TABLE IF EXISTS does");
        statement.executeUpdate("DROP TABLE IF EXISTS has");
        statement.executeUpdate("DROP TABLE IF EXISTS asks_for");
        statement.executeUpdate("DROP TABLE IF EXISTS prescribes");
        statement.executeUpdate("DROP TABLE IF EXISTS contains");
        statement.executeUpdate("DROP TABLE IF EXISTS diagnose");
        statement.executeUpdate("DROP TABLE IF EXISTS results");
        statement.executeUpdate("DROP TABLE IF EXISTS component");
        statement.executeUpdate("DROP TABLE IF EXISTS consists");

        //Creating tables
        statement.executeUpdate("CREATE TABLE address(add_id int NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                                "country varchar(20) NOT NULL," +
                                "city varchar(20) NOT NULL," +
                                "street varchar(25) NOT NULL," +
                                "apartment varchar(20)," +
                                "apartment_num int)" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE user(username varchar(25) NOT NULL PRIMARY KEY," +
                                "image LONGBLOB," +
                                "phone varchar(20) NOT NULL," +
                                "name varchar(20) NOT NULL," +
                                "password varchar(20) NOT NULL," +
                                "birthday date NOT NULL," +
                                "gender varchar(20)," +
                                "add_id int NOT NULL," +
                                "FOREIGN KEY (add_id) REFERENCES address(add_id)," +
                                "UNIQUE (username))" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE pharmacist(username varchar(25) NOT NULL," +
                                "delivery_cost float(2) NOT NULL DEFAULT 0," +
                                "discount int DEFAULT 0," +
                                "FOREIGN KEY (username) REFERENCES user(username))" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE patient(username varchar(25) NOT NULL," +
                                "FOREIGN KEY(username) REFERENCES user(username))" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE hospital(hos_id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                                "name varchar(20) NOT NULL," +
                                "phone varchar(20) NOT NULL," +
                                "image LONGBLOB," +
                                "add_id int NOT NULL," +
                                "FOREIGN KEY(add_id) REFERENCES address(add_id))");

        statement.executeUpdate("CREATE TABLE doctor(username varchar(25) NOT NULL," +
                                "specialization varchar(20) NOT NULL," +
                                "experience int DEFAULT 0," +
                                "education varchar(25) NOT NULL," +
                                "work_time_begin time NOT NULL," +
                                "work_time_end time NOT NULL," +
                                "hos_id int," +
                                "FOREIGN KEY(username) REFERENCES user(username)," +
                                "FOREIGN KEY(hos_id) REFERENCES hospital(hos_id))" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE appointment(app_id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                                "status varchar(25) NOT NULL," +
                                "date date NOT NULL," +
                                "time time NOT NULL," +
                                "patient_username varchar(25) NOT NULL," +
                                "doctor_username varchar(25) NOT NULL," +
                                "FOREIGN KEY(patient_username) REFERENCES patient(username)," +
                                "FOREIGN KEY(doctor_username) REFERENCES doctor(username))" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE test(test_id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                                "name varchar(40) NOT NULL," +
                                "result varchar(40) NOT NULL)" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE symptom(symp_name varchar(25) NOT NULL PRIMARY KEY," +
                                "type varchar(25) NOT NULL," +
                                "description varchar(40))" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE disease(dis_id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                                "name varchar(20) NOT NULL," +
                                "degree varchar(20))" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE prescription(prescription_id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                                "date date NOT NULL)" +
                                "ENGINE=INNODB");
    }
}
