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
        statement.executeUpdate("DROP TABLE IF EXISTS contains");
        statement.executeUpdate("DROP TABLE IF EXISTS transaction");
        statement.executeUpdate("DROP TABLE IF EXISTS has");
        statement.executeUpdate("DROP TABLE IF EXISTS does");
        statement.executeUpdate("DROP TABLE IF EXISTS asks_for");
        statement.executeUpdate("DROP TABLE IF EXISTS prescribes");
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

        statement.executeUpdate("CREATE TABLE drug(drug_id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                                "name varchar(25) NOT NULL," +
                                "producer varchar(25) NOT NULL," +
                                "price float(2) NOT NULL DEFAULT 0," +
                                "image LONGBLOB)" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE transaction(trans_id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                                "total_price float(0) NOT NULL DEFAULT 0," +
                                "date date NOT NULL," +
                                "time time NOT NULL," +
                                "status varchar(25)," +
                                "patient_username varchar(25) NOT NULL," +
                                "FOREIGN KEY(patient_username) REFERENCES patient(username))" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE does(test_id int NOT NULL," +
                                "app_id int NOT NULL," +
                                "doctor_username varchar(25) NOT NULL," +
                                "PRIMARY KEY(test_id, app_id, doctor_username)," +
                                "FOREIGN KEY (test_id) REFERENCES test(test_id)," +
                                "FOREIGN KEY (app_id) REFERENCES appointment(app_id)," +
                                "FOREIGN KEY (doctor_username) REFERENCES doctor(username))" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE has(pharmacist_username varchar(25) NOT NULL," +
                                "drug_id int NOT NULL," +
                                "stock int NOT NULL," +
                                "PRIMARY KEY(pharmacist_username, drug_id)," +
                                "FOREIGN KEY (pharmacist_username) REFERENCES pharmacist(username)," +
                                "FOREIGN KEY (drug_id) REFERENCES drug(drug_id))" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE asks_for(symp_name varchar(25) NOT NULL," +
                                "app_id int NOT NULL," +
                                "PRIMARY KEY(symp_name, app_id)," +
                                "FOREIGN KEY (symp_name) REFERENCES symptom(symp_name)," +
                                "FOREIGN KEY (app_id) REFERENCES appointment(app_id))" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE prescribes(pres_id int NOT NULL," +
                                "drug_id int NOT NULL," +
                                "description varchar(40) NOT NULL," +
                                "amount int NOT NULL," +
                                "PRIMARY KEY (pres_id, drug_id)," +
                                "FOREIGN KEY (pres_id) REFERENCES prescription (prescription_id)," +
                                "FOREIGN KEY (drug_id) REFERENCES drug (drug_id))" +
                                "ENGINE=INNODB");

        statement.executeUpdate("CREATE TABLE contains(trans_id int PRIMARY KEY NOT NULL," +
                "pharmacist_username varchar(25) NOT NULL," +
                "pres_id int NOT NULL," +
                "FOREIGN KEY (trans_id) REFERENCES transaction(trans_id)," +
                "FOREIGN KEY (pharmacist_username) REFERENCES pharmacist(username)," +
                "FOREIGN KEY (pres_id) REFERENCES prescription(prescription_id))" +
                "ENGINE=INNODB");
    }
}
