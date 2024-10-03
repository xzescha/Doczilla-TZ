package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/listStudents")
public class ListStudentsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver"); // Загружаем драйвер
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studentsdb", "dbuser", "testpass");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

                JSONArray studentsArray = new JSONArray();

                while (rs.next()) {
                    JSONObject student = new JSONObject();
                    student.put("student_id", rs.getInt("student_id"));
                    student.put("first_name", rs.getString("first_name"));
                    student.put("last_name", rs.getString("last_name"));
                    student.put("middle_name", rs.getString("middle_name"));
                    student.put("birth_date", rs.getDate("birth_date").toString());
                    student.put("student_group", rs.getString("student_group"));
                    studentsArray.put(student);
                }

                response.setContentType("application/json");
                response.getWriter().write(studentsArray.toString());
            } catch (SQLException e) {
                response.getWriter().write("Error: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
