FROM nginx:1.23.3
LABEL org.opencontainers.image.title="football-app-angular"
LABEL org.opencontainers.image.version="1.0.0"

LABEL authors="jakub"

ENV VERSION="1.0.0"
ENV API_URL=http://localhost:8080/api

EXPOSE 80

ADD dist/frontend/browser /usr/share/nginx/html/
ADD docker/etc/nginx/templates/ /etc/nginx/templates/
