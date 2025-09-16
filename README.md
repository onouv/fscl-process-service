# FSCL Process Service

NOTE : THIS IS WORK IN PROGRESS !

A microservice backend to [model a process technology view](doc/process-view/process-domain.md) of a technical domain. The service allows users to create a general formal representation of the domain which is distributed and maintained consistently. The model is intended to be integrated with other views such as an automation or safety/reliability management view. [More Details on the Concept...](https://github.com/onouv/fscl/blob/main/doc/fscl/Views/Views.md)

This is the backend service providing a REST API to a SPA frontend. It runs as a pod in kubernetes and is implemented 
in Quarkus. It maintains a data model and communicates updates 
as events published on a kafka backend broker. It also subscribes to events to learn about remote model updates.

## START UP

### Start minikube 

```
$: minikube start --memory=4096 --driver=virtualbox --namespace=fscl
```

### Setup k8s Namespaces
```
$: kubectl apply -f src/main/kubernetes/namespaces.yaml
```

### Initialize Data Base 
The database is expected to run in a kubernetes pod as well. This step provides two ConfigMaps in the k8s cluster with properties and credentials for it. 

#### Init Props and Credentials in k8s

This [helper script](utils/db-init)

- creates a kubernetes ConfigMap for the database url  
- a Secret with user credentials by running this 
- spins up the k8s deployment


   ```
   $: db-init
   FSCL: initializing postgres data base credentials...
   user: fscl
   password: 
   secret/process-db-credentials created
   FSCL: setting up database, role and rolebindings
   configmap/process-db-props created
   service/process-db created
   deployment.apps/process-db created
   ```

### Setup Kafka and Debezium
#### Strimzi Operator

This constitutes the canonical way of installing / managing kafka in k8s. 
```
$: kubectl apply -f src/main/kubernetes/strimzi-operator.yaml
kafka.kafka.strimzi.io/kafka-cluster created
kafkatopic.kafka.strimzi.io/process-functions created
```


#### Kafka Cluster
This sets up a single-node kafka cluster in the `fscl` namespace.

```
$: kubectl apply -f src/main/kubernetes/kafka-cluster.yaml -n kafka
```

TODO: only works for `-n kafka` namespace. Need to figure out where to apply `watchedNamespace: fscl` or `watchAnyNamespace` to the strimzi cluster operator config

### Debezium

Following the [Debezium Kubernetes guide](https://debezium.io/documentation/reference/stable/operations/kubernetes.html)

#### Fetch the connector plugin 
```
$: curl https://repo1.maven.org/maven2/io/debezium/debezium-connector-postgres/3.1.0.Final/debezium-connector-postgres-3.1.0.Final-plugin.
```

#### Build and push Docker Image 

Notice, `rabaul/fscl-kafka-connect:latest` is already existing, so these steps are shown for info only.  

```
$: cd src/main/kubernetes/debezium
$: curl https://repo1.maven.org/maven2/io/debezium/debezium-connector-postgres/3.1.0.Final/debezium-connector-postgres-3.1.0.Final-plugin.tar.gz | tar xvz
$: docker build . -t rabaul/fscl-kafka-connect
$: docker push rabaul/fscl-kafka-connect
```

#### Set up the Workloads

This sets up a debezium connect cluster, a Kafka connector to the postgres database, a role and a role binding, all in the `fscl` namespace.

```
$:kubectl apply -f ../src/main/kubernetes/debezium/debezium.yaml
```

### Build and Deploy the App
#### Build the app and deploy it to your k8s cluster.
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

### Test the deployed Enpoint 
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
   