<p align="center" width="100%">
    <img width="30%" src="images/picpay.jpg"> 
</p>

# Desafio Backend do PicPay

Este é um projeto Kotlin desenvolvido com Spring Boot, implementando um sistema de pagamentos similar ao PicPay.

## 🛠 Tecnologias

- Kotlin 1.9.25
- Java 21
- Spring Boot 3.4.1
- Spring Data JPA
- Spring Cloud OpenFeign
- MariaDB
- Gradle

## 📋 Pré-requisitos

- JDK 21
- Gradle 8.5 ou superior
- MariaDB

## 🚀 Começando

### 1. Clone o repositório
```bash
git clone https://github.com/jobsonmarinho/desafio_backend_picpay.git
cd desafio_backend_picpay
```

### 2. Configure o banco de dados
Certifique-se de ter o MariaDB instalado e rodando.
Configure as credenciais do banco de dados no arquivo `application.properties`.

### 3. Execute o projeto
```bash
./gradlew bootRun
```

O servidor estará disponível em `http://localhost:8080`

## 🏗 Estrutura do Projeto

O projeto segue uma arquitetura limpa e organizada:

```
src/
├── main/
│   ├── kotlin/
│   │   └── net/
│   │       └── hypedmc/
│   │           └── picpay/
│   │               ├── client/        # Clientes HTTP para serviços externos
│   │               ├── config/        # Configurações do Spring e da aplicação
│   │               ├── controller/    # Controladores REST da API
│   │               ├── entity/        # Entidades JPA do banco de dados
│   │               ├── exception/     # Exceções personalizadas
│   │               ├── handler/       # Handlers para tratamento de erros
│   │               ├── repository/    # Repositórios JPA
│   │               └── service/       # Lógica de negócios e serviços
│   └── resources/
│       └── application.properties    # Configurações da aplicação
└── test/
    └── kotlin/                       # Testes unitários e de integração
```

## 🧪 Testes

Para executar os testes:

```bash
./gradlew test
```

## 📦 Build

Para criar um arquivo JAR executável:

```bash
./gradlew build
```

O arquivo JAR será gerado em `build/libs/`.

## 🐋 Configuração do Docker MariaDB

O projeto inclui um arquivo `docker-compose.yml` que configura um container MariaDB pronto para uso. Aqui estão os detalhes:

### Configurações do Banco
- **Porta**: 3306 (mapeada para 3306 do host)
- **Usuário**: admin
- **Senha**: 123
- **Banco de Dados**: db_picpay
- **Senha Root**: 123

### Como Usar

1. Navegue até a pasta docker:
```bash
cd docker
```

2. Inicie o container:
```bash
docker-compose up -d
```

3. O MariaDB estará disponível em:
- Host: localhost
- Porta: 3306

### Persistência de Dados
Os dados do banco são persistidos através de um volume Docker nomeado `db_data`, garantindo que seus dados permaneçam mesmo após reiniciar o container.

> **Nota de Segurança**: As credenciais fornecidas são apenas para ambiente de desenvolvimento. Em produção, use senhas fortes e gerencie as credenciais de forma segura através de variáveis de ambiente ou secrets.
