# Stage 1: Build the Angular application
FROM node:18.10.0 as build
WORKDIR /app

# Install npm version 9.8.1
RUN npm install -g npm@9.8.1

# Install dependencies
COPY package.json package-lock.json ./
RUN npm install --legacy-peer-deps

# Copy project files into the Docker image
COPY . .

# Build the Angular application
RUN npm run build -- --configuration production

# Stage 2: Serve the application with Nginx
FROM nginx:alpine
COPY --from=build /app/dist/ /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
