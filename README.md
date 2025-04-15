# InnovationProjectsPeru - Innovation Projects API

This project is a REST API that manages information about innovation projects funded by the Peruvian government. The application is built with Spring Boot and uses PostgreSQL as its database.

## Project Architecture

```mermaid
graph TD
    Client[Client Applications] -->|HTTP Requests| API[REST API]
    API -->|CRUD Operations| DB[(PostgreSQL Database)]
    
    subgraph Backend Architecture
        API -->|1. Get Projects| Projects[Projects Service]
        API -->|2. Search| Search[Search Service]
        API -->|3. Authentication| Auth[Auth Service]
        Projects --> Repository[Project Repository]
        Search --> Repository
        Repository -->|JPA| DB
    end

    subgraph Data Flow
        CSV[CSV Data] -.->|Initial Load| DB
        Client -->|1. GET /api/projects| API
        Client -->|2. GET /api/projects/{id}| API
        Client -->|3. GET /api/projects/search/*| API
    end

    style Client fill:#f9f,stroke:#333,stroke-width:2px
    style API fill:#bbf,stroke:#333,stroke-width:2px
    style DB fill:#dfd,stroke:#333,stroke-width:2px
```

## Prerequisites

- Java 21
- PostgreSQL 12 or higher
- Maven 3.6 or higher
- Docker and Docker Compose (for container deployment)

## Environment Setup

### 1. PostgreSQL Configuration

1. **Install PostgreSQL**
   ```bash
   # For macOS using Homebrew
   brew install postgresql@15
   
   # For Ubuntu/Debian
   sudo apt-get update
   sudo apt-get install postgresql-15
   ```

2. **Start PostgreSQL Service**
   ```bash
   # For macOS
   brew services start postgresql@15
   
   # For Ubuntu/Debian
   sudo systemctl start postgresql
   ```

3. **Create Database**
   ```bash
   # Access PostgreSQL
   psql -U postgres
   
   # Create database
   CREATE DATABASE proyectos_proinnovate_peru;
   
   # Verify creation
   \l
   
   # Exit psql
   \q
   ```

### 2. .env File Configuration

1. **Create .env file in project root**
   ```bash
   touch .env
   ```

2. **Add the following variables to .env file**
   ```properties
   # Database Configuration
   POSTGRES_DB=proyectos_proinnovate_peru
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=postgres
   DB_HOST=db
   DB_PORT=5432

   # Application Configuration
   SERVER_PORT=8080
   SPRING_PROFILES_ACTIVE=prod

   # JVM Configuration
   JAVA_OPTS=-Xmx512m -Xms256m

   # Security Variables
   JWT_SECRET=your_very_secure_secret_key_here
   JWT_EXPIRATION=86400000
   ```

   > **IMPORTANT**: 
   > - Change default values, especially credentials and secret keys in production
   > - Don't upload the .env file to the repository
   > - For local development, change `DB_HOST=db` to `DB_HOST=localhost`

### 3. Configuration Verification

1. **Verify PostgreSQL Connection**
   ```bash
   psql -U postgres -d proyectos_proinnovate_peru -c "\dt"
   ```

2. **Verify Environment Variables**
   ```bash
   # Verify .env file exists
   cat .env
   ```

## Installation and Execution

### Local Execution

1. **Clone Repository**
   ```bash
   git clone [https://github.com/Alumno-Codigo-Tecsup/ms-innovate-peru.git]
   cd proinnovatePeru
   ```

2. **Build Project**
   ```bash
   ./mvnw clean install
   ```

3. **Run Application**
   ```bash
   ./mvnw spring-boot:run
   ```

### Docker Execution

1. **Build and Run with Docker Compose**
   ```bash
   # Stop previous containers if they exist
   docker-compose down -v
   
   # Build images
   docker-compose build --no-cache
   
   # Start services
   docker-compose up
   ```

2. **Check Container Status**
   ```bash
   docker-compose ps
   ```

3. **View Application Logs**
   ```bash
   docker-compose logs -f app
   ```

The application will be available at `http://localhost:8080`

## Common Troubleshooting

1. **Database Connection Error**
   - Verify PostgreSQL is running
   - Confirm credentials in .env file
   - For local development, ensure DB_HOST=localhost
   - For Docker, ensure DB_HOST=db

2. **Port in Use Error**
   - Verify ports 8080 and 5432 are free
   - Change SERVER_PORT or DB_PORT in .env if needed

