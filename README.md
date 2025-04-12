# InnovationProjectsPeru - API de Proyectos de Innovación

Este proyecto es una API REST que gestiona información sobre proyectos de innovación financiados por el estado peruano. La aplicación está construida con Spring Boot y utiliza PostgreSQL como base de datos.

## Requisitos Previos

- Java 21
- PostgreSQL 12 o superior
- Maven 3.6 o superior

## Configuración del Entorno

1. **Base de Datos**
   ```sql
   CREATE DATABASE proyectos_proinnovate_peru;
   ```

2. **Configuración de la Base de Datos**
   - Abrir `src/main/resources/application.properties`
   - Modificar las siguientes propiedades según tu configuración:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/proyectos_proinnovate_peru
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   ```

## Instalación y Ejecución

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

La aplicación estará disponible en `http://localhost:8080`

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