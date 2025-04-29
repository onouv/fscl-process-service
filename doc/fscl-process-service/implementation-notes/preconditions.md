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

The following steps may be assumed by this [helper script](../../../utils/db-init):

1) retrieve credentials, set up a secret 
2) deploy a postgres database 

Steps 2) is implemented in the `src/main/kubernetes/process-db.yaml`manifest. They can be reversed by running  a `kubectl delete -f ...` command on this file.

### Debezium Connector

#### Notes:   
docker pull quay.io/debezium/postgres:15-alpine
https://hub.docker.com/r/debezium/postgres
https://repo1.maven.org/maven2/io/debezium/debezium-connector-postgres/3.1.0.Final/debezium-connector-postgres-3.1.0.Final-plugin.tar.gz
https://strimzi.io/docs/operators/0.34.0/full/configuring#proc-kafka-connect-config-str  
image goes to `hub.docker.com/repository/docker/rabaul/fscl-debezium-connect:latest`

> strimzi cluster operator version 0.45.0  
> kafka version: 3.9.0


Following the [Debezium Kubernetes guide](https://debezium.io/documentation/reference/stable/operations/kubernetes.html)

#### Fetch the connector plugin 
curl https://repo1.maven.org/maven2/io/debezium/debezium-connector-postgres/3.1.0.Final/debezium-connector-postgres-3.1.0.Final-plugin.


#### Build and push Docker Image 

> rabaul/fscl-kafka-connect:latest is already existing, so these steps are shown for info only

```
$: cd src/main/kubernetes/debezium
$: curl https://repo1.maven.org/maven2/io/debezium/debezium-connector-postgres/3.1.0.Final/debezium-connector-postgres-3.1.0.Final-plugin.tar.gz | tar xvz
$: docker build . -t rabaul/fscl-kafka-connect
$: docker push rabaul/fscl-kafka-connect
```
