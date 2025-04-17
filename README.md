# FSCL Process Service

NOTE : THIS IS WORK IN PROGRESS !

A microservice backend to model a process technology view of a technical domain. 

This is the backend service providing a REST API to a SPA frontend. It runs as a pod in kubernetes and is implemented 
in Quarkus. It maintains a data model and communicates updates 
as events published on a kafka backend broker. It also subscribes to events to learn about remote model updates.

## START UP

### Initialize Data Base Props and Credentials
The database is expected to run in a kubernetes pod as well. This step provides two ConfigMaps in the k8s cluster with properties and credentials for it. 

Create a kubernetes ConfigMap with user credentials by running this [helper script](https://github.com/onouv/dev-bin/blob/main/db-init.sh)
   ```
   $: db-init
   initializing postgres data base
   namespace: fscl
   application: process
   user: process-service
   password: 
   configmap/process-db-credentials created
   ```

### 2 Build the App
Build the app and install the image to dockerhub by executing [app2dck](https://github.com/onouv/dev-bin/blob/main/app2dck)  


 This will spin up a pod for the application as well as a ModePort service for external access.   



Test the app, i.e. using Postman
   ```
   $: minikube service list

   