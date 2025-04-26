const express = require('express');
const app = express();

app.use(express.json()); // permite trabalhar com JSON

app.get('/', (req, res) => {
    res.send('API CRUD de Usuários');
});

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Servidor rodando na porta ${PORT}`);
});

