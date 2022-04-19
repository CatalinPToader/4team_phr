FROM python:3.8-alpine

COPY . /app
WORKDIR /app

RUN apk add gcc
RUN apk add --no-cache mariadb-connector-c-dev
RUN apk update && apk add python3 python3-dev mariadb-dev build-base musl-dev && pip3 install mysqlclient && apk del python3-dev mariadb-dev build-base

RUN apk add netcat-openbsd bash git openssh

RUN pip3 install -r requirements.txt