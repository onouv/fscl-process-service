
https://dev.to/devlix-blog/remote-debugging-of-java-apps-in-kubernetes-2mo
https://quarkus.io/guides/deploying-to-kubernetes#remote-debugging


![Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/onouv/fscl-process-service/doc/implementation-notes/k8s-debug.component.puml)

[Diagram source](k8s-debug.component.puml)

Add `quarkus.kubernetes.remote-debug.enabled=true` to `application.properties`. This adds a set of command-line options to the execution of the JVM inside the pod, port 5005 is default.

Add a "Remote JVM Debug" run configuration with that port in IntelliJ

Deploy the app 
`$: mvn-2-k8s`

Forward the debug port
`$: kubectl port-forward fscl-process-service-xxxxxx 5005:5005`

In IntelliJ, execute the run configuration as debug to attach debugger remotely.
