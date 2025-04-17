# FSCL Process Service

NOTE : THIS IS WORK IN PROGRESS !

A microservice backend to [model a process technology view](doc/fscl-process-service//Process%20Domain%20Model.md) of a technical domain. The service allows users to create a general formal representation of the domain which is distributed and maintained consistently. The model is intended to be integrated with other views such as an automation or safety/reliability management view. [More Details on the Concept...](https://github.com/onouv/fscl/blob/main/doc/fscl/Views/Views.md)

This is the backend service providing a REST API to a SPA frontend. It runs as a pod in kubernetes and is implemented 
in Quarkus. It maintains a data model and communicates updates 
as events published on a kafka backend broker. It also subscribes to events to learn about remote model updates.

## START UP

### 1 Initialize Data Base 
The database is expected to run in a kubernetes pod as well. This step provides two ConfigMaps in the k8s cluster with properties and credentials for it. 

#### Init Props and Credentials in k8s

1) Create a kubernetes ConfigMap with user credentials by running this [helper script](https://github.com/onouv/dev-bin/blob/main/db-init.sh)
   ```
   $: db-init
   initializing postgres data base
   namespace: fscl
   application: process
   user: process-service
   password: 
   configmap/process-db-credentials created
   ```

2) Spin up the k8s items
   ```
   $: kubectl apply -f src/main/kubernetes/process-db.yaml
   ```

### 2 Build and Deploy the App
Build the app and deploy it to your k8s cluster.
```
$: quarkus build; quarkus deploy
```
This will spin up a pod for the application as well as a NodePort service for external access.   




#### Inspect the k8s setup

```
$: kubectl get pods -n fscl 
NAME                               READY   STATUS    RESTARTS  ...
process-db-74c99d645d-wc874        1/1     Running   0          
process-service-5d97d894bd-lkt9r   1/1     Running   0          

$: kubectl get services -n fscl 
NAME              TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)  ...              
process-db        ClusterIP   10.96.70.245    <none>        5432/TCP                      
process-service   NodePort    10.106.176.56   <none>        5005:31828/TCP,80:30307/TCP   

$: kubectl get configmaps -n fscl
NAME                     DATA   AGE
kube-root-ca.crt         1      1d
process-db-credentials   2      1h
process-db-props         1      1h

```

### 3 Test the deployed Enpoint 
#### Retrieve the endpoint
> Assuming the deployment happens on a local minikube cluster. For different platforms, the services url must be retrieved according to the specific technology (kubectl normally cannot provide that information, as mapping of ports is managed by the container system) 

```
$: minikube service list -n fscl
|-----------|-----------------|--------------|-----------------------------|
| NAMESPACE |      NAME       | TARGET PORT  |             URL             |
|-----------|-----------------|--------------|-----------------------------|
| fscl      | process-db      | No node port |                             |
| fscl      | process-service | debug/5005   | http://192.168.59.117:31828 | <--- (2)
|           |                 | http/80      | http://192.168.59.117:30307 | <--- (1)
|-----------|-----------------|--------------|-----------------------------|
```

> (1) the business endpoint  
>  
> (2) Notice you can attach a debugger to the service at this endpoint  
  
#### Check the endpoint
```
$: curl http://192.168.59.117:30307/fscl/v2/process/function/lifesign
fscl process service function endpoint is alive.
```
   