# 🛒 Shopping Backend

Este é o backend do sistema de compras online desenvolvido com **Java**, **Spring Boot** e **SQL Server**, responsável por gerenciar produtos, pedidos e lógica de negócio como controle de estoque e checkout.

## 📦 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **SQL Server**
- **Maven**
- **Lombok**

## 📁 Estrutura do Projeto

src/ ├── main/ │ ├── java/ │ │ └── com/seu_pacote/ │ │ ├── controller/ │ │ ├── dto/ │ │ ├── model/ │ │ ├── repository/ │ │ ├── service/ │ │ └── ShoppingApplication.java │ └── resources/ │ ├── application.properties │ └── data.sql (opcional) | config

## ⚙️ Configuração

### Banco de Dados

Configure sua `application.properties`:

``properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=shopping
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Certifique-se de que o SQL Server esteja rodando e acessível.

Executar o projeto
bash
./mvnw spring-boot:run
Ou via sua IDE preferida.


Importação automática de produtos
Ao iniciar a aplicação, o sistema faz uma requisição para a API pública da FakeStore e importa os produtos automaticamente para o banco de dados. Essa ação é feita uma única vez durante a inicialização (startup) e persiste os dados no banco configurado.

CORS
O CORS está configurado para permitir requisições do frontend rodando localmente na porta 5173:

java
Copiar
Editar
@CrossOrigin(origins = "http://localhost:5173")
Isso permite integração com o frontend React/Vite durante o desenvolvimento.

🛠️ Funcionalidades
🔍 Listagem de produtos

🛒 Adição de produtos ao carrinho

✅ Finalização de compra (checkout)

📦 Controle de estoque (diminui após compra)

📧 Simulação de envio de pedido (payload com dados do cliente)

📬 Endpoints principais
Produtos
Método	Rota	Descrição
GET	/products	Lista todos os produtos
Pedidos (Checkout)
Método	Rota	Descrição
POST	/orders	Cria um novo pedido e atualiza o estoque
Payload de exemplo para /orders:

{
  "customerName": "João",
  "email": "joao@email.com",
  "address": "Rua Exemplo, 123",
  "paymentMethod": "PIX",
  "items": [
    { "productId": 1, "quantity": 2 },
    { "productId": 3, "quantity": 1 }
  ]
}

✅ Testes
Você pode testar os endpoints usando ferramentas como Postman ou Insomnia.



