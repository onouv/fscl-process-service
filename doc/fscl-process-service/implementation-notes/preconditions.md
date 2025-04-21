          Precondition                   Action

1)      minikube running                mnk-start
2)      docker login succeeded          docker login, check ~/.docker/config.json
3)      reposilite running              system service default
4)      reposilite credentials          check ~/.m2/settings.xml, must be according to server token
5)      quarkus build; quarkus deploy
6)      strimzi-operator deployed       
6)      debezium server configured
7)      debug port exposed              kubectl port-forward fscl-process-service-xxxxxx 5005:5005, then set up IDE configuration for that port
  
    
---  
---  

# Operator Package Manager (opm)

required for creating catalogs in OLM

### Install CLI

https://docs.redhat.com/en/documentation/openshift_container_platform/4.10/html/cli_tools/opm-cli#olm-about-opm_cli-opm-install

https://docs.redhat.com/en/documentation/openshift_container_platform/4.10/html/cli_tools/opm-cli#cli-opm-ref

### Remarks
1) `opm generate dockerfile ...` NOT working, must use `opm alpha generate dockerfile ...`  :-(
2) `opm version` does NOT provide any version number usable in the documention :-( 

Looks like the doc and usability philosophy of this toolset really sucks

```
$: opm alpha generate dockerfile default-catalog/ ; ll
drwxrwxr-x 2 ono ono 4,0K Apr 21 12:38 default-catalog/
-rw-rw-r-- 1 ono ono  466 Apr 21 12:59 default-catalog.Dockerfile
```

# Operator Lifecycle Manager (OLM)

Required for deploying operators like strimzi and debezium in the canonical way

### Overview
https://olm.operatorframework.io

### Install SDK (CLI) 
https://sdk.operatorframework.io/docs/installation/

### Deploy OLM (k8s Cluster) 
https://olm.operatorframework.io/docs/getting-started/



# Strimzi 

The canonical way of deploying and managing a kafka cluster on a k8s cluster.

### Overview
https://strimzi.io/  
https://strimzi.io/documentation/

### Deploying Strimzi Operator and a Kafka single-node Cluster
https://strimzi.io/quickstarts/

Just the operator:  
https://operatorhub.io/operator/strimzi-kafka-operator



# Debezium

Required for Change Data Capture/ Transactional Outbox Pattern 

### Overview
https://debezium.io/

### Deploy on k8s Cluster
https://debezium.io/documentation/reference/stable/operations/kubernetes.html


### Detailed Outbox Article 
https://debezium.io/blog/2019/02/19/reliable-microservices-data-exchange-with-the-outbox-pattern/

https://github.com/debezium/debezium-examples/tree/main/outbox


# Startup From Scratch

### Minikube

Assuming minikube is installed and started.
```
minikube addons enable registry
```  

### Namespaces

```
kubectl apply -f src/main/kubernetes/namespaces
namespace/kafka created
namespace/debezium-fscl created
namespace/fscl created
```


### Strimzi and Kafka
```
kubectl create -f https://operatorhub.io/install/strimzi-kafka-operator.yaml
```

```
kubectl create -f src/main/kubernetes/kafka-cluster.yaml;
kubectl wait kafka/kafka-cluster --for=condition=Ready --timeout=180s -n fscl
```


### Postgres Database 'process-db'
#### User Credentials for DB.   
Set up a secret, a role and rolebinding for the user credentials in kubernetes.

This may be achieved by running this [helper script](https://github.com/onouv/dev-bin/blob/main/db-init):

```
$: db-init
FSCL: initializing postgres data base credentials
namespace: fscl
application: process
user: fscl
password: 
secret/process-db-credentials created
```

#### Set up a postgres deployment:  
```
$: kubectl create -f src/main/kubernetes/process-db.yaml
```

### 
