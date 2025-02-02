# Support Service - API

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/FrankDestro/SupportService-v3.0?tab=MIT-1-ov-file)

## 📌 Sobre o Projeto

O **Support Service** é um sistema robusto para **gestão de chamados** e **suporte ao cliente**, projetado para otimizar a resolução de tickets desde a abertura até o encerramentO, 
obs. será implementado outros módulos para gestão de ITSM, mas em primeiro momento o foco será Gerenciamento de tickets.

## 🚀 Tecnologias Utilizadas

### 🔹 Backend
- **Java 21**
- **Spring Boot 3.3.0**
- **JPA / Hibernate**
- **Maven**
- **OAuth 2.0**
- **Swagger (OpenAPI)**
- **PostgreSQL 16 (Docker)**
- **Pgadmin 4 SGBD (Docker)**
- **Prometheus Observability (Docer)**
- **Grafana Observability (Docker)**
- **Flyway DataBase Migration (Docker)**

### 🔹 Infraestrutura
- **Docker 24.0** (para conteinerização da API e banco de dados)
- **Podman Desktop gerenciamento visual dos container docker** 
- **Fedora 41** 

## 🎨 Modelo Conceitual

![Modelo Conceitual](https://github.com/FrankDestro/Imagens-Readme/blob/main/modeloconceitualk.png)

---

## 📂 Estrutura do Projeto

```
back/
├── docker/
│   ├── data/
│   │   ├── pgadmin/
│   │   ├── postgresql/
│   │       ├── data/
│   ├── observability/
│   │   ├── prometheus/
│   │       └── prometheus.yml
│   └── docker-compose.yml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │       ├── dev/
│   │   │           ├── ServiceHelp/
│   │   │               ├── config/
│   │   │               │   ├── AppConfig.java
│   │   │               │   ├── JpaConfig.java
│   │   │               │   └── SwaggerConfig.java
│   │   │               ├── constants/
│   │   │               │   └── TicketHistoryConstants.java
│   │   │               ├── controllers/
│   │   │               │   ├── exception/
│   │   │               │   │   ├── FieldMessage.java
│   │   │               │   │   ├── ResourceExceptionHandler.java
│   │   │               │   │   ├── StandardError.java
│   │   │               │   │   └── ValidationError.java
│   │   │               │   ├── AttachmentController.java
│   │   │               │   ├── CategoryTicketController.java
│   │   │               │   ├── DepartmentController.java
│   │   │               │   ├── KnowErrorController.java
│   │   │               │   ├── RoleController.java
│   │   │               │   ├── SLAController.java
│   │   │               │   ├── SolvingAreaController.java
│   │   │               │   ├── TicketController.java
│   │   │               │   ├── TicketHistoryController.java
│   │   │               │   ├── TypeRequestController.java
│   │   │               │   └── UserController.java
│   │   │               ├── enums/
│   │   │               │   ├── FileType.java
│   │   │               │   ├── NoteType.java
│   │   │               │   ├── StatusKnowError.java
│   │   │               │   ├── StatusTicket.java
│   │   │               │   └── StatusUser.java
│   │   │               ├── mappers/
│   │   │               │   ├── AttachmentMapper.java
│   │   │               │   ├── CategoryTicketMapper.java
│   │   │               │   ├── DepartmentMapper.java
│   │   │               │   ├── KnowErrorMapper.java
│   │   │               │   ├── RoleMapper.java
│   │   │               │   ├── SLAMapper.java
│   │   │               │   ├── SolvingAreaMapper.java
│   │   │               │   ├── TicketHistoryMapper.java
│   │   │               │   ├── TicketMapper.java
│   │   │               │   ├── TypeRequestMapper.java
│   │   │               │   └── UserMapper.java
│   │   │               ├── models/
│   │   │               │   ├── dto/
│   │   │               │   │   ├── input/
│   │   │               │   │   ├── output/
│   │   │               │   │   ├── shared/
│   │   │               │   │   ├── AttachmentDTO.java
│   │   │               │   │   ├── AttachmentExtendedDTO.java
│   │   │               │   │   ├── AttachmentSimpleDTO.java
│   │   │               │   │   ├── CategoryTicketDTO.java
│   │   │               │   │   ├── CustomErrorDTO.java
│   │   │               │   │   ├── DepartmentDTO.java
│   │   │               │   │   ├── KnowErrorDTO.java
│   │   │               │   │   ├── NewPasswordDTO.java
│   │   │               │   │   ├── RoleDTO.java
│   │   │               │   │   ├── SLADTO.java
│   │   │               │   │   ├── SolvingAreaDTO.java
│   │   │               │   │   ├── TicketDTO.java
│   │   │               │   │   ├── TicketHistoryDTO.java
│   │   │               │   │   ├── TicketSimpleDTO.java
│   │   │               │   │   ├── TicketUpdateDTO.java
│   │   │               │   │   ├── TypeRequestDTO.java
│   │   │               │   │   ├── UserDTO.java
│   │   │               │   │   ├── UserInsertDTO.java
│   │   │               │   │   ├── UserSimpleDTO.java
│   │   │               │   │   └── UserUpdateDTO.java
│   │   │               │   ├── entities/
│   │   │               │       ├── Attachment.java
│   │   │               │       ├── CategoryTicket.java
│   │   │               │       ├── Department.java
│   │   │               │       ├── KnowError.java
│   │   │               │       ├── PasswordRecover.java
│   │   │               │       ├── Role.java
│   │   │               │       ├── SLA.java
│   │   │               │       ├── SolvingArea.java
│   │   │               │       ├── Ticket.java
│   │   │               │       ├── TicketHistory.java
│   │   │               │       ├── TypeRequest.java
│   │   │               │       └── User.java
│   │   │               ├── projections/
│   │   │               │   ├── ActivityPanelPercentOnSlaProjection.java
│   │   │               │   ├── ActivityPanelServiceByDayProjection.java
│   │   │               │   ├── ActivityPanelSummaryPercentTicketsProjection.java
│   │   │               │   ├── ActivityPanelSummaryTicketsByUrgencyProjection.java
│   │   │               │   ├── ActivityPanelSummaryTicketsProjection.java
│   │   │               │   ├── ActivityPanelSummaryTicketsValueByUrgencyProjection.java
│   │   │               │   ├── ActivityPanelTimeMediumResolution.java
│   │   │               │   ├── UserDetailsProjection.java
│   │   │               │   └── activityPanelAverageFirstResponseTimeProjection.java
│   │   │               ├── repository/
│   │   │               │   ├── AttachmentRepository.java
│   │   │               │   ├── CategoryTicketRepository.java
│   │   │               │   ├── DepartmentRepository.java
│   │   │               │   ├── KnowErrorRepository.java
│   │   │               │   ├── PasswordRecoverRepository.java
│   │   │               │   ├── RoleRepository.java
│   │   │               │   ├── SLARepository.java
│   │   │               │   ├── SolvingAreaRepository.java
│   │   │               │   ├── TicketHistoryRepository.java
│   │   │               │   ├── TicketRepository.java
│   │   │               │   ├── TypeRequestRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               ├── security/
│   │   │               │   ├── auth/
│   │   │               │   │   ├── CustomPasswordAuthenticationConverter.java
│   │   │               │   │   ├── CustomPasswordAuthenticationProvider.java
│   │   │               │   │   ├── CustomPasswordAuthenticationToken.java
│   │   │               │   │   └── CustomUserAuthorities.java
│   │   │               │   ├── infra/
│   │   │               │       ├── AuthorizationServerConfig.java
│   │   │               │       └── ResourceServerConfig.java
│   │   │               ├── services/
│   │   │               │   ├── exceptions/
│   │   │               │   │   ├── DatabaseException.java
│   │   │               │   │   ├── PasswordException.java
│   │   │               │   │   ├── ResourceNotFoundException.java
│   │   │               │   │   └── TicketStatusException.java
│   │   │               │   ├── validation/
│   │   │               │   │   ├── UserInsertValid.java
│   │   │               │   │   ├── UserInsertValidator.java
│   │   │               │   │   ├── UserUpdateValid.java
│   │   │               │   │   └── UserUpdateValidator.java
│   │   │               │   ├── AttachmentService.java
│   │   │               │   ├── CategoryTicketService.java
│   │   │               │   ├── DepartmentService.java
│   │   │               │   ├── KnowErrorService.java
│   │   │               │   ├── RoleService.java
│   │   │               │   ├── SLAService.java
│   │   │               │   ├── SolvingAreaService.java
│   │   │               │   ├── TicketHistoryService.java
│   │   │               │   ├── TicketService.java
│   │   │               │   ├── TypeRequestService.java
│   │   │               │   └── UserService.java
│   │   │               ├── strategy/
│   │   │               │   ├── factory/
│   │   │               │   │   └── StatusTicketStrategyFactory.java
│   │   │               │   ├── strategyimpl/
│   │   │               │   │   ├── CanceledStatusStrategy.java
│   │   │               │   │   ├── FinishedStatusStrategy.java
│   │   │               │   │   ├── FrozenStatusStrategy.java
│   │   │               │   │   └── InProgressStatusStrategy.java
│   │   │               │   └── StatusTicketStrategy.java
│   │   │               ├── utils/
│   │   │               │   ├── DateTimeUtil.java
│   │   │               │   └── ResourceUtil.java
│   │   │               └── ServiceHelpApplication.java
│   │   ├── resources/
│   │       ├── anotations.txt
│   │       ├── application-dev.properties
│   │       ├── application-prod.properties
│   │       ├── application-test.properties
│   │       ├── application.properties
│   │       ├── banner.txt
│   │       └── import.sql
│   ├── test/
│       ├── java/
│           ├── com/
│           │   ├── dev/
│           │       ├── ServiceHelp/
│           │           ├── factory/
│           │           │   ├── ActivityPanelSummaryTicketsProjectionFactory.java
│           │           │   ├── AttachmentFactory.java
│           │           │   ├── CategoryTicketFactory.java
│           │           │   ├── DepartmentFactory.java
│           │           │   ├── KnowErrorFactory.java
│           │           │   ├── RoleFactory.java
│           │           │   ├── SLAFactory.java
│           │           │   ├── SolvingAreaFactory.java
│           │           │   ├── TicketFactory.java
│           │           │   ├── TicketHistoryFactory.java
│           │           │   ├── TypeRequestFactory.java
│           │           │   └── UserFactory.java
│           │           ├── services/
│           │           │   ├── AttachmentServiceTest.java
│           │           │   ├── DepartmentServiceTest.java
│           │           │   ├── KnowErrorServiceTest.java
│           │           │   ├── RoleServiceTest.java
│           │           │   ├── SLAServiceTest.java
│           │           │   ├── SolvingAreaServiceTest.java
│           │           │   ├── TicketHistoryServiceTest.java
│           │           │   └── TypeRequestServiceTest.java
│           │           └── PostgresContainerTest.java
│           └── ServiceHelpApplicationTests.java
├── Dockerfile
├── create5.sql
├── mvnw
├── mvnw.cmd
└── pom.xml

```

---

## ⚙️ Como Executar o Projeto

### 🔹 Pré-requisitos
- **Java 21**
- **Maven**
- **Docker**
- **PostgreSQL (ou Docker Compose para banco de dados)**

### 🔹 Passos para Execução

```bash
# Clonar o repositório
git clone https://github.com/FrankDestro/SupportService-v3.0.git

# Entrar na pasta do backend
cd SupportService-backend

# Construir o projeto com Maven
mvn clean install

# Subir os containers do PostgreSQL com Docker Compose
docker-compose up -d

# Executar a API
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

---

## 📖 Documentação da API

A documentação da API está disponível no Swagger:

📌 **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

📌 **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 📜 Licença

Este projeto está licenciado sob a licença MIT. Para mais detalhes, acesse o arquivo [`LICENSE`](https://github.com/FrankDestro/SupportService-v3.0/blob/main/LICENSE).

---

## 👨‍💻 Autor

Franklyn Destro Damaceno  
🔗 [LinkedIn](https://www.linkedin.com/in/franklyn-damaceno-441baa143/)

