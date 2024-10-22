FROM node:lts-alpine3.20 AS build

WORKDIR /app
LABEL authors="Mauricio Monroy <"

COPY package.json ./
COPY yarn.lock ./
RUN  yarn install

COPY . .

RUN yarn run build

FROM nginx:1.21.3-alpine

COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