3. **PostgreSQL Permission Error**
   ```bash
   # Access PostgreSQL and grant permissions
   psql -U postgres
   ALTER USER postgres WITH PASSWORD 'postgres';
   GRANT ALL PRIVILEGES ON DATABASE proyectos_proinnovate_peru TO postgres;
   ```

## Available Endpoints

### 1. Get All Projects (Paginated)
- **URL**: `/api/projects`
- **Method**: GET
- **Query Parameters**:
  - `page`: page number (0 by default)
  - `size`: page size (20 by default)
- **Example**: `GET http://localhost:8080/api/projects?page=0&size=10`

### 2. Get Project by ID
- **URL**: `/api/projects/{id}`
- **Method**: GET
- **Example**: `GET http://localhost:8080/api/projects/1`

### 3. Search Projects by Fund
- **URL**: `/api/projects/search/fondo/{fondo}`
- **Method**: GET
- **Example**: `GET http://localhost:8080/api/projects/search/fondo/PNIPA`

### 4. Search Projects by Department
- **URL**: `/api/projects/search/departamento/{departamento}`
- **Method**: GET
- **Example**: `GET http://localhost:8080/api/projects/search/departamento/LIMA`

### 5. Search Projects by Year
- **URL**: `/api/projects/search/anio/{anio}`
- **Method**: GET
- **Example**: `GET http://localhost:8080/api/projects/search/anio/2022`

### 6. Search Projects by Title
- **URL**: `/api/projects/search/titulo/{titulo}`
- **Method**: GET
- **Example**: `GET http://localhost:8080/api/projects/search/titulo/innovacion`

## cURL Examples

### Get All Projects (Paginated)
```bash
curl -X GET 'http://localhost:8080/api/projects?page=0&size=10' \
-H 'Accept: application/json'
```

### Get Project by ID
```bash
curl -X GET 'http://localhost:8080/api/projects/1' \
-H 'Accept: application/json'
```

### Search Projects by Fund
```bash
curl -X GET 'http://localhost:8080/api/projects/search/fondo/PNIPA' \
-H 'Accept: application/json'
```

### Search Projects by Department
```bash
curl -X GET 'http://localhost:8080/api/projects/search/departamento/LIMA' \
-H 'Accept: application/json'
```

### Search Projects by Year
```bash
curl -X GET 'http://localhost:8080/api/projects/search/anio/2022' \
-H 'Accept: application/json'
```

### Search Projects by Title
```bash
curl -X GET 'http://localhost:8080/api/projects/search/titulo/innovacion' \
-H 'Accept: application/json'
```

### Verify Application Status
```bash
curl -X GET 'http://localhost:8080/health' \
-H 'Accept: application/json'
```

## Data Structure

Projects have the following structure:

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

## Initial Data Load

The application will automatically load data from the CSV file located in `src/main/resources/data/projects.csv` when it starts for the first time. This process will only run if the database is empty.

## Application Monitoring

To check the status of the application, you can use the health endpoint:
- **URL**: `/health`
- **Method**: GET
- **Example**: `GET http://localhost:8080/health`

## Docker Deployment

### Requirements
- Docker
- Docker Compose

### Steps for Deployment

1. **Build and Run with Docker Compose**
   ```bash
   docker-compose up --build
   ```
   This command will build the application image and start both the application and PostgreSQL database.

2. **Stop Containers**
   ```bash
   docker-compose down
   ```

3. **View Application Logs**
   ```bash
   docker-compose logs -f app
   ```

### Cloud Deployment

To deploy on different cloud providers:

#### AWS Elastic Beanstalk
1. Install AWS CLI and configure credentials
2. Create file `Dockerrun.aws.json`
3. Create new application in Elastic Beanstalk
4. Upload application:
   ```bash
   eb init
   eb create
   eb deploy
   ```

#### Google Cloud Run
1. Install Google Cloud CLI
2. Configure project:
   ```bash
   gcloud init
   ```
3. Build and upload image:
   ```bash
   gcloud builds submit --tag gcr.io/[PROJECT_ID]/proinnovate-api
   ```
4. Deploy to Cloud Run:
   ```bash
   gcloud run deploy proinnovate-api --image gcr.io/[PROJECT_ID]/proinnovate-api
   ```

#### Azure Container Instances
1. Install Azure CLI
2. Create container registry:
   ```bash
   az acr create --name proinnovateregistry --resource-group [RESOURCE_GROUP] --sku Basic
   ```
3. Build and upload image:
   ```bash
   az acr build --registry proinnovateregistry --image proinnovate-api .
   ```
4. Deploy container:
   ```bash
   az container create --name proinnovate-api --image proinnovateregistry.azurecr.io/proinnovate-api:latest
   ``` 