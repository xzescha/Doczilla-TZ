// Обработка формы добавления студента
$('#addStudentForm').submit(function(event) {
    event.preventDefault(); // Предотвращаем стандартную отправку формы

    $.ajax({
        url: '/addStudent',
        type: 'POST',
        data: $(this).serialize(), // Отправляем данные формы
        success: function(response) {
            alert(response); // Показать ответ от сервера
        }
    });
});

// Обработка формы удаления студента
$('#deleteStudentForm').submit(function(event) {
    event.preventDefault(); // Предотвращаем стандартную отправку формы

    $.ajax({
        url: '/deleteStudent',
        type: 'POST',
        data: $(this).serialize(), // Отправляем данные формы
        success: function(response) {
            alert(response); // Показать ответ от сервера
        }
    });
});

// Обработка кнопки показа списка студентов
$('#listStudentsButton').click(function() {
    $.ajax({
        url: '/listStudents',
        type: 'GET',
        success: function(response) {
            let studentsList = JSON.parse(response); // Преобразуем JSON-ответ в массив
            $('#studentsTable tbody').empty(); // Очищаем таблицу

            // Добавляем строки в таблицу
            studentsList.forEach(function(student) {
                $('#studentsTable tbody').append(
                    `<tr>
                        <td>${student.student_id}</td>
                        <td>${student.first_name}</td>
                        <td>${student.last_name}</td>
                        <td>${student.middle_name}</td>
                        <td>${student.birth_date}</td>
                        <td>${student.student_group}</td>
                    </tr>`
                );
            });
        }
    });
});
