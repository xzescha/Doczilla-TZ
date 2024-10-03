const express = require('express');
const cors = require('cors');
const { Pool } = require('pg');

const app = express();
const port = 3000;

app.use(cors());
app.use(express.json());

const pool = new Pool({
    user: 'postgres',
    host: 'localhost',
    database: 'studentdb',
    password: 'testpass',
    port: 5432,
});


// Получить все задачи
app.get('/todolist', async (req, res) => {
    const result = await pool.query('SELECT * FROM todolist');
    res.json(result.rows);
});

// Создать новую задачу
app.post('/todos', async (req, res) => {
    const { title } = req.body;
    const result = await pool.query('INSERT INTO todolist (title, completed) VALUES ($1, $2) RETURNING *', [title, false]);
    res.json(result.rows[0]);
});

// Обновить задачу
app.patch('/todolist/:id', async (req, res) => {
    const { id } = req.params;
    const { completed } = req.body;
    await pool.query('UPDATE todolist SET completed = $1 WHERE id = $2', [completed, id]);
    res.sendStatus(204);
});

// Удалить задачу
app.delete('/todos/:id', async (req, res) => {
    const { id } = req.params;
    await pool.query('DELETE FROM todolist WHERE id = $1', [id]);
    res.sendStatus(204);
});

app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
});
const path = require('path');

// Раздача статических файлов из папки client
app.use(express.static(path.join(__dirname, 'client')));

// Если маршруты не найдены, отправляем index.html
app.get('*', (req, res) => {
    res.sendFile(path.join(__dirname, '../client', 'index.html'));
});

