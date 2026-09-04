#Build application 
./mvnw clean package -DskipTests

#RUN application
./mvnw spring-boot:run -Dspring-boot.run.profiles=local

# Build and run application with Docker
./mvnw clean package -DskipTests spring-boot:run -Dspring-boot.run.profiles=local

#Docker build create image with name product-service:local

docker build -t product-service:local .


#Run application with Docker below setting referring postgres from docker container

docker run --rm --name product-service -p 8081:8081 \
-e SPRING_PROFILES_ACTIVE=prod \
-e PRODUCT_DB_URL=jdbc:postgresql://host.docker.internal:5432/product_db \
-e PRODUCT_DB_USERNAME=product_user \
-e PRODUCT_DB_PASSWORD=product_local_password \
product-service:local


# In case env variables are not set, you can run the application with the following command:
docker run --rm --name product-service -p 8081:8081 product-service:<tag_name>    local/prod is tag name given while image creation see #Docker build 


Organization key enterprise-commerce-platform
Project key enterprise-commerce-platform_product-service
SONAR_TOKEN  0a01958c095a421d654fee8b632f9de4af62d5ec


