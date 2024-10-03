package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        try {
            Class.forName("org.postgresql.Driver"); // Загружаем драйвер
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studentsdb", "dbuser", "testpass");
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM students WHERE student_id = ?")) {

                stmt.setInt(1, Integer.parseInt(studentId));
                stmt.executeUpdate();

                response.getWriter().write("Student deleted successfully");
            } catch (SQLException e) {
                response.getWriter().write("Error: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
