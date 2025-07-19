# Image Tagger API 📸

Una API RESTful construida con Spring Boot que permite a los usuarios subir imágenes, analizarlas usando un servicio de IA (Imagga) para generar etiquetas relevantes, y guardarlas en la nube (Cloudinary).

---

## ✨ Características Principales

-   **Subida de Archivos:** Endpoint para subir imágenes (`multipart/form-data`).
-   **Análisis con IA:** Se integra con la API de Imagga para el etiquetado automático de imágenes.
-   **Almacenamiento en la Nube:** Las imágenes se guardan de forma segura en Cloudinary, no localmente.
-   **Persistencia de Datos:** Toda la metainformación (URL de la imagen, etiquetas, fecha) se guarda en una base de datos.
-   **API de Consulta:** Endpoints para recuperar una imagen por su ID y para buscar imágenes por etiqueta.
-   **Documentación Interactiva:** Documentación de la API generada automáticamente con Springdoc OpenAPI (Swagger UI).

---

## 🛠️ Tecnologías Utilizadas

-   **Framework:** Spring Boot
-   **Lenguaje:** Java 21
-   **Base de Datos:** Spring Data JPA con H2 (para desarrollo)
-   **Almacenamiento de Archivos:** Cloudinary
-   **Servicio de IA:** Imagga API
-   **Documentación:** Springdoc OpenAPI
-   **Build Tool:** Maven

---

## ⚙️ Configuración y Puesta en Marcha

Para ejecutar este proyecto localmente, necesitas configurar las siguientes variables en el archivo `src/main/resources/application.properties`:

1.  **Credenciales de Imagga:**
    ```properties
    imagga.api.key=tu_api_key
    imagga.api.secret=tu_api_secret
    ```
2.  **URL de Cloudinary:**
    ```properties
    cloudinary.url=cloudinary://tu_api_key:tu_api_secret@tu_cloud_name
    ```

### Cómo Ejecutarlo
1.  Clona este repositorio: `git clone https://github.com/tu-usuario/tu-repositorio.git`
2.  Navega a la carpeta del proyecto.
3.  Configura tus credenciales en el archivo `application.properties`.
4.  Ejecuta el proyecto usando Maven: `mvn spring-boot:run`
5.  La aplicación estará disponible en `http://localhost:8080`.

---

## 📖 Endpoints de la API

La documentación completa e interactiva de la API está disponible en Swagger UI una vez que la aplicación está en marcha.

**Accede aquí:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Resumen de Endpoints

| Método | URL                                    | Descripción                                  |
|--------|----------------------------------------|----------------------------------------------|
| `POST` | `/api/v1/images/upload`                | Sube una nueva imagen para ser analizada.    |
| `GET`  | `/api/v1/images/{id}`                  | Obtiene los detalles de una imagen por su ID.|
| `GET`  | `/api/v1/images/search?tag={tagName}`  | Busca imágenes que contengan una etiqueta.   |