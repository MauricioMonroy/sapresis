FROM node:lts-alpine3.20 AS build

WORKDIR /app
LABEL authors="Mauricio Monroy <"

COPY package.json ./
COPY package-lock.json ./
RUN  npm install

COPY . .

ARG REACT_APP_API_URL
ENV REACT_APP_API_URL=$REACT_APP_API_URL

RUN npm run build

FROM node:lts-alpine3.20

WORKDIR /app
COPY --from=build /app/build .

RUN npm install -g serve

EXPOSE 3000
CMD ["serve", "-s", ".", "-l", "3000"]
