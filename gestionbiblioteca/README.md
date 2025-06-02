# 📚 Library Management System

## 🎯 Descripción

Library Management System es una aplicación completa para la gestión de bibliotecas 
que permite administrar autores, libros y categorías. 
Incluye operaciones CRUD completas y consultas personalizadas para facilitar 
búsquedas avanzadas y reportes.

El sistema está pensado para bibliotecas medianas que necesitan digitalizar su inventario 
y agilizar la gestión diaria.

---

## ⚡ Características

- ✅ CRUD completo para:
  - Autores (Authors)
  - Libros (Books)
  - Categorías (Categories)
- ✅ Consultas personalizadas (búsqueda por autor, categoría, año de publicación, etc.)
- ✅ API REST con endpoints claros
- ✅ Validación de datos y manejo de errores
- ✅ Arquitectura limpia siguiendo buenas prácticas de backend
- ✅ Base de datos flexible (MySQL)

---

## 🛠️ Stack Tecnológico

- **Backend**: Java 17, Spring Boot 3.5
- **Base de Datos**: MySQL
- **Frontend**: none
- **Testing**: none
- **DevOps**: none

```bash
# Clona el repositorio
git clone https://github.com/tu-usuario/library-management.git
cd library-management

# Compila el proyecto
./mvnw clean install

# Corre la aplicación
./mvnw spring-boot:run
