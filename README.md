## ExpenseManagement 📊💸

Bem-vindo ao ExpenseManagement! Este é um aplicativo em desenvolvimento para gestão de gastos pessoais. 
Com o ExpenseManagement, você poderá acompanhar suas despesas de forma eficiente, mantendo o controle total sobre suas finanças.

### Principais Recursos

- **Registro de Despesas:** Registre suas despesas diárias de maneira organizada e eficiente.
- **Categorias Personalizadas:** Crie categorias de gastos personalizadas para uma organização mais detalhada.
- **Visualizações Gráficas:** Analise seus padrões de gastos por meio de gráficos intuitivos e informativos.
- **Notificações Personalizadas:** Receba lembretes e alertas para ajudá-lo a manter suas metas financeiras.

### Requisitos

- **Cadastro de Despesas:** Permitir aos usuários registrarem suas despesas com detalhes.
- **Personalização de Categorias:** Os usuários devem poder criar e gerenciar suas próprias categorias de gastos.
- **Visualizações Gráficas:** Implementar gráficos para representar visualmente os padrões de gastos.
- **Notificações:** Configurar sistema de notificações para manter os usuários atualizados sobre seus gastos.

### Pré-requisitos

- Java 17 ☕
- Spring Boot 3.3.2 🍃
- Banco de Dados (H2, MySQL, etc.) 
- Maven ou Gradle

### Configuração do Projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/GabrielBressi/expense-management
   
Importe o projeto em sua IDE favorita *(recomendo o Intellij Idea)*.

Certifique-se de que as dependências do Maven/Gradle estejam atualizadas.

Configure o banco de dados conforme necessário (consulte application.properties para configurações de banco de dados).

API Endpoints:
```bash
GET /users - Retrieve a list of all users. (all authenticated users)
POST /auth/register - Register a new user into the App
POST /auth/login - Login into the App
GET /expense - Retrieve a list of all expenses (ADMIN authenticated user)
POST /expense - Register a new expense into the App (ADMIN authenticated user)
GET /expense/category - Get all expense categories (ADMIN authenticated user)
POST /expense/category - Resgiter a new expense category into the App (ADMIN authenticated user)
```

Contribuindo
Contribuições são bem-vindas! Sinta-se à vontade para abrir pull requests para melhorias, correções de bugs ou novos recursos.
