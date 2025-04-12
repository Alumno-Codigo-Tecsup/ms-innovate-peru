# InnovationProjectsPeru - API de Proyectos de Innovación

Este proyecto es una API REST que gestiona información sobre proyectos de innovación financiados por el estado peruano. La aplicación está construida con Spring Boot y utiliza PostgreSQL como base de datos.

## Requisitos Previos

- Java 21
- PostgreSQL 12 o superior
- Maven 3.6 o superior
- Docker y Docker Compose (para despliegue con contenedores)

## Configuración del Entorno

### 1. Configuración de PostgreSQL

1. **Instalar PostgreSQL**
   ```bash
   # Para macOS usando Homebrew
   brew install postgresql@15
   
   # Para Ubuntu/Debian
   sudo apt-get update
   sudo apt-get install postgresql-15
   ```

2. **Iniciar el servicio de PostgreSQL**
   ```bash
   # Para macOS
   brew services start postgresql@15
   
   # Para Ubuntu/Debian
   sudo systemctl start postgresql
   ```

3. **Crear la Base de Datos**
   ```bash
   # Acceder a PostgreSQL
   psql -U postgres
   
   # Crear la base de datos
   CREATE DATABASE proyectos_proinnovate_peru;
   
   # Verificar la creación
   \l
   
   # Salir de psql
   \q
   ```

### 2. Configuración del Archivo .env

1. **Crear el archivo .env en la raíz del proyecto**
   ```bash
   touch .env
   ```

2. **Agregar las siguientes variables al archivo .env**
   ```properties
   # Configuración de la Base de Datos
   POSTGRES_DB=proyectos_proinnovate_peru
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=postgres
   DB_HOST=db
   DB_PORT=5432

   # Configuración de la Aplicación
   SERVER_PORT=8080
   SPRING_PROFILES_ACTIVE=prod

   # Configuración de JVM
   JAVA_OPTS=-Xmx512m -Xms256m

   # Variables de Seguridad
   JWT_SECRET=tu_clave_secreta_muy_segura_aqui
   JWT_EXPIRATION=86400000
   ```

   > **IMPORTANTE**: 
   > - Cambia los valores por defecto, especialmente las credenciales y claves secretas en producción
   > - No subas el archivo .env al repositorio
   > - Para desarrollo local, cambia `DB_HOST=db` a `DB_HOST=localhost`

### 3. Verificación de la Configuración

1. **Verificar la conexión a PostgreSQL**
   ```bash
   psql -U postgres -d proyectos_proinnovate_peru -c "\dt"
   ```

2. **Verificar las variables de entorno**
   ```bash
   # Verifica que el archivo .env existe
   cat .env
   ```

## Instalación y Ejecución

### Ejecución Local

1. **Clonar el Repositorio**
   ```bash
   git clone [https://github.com/Alumno-Codigo-Tecsup/ms-innovate-peru.git]
   cd proinnovatePeru
   ```

2. **Compilar el Proyecto**
   ```bash
   ./mvnw clean install
   ```

3. **Ejecutar la Aplicación**
   ```bash
   ./mvnw spring-boot:run
   ```

### Ejecución con Docker

1. **Construir y Ejecutar con Docker Compose**
   ```bash
   # Detener contenedores previos si existen
   docker-compose down -v
   
   # Construir las imágenes
   docker-compose build --no-cache
   
   # Iniciar los servicios
   docker-compose up
   ```

2. **Verificar el Estado de los Contenedores**
   ```bash
   docker-compose ps
   ```

3. **Ver Logs de la Aplicación**
   ```bash
   docker-compose logs -f app
   ```

La aplicación estará disponible en `http://localhost:8080`

## Solución de Problemas Comunes

1. **Error de conexión a la base de datos**
   - Verificar que PostgreSQL está corriendo
   - Confirmar las credenciales en el archivo .env
   - Para desarrollo local, asegurarse que DB_HOST=localhost
   - Para Docker, asegurarse que DB_HOST=db

2. **Error de puertos en uso**
   - Verificar que los puertos 8080 y 5432 estén libres
   - Cambiar SERVER_PORT o DB_PORT en el .env si es necesario

3. **Error de permisos en PostgreSQL**
   ```bash
   # Acceder a PostgreSQL y otorgar permisos
   psql -U postgres
   ALTER USER postgres WITH PASSWORD 'postgres';
   GRANT ALL PRIVILEGES ON DATABASE proyectos_proinnovate_peru TO postgres;
   ```

## Endpoints Disponibles

### 1. Obtener Todos los Proyectos (Paginado)
- **URL**: `/api/projects`
- **Método**: GET
- **Parámetros de Query**:
  - `page`: número de página (0 por defecto)
  - `size`: tamaño de página (20 por defecto)
- **Ejemplo**: `GET http://localhost:8080/api/projects?page=0&size=10`

### 2. Obtener Proyecto por ID
- **URL**: `/api/projects/{id}`
- **Método**: GET
- **Ejemplo**: `GET http://localhost:8080/api/projects/1`

