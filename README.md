<div align="center">

# 🌸 DuoBloom

### *A cozy space where shared finances and dreams bloom together*

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://docs.docker.com/compose/)
[![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Lombok](https://img.shields.io/badge/Lombok-Enabled-BC4521?style=for-the-badge)](https://projectlombok.org/)

[![License](https://img.shields.io/badge/license-MIT-pink?style=flat-square)](LICENSE)
[![Status](https://img.shields.io/badge/status-in_development-coral?style=flat-square)]()
[![Branch](https://img.shields.io/badge/branch-release-blueviolet?style=flat-square)]()

---

> *Track expenses, manage a joint wishlist, and save for big dreams together.*  
> *All wrapped in a soft coral and cream aesthetic that turns routine into a delightful experience.*  
> *Grow your future, side by side.*

</div>

---

## ✨ What is DuoBloom?

**DuoBloom** is a couples-first personal finance app designed to make managing money together feel warm, intentional, and even fun. Instead of juggling spreadsheets or fighting over who paid what, DuoBloom gives you and your partner a single shared space to:

- 💸 **Track shared expenses** — categorized, dated, and tied to your group
- 🛒 **Maintain a joint wishlist** — items you both want, with status tracking
- 🎯 **Set and chase saving goals** — visualize progress toward your dreams
- 👥 **Manage your couple group** — invite codes, shared access, role-based control

---

## 🗂️ Project Structure

```
DuoBloom/
├── backend/                        # Spring Boot REST API
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/org/example/duobloom/
│   │   │   │   ├── DuoBloomApplication.java
│   │   │   │   ├── entity/         # ✅ JPA domain entities
│   │   │   │   └── repository/     # ✅ Spring Data repositories
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── pom.xml
└── docker/
    └── docker-compose.yml          # PostgreSQL container setup
```

---

## 🛠️ Technology Stack

| Layer            | Technology                          | Version   | Purpose                                     |
|------------------|--------------------------------------|-----------|---------------------------------------------|
| **Language**     | Java                                 | 17        | Core backend language                       |
| **Framework**    | Spring Boot                          | 4.0.3     | Application framework & auto-configuration  |
| **Web**          | Spring MVC (WebMVC)                  | —         | REST API layer                              |
| **Persistence**  | Spring Data JPA + Hibernate          | —         | ORM & database abstraction                  |
| **Validation**   | Spring Boot Validation (Jakarta)     | —         | Bean validation annotations                 |
| **Database**     | PostgreSQL                           | 15        | Primary relational database                 |
| **Container**    | Docker + Docker Compose              | 3.8       | Local database environment                  |
| **Build Tool**   | Apache Maven                         | Wrapper   | Dependency management & builds              |
| **Boilerplate**  | Lombok                               | —         | Getter/Setter/Constructor generation        |
| **Config**       | dotenv-java (`cdimascio`)            | 3.0.0     | `.env` file loading in local dev            |
| **Dev Tools**    | Spring Boot DevTools                 | —         | Live reload during development              |

---

## 🗃️ Data Model

DuoBloom's domain is built around the concept of a **Group** — a shared space between two (or more) users.

```
┌─────────────────────────────────────────────────────────────────────────┐
│                          DuoBloom Domain Model                          │
└─────────────────────────────────────────────────────────────────────────┘

        ┌──────────┐          ┌────────────┐          ┌──────────┐
        │   User   │──────────│ UserGroup  │──────────│  Group   │
        │          │  1    *  │            │  *    1  │          │
        │ id       │          │ id         │          │ id       │
        │ email    │          │ user_id    │          │ name     │
        │ username │          │ group_id   │          │ link_url │
        │ pwd_hash │          │ role       │          │invite_cod│
        │created_at│          └────────────┘          │created_at│
        └──────────┘                                   └──────────┘
                                                            │
                    ┌───────────────────────────────────────┤
                    │                   │                   │
             ┌──────▼──────┐   ┌────────▼──────┐   ┌───────▼──────┐
             │   Expense   │   │  SavingGoal   │   │   WishItem   │
             │             │   │               │   │              │
             │ id          │   │ id            │   │ id           │
             │ amount      │   │ title         │   │ title        │
             │ description │   │ target_amount │   │ description  │
             │ expense_date│   │ current_amount│   │ link_url     │
             │ group_id    │   │ deadline      │   │ price        │
             │ user_id     │   │ group_id      │   │ status       │
             │ category_id │   └───────────────┘   │ group_id     │
             └──────┬──────┘                        │ creator_id   │
                    │                               └──────────────┘
             ┌──────▼──────┐
             │  Category   │          WishStatus: AVAILABLE | RESERVED | BOUGHT
             │             │          Role:       ADMIN     | MEMBER
             │ id          │
             │ name        │
             │ icon        │
             └─────────────┘
```

### Entity Summary

| Entity       | Table          | Key Fields                                                         |
|--------------|----------------|--------------------------------------------------------------------|
| `User`       | `users`        | `id`, `email` (unique), `username`, `password_hash`, `created_at` |
| `Group`      | `groups`       | `id`, `name`, `invite_code` (unique), `link_url`, `created_at`    |
| `UserGroup`  | `user_groups`  | `id`, `user_id`, `group_id`, `role` (ADMIN/MEMBER)                |
| `Expense`    | `expenses`     | `id`, `amount`, `description`, `expense_date`, `group`, `user`, `category` |
| `Category`   | `categories`   | `id`, `name`, `icon`                                               |
| `SavingGoal` | `saving_goals` | `id`, `title`, `target_amount`, `current_amount`, `deadline`, `group` |
| `WishItem`   | `wish_items`   | `id`, `title`, `description`, `price`, `link_url`, `status`, `group`, `creator` |

---

## 🏗️ Architecture Overview

```
┌─────────────────────────────────────────────────┐
│                  CLIENT (Future)                 │
│            Web / Mobile Frontend                 │
└────────────────────┬────────────────────────────┘
                     │ HTTP/REST
┌────────────────────▼────────────────────────────┐
│              Spring Boot Backend                 │
│  ┌──────────────────────────────────────────┐   │
│  │         Controller Layer  (TODO)         │   │
│  └────────────────────┬─────────────────────┘   │
│  ┌────────────────────▼─────────────────────┐   │
│  │          Service Layer  (TODO)           │   │
│  └────────────────────┬─────────────────────┘   │
│  ┌────────────────────▼─────────────────────┐   │
│  │       Repository Layer  (DONE ✅)        │   │
│  └────────────────────┬─────────────────────┘   │
│  ┌────────────────────▼─────────────────────┐   │
│  │         Entity / Domain  (DONE ✅)       │   │
│  └────────────────────┬─────────────────────┘   │
└───────────────────────┼─────────────────────────┘
                        │ JDBC / JPA
┌───────────────────────▼─────────────────────────┐
│          PostgreSQL 15 (Docker)                  │
│               duobloom database                  │
│          port 6969 → 5432 (internal)             │
└─────────────────────────────────────────────────┘
```

---

## 🚀 Getting Started

### Prerequisites

- **Java 17+** — [Download](https://adoptium.net/)
- **Docker + Docker Compose** — [Download](https://www.docker.com/products/docker-desktop/)
- **Maven** (or use the included `./mvnw` wrapper)

### 1. Clone the repository

```bash
git clone https://github.com/golembio/DuoBloom.git
cd DuoBloom
```

### 2. Configure environment variables

Create a `.env` file in the `backend/` directory:

```env
DB_URL=jdbc:postgresql://localhost:6969/duobloom
DB_USERNAME=your_username
DB_PASSWORD=your_password
```

### 3. Start the database

```bash
cd docker
docker compose up -d
```

This spins up a PostgreSQL 15 container named `duobloom-db` on port **6969**.

### 4. Run the backend

```bash
cd backend
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## 📦 Repository Layer

All repositories extend `JpaRepository<Entity, Long>` and provide full CRUD + pagination out of the box:

| Repository               | Entity        |
|--------------------------|---------------|
| `UserRepository`         | `User`        |
| `GroupRepository`        | `Group`       |
| `UserGroupRepository`    | `UserGroup`   |
| `ExpenseRepository`      | `Expense`     |
| `CategoryRepository`     | `Category`    |
| `SavingGoalRepository`   | `SavingGoal`  |
| `WishItemRepository`     | `WishItem`    |

---

## 🗓️ Development Progress

```
Phase 1 — Foundation         ████████████████████  100%
Phase 2 — Data Access        ████████████████████  100%
Phase 3 — Business Logic     ░░░░░░░░░░░░░░░░░░░░    0%
Phase 4 — REST API           ░░░░░░░░░░░░░░░░░░░░    0%
Phase 5 — Security           ░░░░░░░░░░░░░░░░░░░░    0%
Phase 6 — Frontend           ░░░░░░░░░░░░░░░░░░░░    0%
```

### Completed

- [x] Project scaffolding with Spring Boot 4
- [x] Docker Compose setup for PostgreSQL 15
- [x] Environment variable configuration via `.env` + dotenv-java
- [x] Full JPA entity layer with Javadoc
- [x] Spring Data JPA repositories for all entities
- [x] Hibernate DDL auto-update configured
- [x] Checkstyle integration

### In Progress / Upcoming

- [ ] Service layer (business logic)
- [ ] REST controllers (CRUD endpoints for all resources)
- [ ] DTOs + request/response mapping
- [ ] Spring Security + JWT authentication
- [ ] Group invite flow (invite code generation)
- [ ] Wish item reservation & purchase flow
- [ ] Saving goal progress tracking
- [ ] Frontend (TBD — React / Vue / Mobile)
- [ ] Unit & integration tests
- [ ] API documentation (Swagger / OpenAPI)

---

## 🌿 Git Workflow

| Branch    | Purpose                                   |
|-----------|-------------------------------------------|
| `main`    | Stable, production-ready code             |
| `release` | Current integration branch                |
| `feature/*` | Individual feature branches (e.g. `feature/entities`) |

### Recent Commits

```
51af5ee  Merge pull request #1 from Januzao/feature/entities
61dbc00  Fix .env loading for local development using dotenv-java
a8cbffe  Add entity package with JPA entities and Javadoc comments
ecaca9e  Setup project with env variables
a228a61  Initial commit
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes following the existing commit style
4. Open a Pull Request against `main`

---

## 👥 Team

| Contributor | GitHub                                        |
|-------------|-----------------------------------------------|
| golembio    | [@golembio](https://github.com/golembio)     |
| Januzao     | [@Januzao](https://github.com/Januzao)       |

---

<div align="center">

*Made with 🌸 and a lot of care for couples who dream together.*

</div>
