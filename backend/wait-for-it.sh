#!/bin/sh
# Espera a que el puerto de MySQL esté disponible
while ! nc -z database 3306; do
  echo "Esperando a que la base de datos MySQL esté disponible..."
  sleep 3
done

# Después de que el puerto esté disponible, ejecuta la aplicación
java -jar /app/app.jar
