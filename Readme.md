# clean and package application without running tests
./mvnw clean package -DskipTests

# Run application with local profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=local

# Build and run application with Docker
./mvnw clean package -DskipTests spring-boot:run -Dspring-boot.run.profiles=local

# Build docker image for product-service tagged with local/prod profile, you can change the tag name as per your requirement
docker build -t product-service:prod .

# Run docker image with prod profile and environment variables for database connection
docker run --rm --name product-service -p 8081:8081 \
-e SPRING_PROFILES_ACTIVE=prod \
-e PRODUCT_DB_URL=jdbc:postgresql://host.docker.internal:5432/product_db \
-e PRODUCT_DB_USERNAME=product_user \
-e PRODUCT_DB_PASSWORD=product_local_password \
product-service:prod

# In case env variables are not set, you can run the application with the following command:
docker run --rm --name product-service -p 8081:8081 product-service:<tag_name>    local/prod is tag name given while image creation see #Docker build 

# SonarQube configuration for product-service

Organization key enterprise-commerce-platform
Project key enterprise-commerce-platform_product-service
SONAR_TOKEN  0a01958c095a421d654fee8b632f9de4af62d5ec

# kubernetes secret creation for product-service-db

kubectl create secret generic product-service-db \
--namespace commerce-demo \
--from-literal=PRODUCT_DB_URL=jdbc:postgresql://host.docker.internal:5432/product_db \
--from-literal=PRODUCT_DB_USERNAME=product_user \
--from-literal=PRODUCT_DB_PASSWORD=product_local_password \
--dry-run=client \
-o yaml |
kubectl apply -f -

# kubernetes deployment

helm upgrade --install product-service \
helm/product-service \
--namespace commerce-demo \
--wait \
--timeout 3m




