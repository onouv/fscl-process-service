
The local kubernetes setup in minikube can be intercepted by [telepresence](https://www.getambassador.io/docs/telepresence/latest/quick-start). This allows to connect to cluster-internal ip addresses for various purposes: 

`$: telepresence connect`


## Connect to a cluster postgres server from local psql 
```
$: kubectl get svc
NAME                   TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)        AGE
fscl-process-service   NodePort    10.103.29.156   <none>        80:30631/TCP   80m
kubernetes             ClusterIP   10.96.0.1       <none>        443/TCP        21d
postgres               ClusterIP   10.102.107.25   <none>        5432/TCP       80m
```

`$: psql -h 10.102.107.25 -p 5432 -d process -U fscl-process`

then enter the db password configured in the ConfigMap and you are in. Note the postgres pod is not exposed outside of the cluster !

## Debug service running locally, connect to dependencies in cluster

See this [IntelliJ Article](https://blog.jetbrains.com/idea/2021/05/easily-debug-java-microservices-running-on-kubernetes-with-intellij-idea/)

Note the kubernetes cluster can be running remotely (intended case) as well as in a local minikube.

## Ambassador Telepresence

The local kubernetes setup in minikube can be intercepted by [telepresence](https://www.getambassador.io/docs/telepresence/latest/quick-start). This allows to connect to cluster-internal ip addresses for various purposes: 

`$: telepresence connect`

