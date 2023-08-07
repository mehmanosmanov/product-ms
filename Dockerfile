FROM openjdk
WORKDIR /home
COPY build/libs/product-ms-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","/home/product-ms-0.0.1-SNAPSHOT.jar"]