FROM node:22-alpine AS build

ENV NODE_ENV=production

WORKDIR /app

COPY package.json package-lock.json ./

RUN npm ci

COPY . .

RUN npm run build

RUN rm -rf node_modules && npm ci --only=production

FROM nginx:alpine

COPY --from=build /app/dist/meteo-es-ui/browser /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
