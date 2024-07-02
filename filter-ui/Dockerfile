FROM node:18 AS build

WORKDIR /app

# Скопируем package.json и package-lock.json
COPY filter-ui/package*.json ./

# Установим зависимости
RUN npm install

# Скопируем остальные файлы и построим приложение
COPY filter-ui/ .
RUN npm run build

# Используем nginx для сервировки статических файлов
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
