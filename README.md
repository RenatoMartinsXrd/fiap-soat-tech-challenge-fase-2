## Some features of this project

1. TODO

## Requirements to run this project locally

1. Java SDK 19
2. Docker
3. Docker Desktop
4. Kubernetes
5. (Optional) Beekeeper Studio or DBeaver


## Recommended IntelliJ Plugins

1. Lombok
2. Restful Api Tool

## How to run this project using Docker and Kubernetes

### Build the Docker Image

```sh
docker build -t tech-challenge-fase-1 .
```

### Apply all Application K8s Manifests

```sh
kubectl apply -f k8s/app/app-hpa.yaml
kubectl apply -f k8s/app/app-configmap.yaml
kubectl apply -f k8s/app/app-deployment.yaml
kubectl apply -f k8s/app/app-service.yaml
kubectl apply -f k8s/app/app-secret.yaml
```

### Apply all Databse K8s Manifests

```sh
kubectl apply -f k8s/db/db-configmap.yaml
kubectl apply -f k8s/db/db-pv.yaml
kubectl apply -f k8s/db/db-pvc.yaml
kubectl apply -f k8s/db/db-deployment.yaml
kubectl apply -f k8s/db/db-service.yaml
kubectl apply -f k8s/db/db-secret.yaml
```

### Test the application

```sh
curl -X GET "localhost:30080/customers"
```

### Useful commands

```sh
kubectl rollout restart deployment poc-hexagonal-arch
```

```sh
watch -n 1 kubectl top pods
```

```sh
kubectl delete all --all -n default
```

```sh
kubectl port-forward service/poc-hexagonal-arch 30080:8080
```

Enjoy! API should be accessible on localhost:30080

### Running Docker containers

1. Access the folder where docker-compose.yml is located
2. Run the command:

```
docker compose up -d 
```

### Accessing OpenAPI/Swagger

1. Open the url on your web browser: ```http://localhost:8080/swagger-ui/index.html```

### Connecting to Postgres using PGAdmin

1. Access PGAdmin on any web browser using the address: http://localhost:5050/
2. If this is the first time, set a master password, such as: master
3. Create a server with the following configuration:

```
Name: Any Name
Host name/address: host.docker.internal
Port: 5432
Username: postgres
Password: changeme
```

### Debug pod

```sh
kubectl apply -f k8s/debug-pod.yaml
```

```sh
kubectl exec -it debug-pod sh
```

```sh
nslookup db-service
```

### Install Kubernetes Metrics Server

Metrics server is needed in order to Horizontal Pod (HPA) Autoscaling to work properly

```sh
kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/latest/download/components.yaml
```