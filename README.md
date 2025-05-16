# dpilaloa.clients.movements

Este proyecto proporciona una API REST para la gestión de **Clientes**, **Cuentas** y **Movimientos** financieros.

---

## 📦 Tecnologías

- Java / Spring Boot
- REST API
- JSON
- Postman (colección de pruebas)
- Puerto por defecto: `8080`

---

## 📁 Endpoints

### 🔹 Clients

- **GET** `/api/v1/clients`  
  Obtiene todos los clientes.

- **GET** `/api/v1/clients/{id}`  
  Obtiene un cliente por su ID.

- **POST** `/api/v1/clients`  
  Crea un nuevo cliente.  
  **Body:**
  ```json
  {
    "name": "Darwin Pilaloa",
    "gender": "M",
    "age": 26,
    "identification": "0705463420",
    "address": "Av. Paez y 8va C Norte",
    "phone": "0969140482",
    "password": "123",
    "state": true
  }
  ```

- **PUT** `/api/v1/clients/{id}`  
  Actualiza un cliente existente.  
  **Body:**
  ```json
  {
    "name": "Darwin Pilaloa Zea",
    "gender": "M",
    "age": 27,
    "identification": "0705463420",
    "address": "Av. Paez y 8va C Norte",
    "phone": "0969140482",
    "password": "45678",
    "state": true
  }
  ```

- **DELETE** `/api/v1/clients/{id}`  
  Elimina un cliente por ID.

---

### 🔹 Accounts

- **GET** `/api/v1/accounts`  
  Lista todas las cuentas.

- **GET** `/api/v1/accounts/{number}`  
  Consulta una cuenta por su número.

- **POST** `/api/v1/accounts`  
  Crea una nueva cuenta.  
  **Body:**
  ```json
  {
    "type": "Ahorro",
    "balance": 10,
    "state": true,
    "client": "5ec9b012-6e3b-4840-a9ff-234e06c3d98b"
  }
  ```

- **PATCH** `/api/v1/accounts/{number}`  
  Cambia el estado de una cuenta.  
  **Body (ejemplo):**
  ```json
  true
  ```

- **DELETE** `/api/v1/accounts/{number}`  
  Elimina una cuenta por su número.

---

### 🔹 Movements

- **GET** `/api/v1/movements`  
  Lista todos los movimientos.

- **POST** `/api/v1/movements`  
  Crea un nuevo movimiento.  
  **Body:**
  ```json
  {
    "type": "Credito",
    "amount": 100,
    "account": 1
  }
  ```

---

## ▶️ Ejecución

Levanta el proyecto con el siguiente comando:

```bash
./mvnw spring-boot:run
```

O con:

```bash
java -jar target/tu-archivo.jar
```

---

## 🧪 Pruebas con Postman

Puedes importar la colección Postman desde el archivo `dpilaloa.clients.movements.postman_collection.json`.

---

## 📝 Licencia

Este proyecto está bajo licencia MIT. Puedes usarlo libremente para propósitos educativos y comerciales.

---

## 👤 Autor

- **Darwin Pilaloa**
