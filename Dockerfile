# Usar una imagen base de Gradle con JDK 17
FROM gradle:7.5.1-jdk17 as build

# Configurar el directorio de trabajo
WORKDIR /app

# Copiar los archivos de build y configuraciones
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle

# Descargar dependencias sin construir
RUN gradle dependencies --no-daemon

# Copiar el código fuente
COPY src ./src

# Construir la aplicación y omitir los tests
RUN gradle build -x test --no-daemon

# Usar una imagen base de OpenJDK para el runtime
FROM openjdk:17-jdk-slim

# Configurar el directorio de trabajo
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Definir el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
