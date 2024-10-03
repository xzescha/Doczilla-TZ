package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String middleName = request.getParameter("middleName");
        String birthDate = request.getParameter("birthDate");
        String studentGroup = request.getParameter("studentGroup");

        try {
            Class.forName("org.postgresql.Driver"); // Загружаем драйвер
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studentsdb", "dbuser", "testpass");
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO students (first_name, last_name, middle_name, birth_date, student_group) VALUES (?, ?, ?, ?, ?)")) {

                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, middleName);
                stmt.setDate(4, Date.valueOf(birthDate));
                stmt.setString(5, studentGroup);
                stmt.executeUpdate();

                response.getWriter().write("Student added successfully");
            } catch (SQLException e) {
                response.getWriter().write("Error: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