### 3. Buscar Proyectos por Fondo
- **URL**: `/api/projects/search/fondo/{fondo}`
- **Método**: GET
- **Ejemplo**: `GET http://localhost:8080/api/projects/search/fondo/PNIPA`

### 4. Buscar Proyectos por Departamento
- **URL**: `/api/projects/search/departamento/{departamento}`
- **Método**: GET
- **Ejemplo**: `GET http://localhost:8080/api/projects/search/departamento/LIMA`

### 5. Buscar Proyectos por Año
- **URL**: `/api/projects/search/anio/{anio}`
- **Método**: GET
- **Ejemplo**: `GET http://localhost:8080/api/projects/search/anio/2022`

### 6. Buscar Proyectos por Título
- **URL**: `/api/projects/search/titulo/{titulo}`
- **Método**: GET
- **Ejemplo**: `GET http://localhost:8080/api/projects/search/titulo/innovacion`

## Ejemplos de cURLs

### Obtener Todos los Proyectos (Paginado)
```bash
curl -X GET 'http://localhost:8080/api/projects?page=0&size=10' \
-H 'Accept: application/json'
```

### Obtener Proyecto por ID
```bash
curl -X GET 'http://localhost:8080/api/projects/1' \
-H 'Accept: application/json'
```

### Buscar Proyectos por Fondo
```bash
curl -X GET 'http://localhost:8080/api/projects/search/fondo/PNIPA' \
-H 'Accept: application/json'
```

### Buscar Proyectos por Departamento
```bash
curl -X GET 'http://localhost:8080/api/projects/search/departamento/LIMA' \
-H 'Accept: application/json'
```

### Buscar Proyectos por Año
```bash
curl -X GET 'http://localhost:8080/api/projects/search/anio/2022' \
-H 'Accept: application/json'
```

### Buscar Proyectos por Título
```bash
curl -X GET 'http://localhost:8080/api/projects/search/titulo/innovacion' \
-H 'Accept: application/json'
```

### Verificar Estado de la Aplicación
```bash
curl -X GET 'http://localhost:8080/health' \
-H 'Accept: application/json'
```

## Estructura de Datos

Los proyectos tienen la siguiente estructura:

```json
{
  "id": 1,
  "fondo": "string",
  "concurso": "string",
  "anio": 2023,
  "contrato": "string",
  "titulo": "string",
  "fechaInicio": "2023-01-01",
  "fechaFin": "2023-12-31",
  "nombreSolicitante": "string",
  "distrito": "string",
  "provincia": "string",
  "departamento": "string",
  "ubigeo": "string",
  "montoRNR": 0.0,
  "montoFinanciero": 0.0,
  "montoNoFinanciero": 0.0,
  "moneda": "string",
  "fechaCorte": "2023-12-31"
}
```

## Carga Inicial de Datos

La aplicación cargará automáticamente los datos del archivo CSV ubicado en `src/main/resources/data/projects.csv` cuando se inicie por primera vez. Este proceso solo se ejecutará si la base de datos está vacía.

## Monitoreo de la Aplicación

Para verificar el estado de la aplicación, puede utilizar el endpoint de salud:
- **URL**: `/health`
- **Método**: GET
- **Ejemplo**: `GET http://localhost:8080/health`

## Despliegue con Docker

### Requisitos
- Docker
- Docker Compose

### Pasos para el Despliegue

1. **Construir y Ejecutar con Docker Compose**
   ```bash
   docker-compose up --build
   ```
   Este comando construirá la imagen de la aplicación y iniciará tanto la aplicación como la base de datos PostgreSQL.

2. **Detener los Contenedores**
   ```bash
   docker-compose down
   ```

3. **Ver Logs de la Aplicación**
   ```bash
   docker-compose logs -f app
   ```

### Despliegue en la Nube

Para desplegar en diferentes proveedores de nube:

#### AWS Elastic Beanstalk
1. Instalar AWS CLI y configurar credenciales
2. Crear archivo `Dockerrun.aws.json`
3. Crear nueva aplicación en Elastic Beanstalk
4. Subir la aplicación:
   ```bash
   eb init
   eb create
   eb deploy
   ```

#### Google Cloud Run
1. Instalar Google Cloud CLI
2. Configurar proyecto:
   ```bash
   gcloud init
   ```
3. Construir y subir la imagen:
   ```bash
   gcloud builds submit --tag gcr.io/[PROJECT_ID]/proinnovate-api
   ```
4. Desplegar en Cloud Run:
   ```bash
   gcloud run deploy proinnovate-api --image gcr.io/[PROJECT_ID]/proinnovate-api
   ```

#### Azure Container Instances
1. Instalar Azure CLI
2. Crear registro de contenedores:
   ```bash
   az acr create --name proinnovateregistry --resource-group [RESOURCE_GROUP] --sku Basic
   ```
3. Construir y subir imagen:
   ```bash
   az acr build --registry proinnovateregistry --image proinnovate-api .
   ```
4. Desplegar contenedor:
   ```bash
   az container create --name proinnovate-api --image proinnovateregistry.azurecr.io/proinnovate-api:latest
   ``` 