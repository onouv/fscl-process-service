# Setup Kafka cluster and Strimzi Operator

[Original Quickstart](https://strimzi.io/quickstarts/)

## Creating From Scratch 

Assuming local minikube k8s cluster.

```
$: kubectl create namespace kafka
```

```
$:kubectl create -f 'https://strimzi.io/install/latest?namespace=kafka' -n kafka
```

```
$: kubectl apply -f https://strimzi.io/examples/latest/kafka/kraft/kafka-single-node.yaml -n kafka 
```

## Deleting to Scratch

```
$:kubectl delete -f 'https://strimzi.io/install/latest?namespace=kafka' -n kafka
```
```
$: kubectl delete namespace kafka
```


