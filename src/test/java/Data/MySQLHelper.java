package Data;

import lombok.val;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MySQLHelper {
    static String url = "jdbc:mysql://192.168.99.100:3306/app";
    static String user = "app";
    static String password = "pass";

    public static String getPaymentStatusMySQL () throws SQLException {

        val statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        String paymentStatusMySQL;
        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                );
        ) {
            paymentStatusMySQL = runner.query(conn, statusSQL, new ScalarHandler<>());
        }
        return paymentStatusMySQL;
    }

    public static String getRequestStatusMySQL () throws SQLException {

        val statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        String requestStatusMySQL;
        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                );
        ) {
            requestStatusMySQL = runner.query(conn, statusSQL, new ScalarHandler<>());
        }
        return requestStatusMySQL;
    }

    public static String getCreatedOrderStatusMySQL () throws SQLException {

        val statusSQL = "SELECT created FROM order_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        Timestamp createdOrderStatus;
        String createdOrderStatusMySQL;
        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                );
        ) {
            createdOrderStatus = runner.query(conn, statusSQL, new ScalarHandler<>());
            createdOrderStatusMySQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdOrderStatus);
        }
        return createdOrderStatusMySQL;
    }

    public static String getCreatedPaymentStatusMySQL () throws SQLException {

        val statusSQL = "SELECT created FROM payment_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        Timestamp createdPaymentStatus;
        String createdPaymentStatusMySQL;
        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                );
        ) {
            createdPaymentStatus = runner.query(conn, statusSQL, new ScalarHandler<>());
            createdPaymentStatusMySQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdPaymentStatus);
        }
        return createdPaymentStatusMySQL;
    }

    public static String getCreatedRequestStatusMySQL () throws SQLException {

        val statusSQL = "SELECT created FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        Timestamp createdRequestStatus;
        String createdRequestStatusMySQL;
        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                );
        ) {
            createdRequestStatus = runner.query(conn, statusSQL, new ScalarHandler<>());
            createdRequestStatusMySQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdRequestStatus);
        }
        return createdRequestStatusMySQL;
    }

    public static void cleanDBMySQL () throws SQLException {

        val cleanOrderSQL = "DELETE FROM order_entity;";
        val cleanPaymentSQL = "DELETE FROM payment_entity;";
        val cleanCreditSQL = "DELETE FROM credit_request_entity;";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                );
        ) {
            val cleaningOrder = runner.update(conn, cleanOrderSQL);
            val cleaningPayment = runner.update(conn, cleanPaymentSQL);
            val cleaningCredit = runner.update(conn, cleanCreditSQL);
        } finally {
            Connection conn = DriverManager.getConnection(url, user, password);
            DbUtils.closeQuietly(conn);
        }
    }
}