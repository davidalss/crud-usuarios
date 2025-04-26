const express = require('express');
const app = express();

app.use(express.json());

let users = []; // Armazenar os usuários

app.get('/', (req, res) => {
    res.send('API CRUD de Usuários');
});

// CREATE - Criar um novo usuário
app.post('/users', (req, res) => {
    const user = req.body;
    user.id = users.length + 1; // cria um ID simples
    users.push(user);
    res.status(201).send(user);
});

// READ - Listar todos os usuários
app.get('/users', (req, res) => {
    res.send(users);
});

// READ - Buscar um usuário pelo ID
app.get('/users/:id', (req, res) => {
    const id = parseInt(req.params.id);
    const user = users.find(u => u.id === id);
    if (user) {
        res.send(user);
    } else {
        res.status(404).send({ message: 'Usuário não encontrado' });
    }
});

// UPDATE - Atualizar um usuário pelo ID
app.put('/users/:id', (req, res) => {
    const id = parseInt(req.params.id);
    const index = users.findIndex(u => u.id === id);
    if (index !== -1) {
        users[index] = { ...users[index], ...req.body };
        res.send(users[index]);
    } else {
        res.status(404).send({ message: 'Usuário não encontrado' });
    }
});

// DELETE - Deletar um usuário pelo ID
app.delete('/users/:id', (req, res) => {
    const id = parseInt(req.params.id);
    const index = users.findIndex(u => u.id === id);
    if (index !== -1) {
        users.splice(index, 1);
        res.send({ message: 'Usuário deletado com sucesso' });
    } else {
        res.status(404).send({ message: 'Usuário não encontrado' });
    }
});

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Servidor rodando na porta ${PORT}`);
});