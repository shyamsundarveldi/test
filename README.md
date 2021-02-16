1) Please run the following commands to generate Kubernetes artifacts such as pod, service, deployment & replicaset and to connect to the application, which will be exposed as a Service in Kubernetes

kubectl apply -f deployment.yaml 
kubectl port-forward svc/demo 8080:8080


I have no knowledge of MongoDB so I developed this application using H2 embedded database as a storage
