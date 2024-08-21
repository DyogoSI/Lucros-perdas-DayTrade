<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lucros de DayTrade</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('${pageContext.request.contextPath}/css/imagecss.png') no-repeat center center fixed;
            background-color: #f4f7f6;
            color: #333;
            margin: 0;
            padding: 0;
        }
        h2 {
            text-align: center;
            color: #4CAF50;
            padding: 20px;
            border-bottom: 2px solid #4CAF50;
        }
        form {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background: rgba(0, 0, 0, 0.8); /* Preto semi-transparente */
            color: white; /* Cor do texto ajustada para contraste */
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        form input[type="date"],
        form input[type="text"],
        form button {
            display: block;
            width: calc(100% - 22px);
            margin: 10px 0;
            padding: 10px;
            border-radius: 4px;
            border: 1px solid #555; /* Borda ajustada para melhor contraste */
            box-sizing: border-box;
        }
        form input[type="text"] {
            color: #333; /* Cor do texto ajustada para contraste */
        }
        form button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        form button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Lucros de DayTrade em Opções Binárias</h2>
    <form action="listarLucros" method="post">
        Data: <input type="date" name="data" required><br>
        Valor: <input type="text" name="valor" placeholder="R$ 0,00" required><br>
        <button type="submit">Salvar Lucro</button>
    </form>
</body>
</html>
