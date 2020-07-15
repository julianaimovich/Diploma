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

public class PsqlHelper {
    static String url = "jdbc:postgresql://192.168.99.100:5432/app";
    static String user = "app";
    static String password = "pass";

    public static String getPaymentStatusPsql () throws SQLException {

        val statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        String paymentStatusPsql;
        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                );
        ) {
            paymentStatusPsql = runner.query(conn, statusSQL, new ScalarHandler<>());
        }
        return paymentStatusPsql;
    }

    public static String getRequestStatusPsql () throws SQLException {

        val statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        String requestStatusPsql;
        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                );
        ) {
            requestStatusPsql = runner.query(conn, statusSQL, new ScalarHandler<>());
        }
        return requestStatusPsql;
    }

    public static String getCreatedOrderStatusPsql () throws SQLException {

        val statusSQL = "SELECT created FROM order_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        Timestamp createdOrderStatus;
        String createdOrderStatusPsql;
        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                );
        ) {
            createdOrderStatus = runner.query(conn, statusSQL, new ScalarHandler<>());
            createdOrderStatusPsql = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdOrderStatus);
        }
        return createdOrderStatusPsql;
    }

    public static String getCreatedPaymentStatusPsql () throws SQLException {

        val statusSQL = "SELECT created FROM payment_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        Timestamp createdPaymentStatus;
        String createdPaymentStatusPsql;
        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                );
        ) {
            createdPaymentStatus = runner.query(conn, statusSQL, new ScalarHandler<>());
            createdPaymentStatusPsql = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdPaymentStatus);
        }
        return createdPaymentStatusPsql;
    }

    public static String getCreatedRequestStatusPsql () throws SQLException {

        val statusSQL = "SELECT created FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        Timestamp createdRequestStatus;
        String createdRequestStatusPsql;
        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                );
        ) {
            createdRequestStatus = runner.query(conn, statusSQL, new ScalarHandler<>());
            createdRequestStatusPsql = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdRequestStatus);
        }
        return createdRequestStatusPsql;
    }

    public static void cleanDBPsql () throws SQLException {

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
