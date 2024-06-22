
```bash
fscl-process-service/
	/resources/
		web/
			openapi.yaml
			schemas/
				data/
					... data dto schemas ...
				commands/
					... cmd dto schemas ...
	target/
		generated-sources/
			openapi/
				src/main/java/ono.org.process.openapi/					
					uiApi/
						api/
							... interfaces and controllers ...
							... starting with 'Oas' ...
							OasFunctionApi.java
							OasSystemApi.java
							...
						model/
							... data dtos ...
	src/
		main/
			java/org/ono/fscl/process/service/
				functions/
					adapters/
						upstream/
							FunctionController.java
						downstream/
							...
					ports/
						upstream/
							web/
								FunctionsApi.java  
								# extends ono.org.process.openapi.OasFunctionsApi
						downstream/
							FunctionsRepository.java
							...
					appservices/
						FunctionsLifecycleService.java
						FunctionsConnectorService.java
						FunctionsParameterService.java
					domain/
						Function.java
				systems/
					adapters/
						upstream/
							...
						downstream/
							...
					ports/
						upstream/
							web/
								SystemsApi.java  
								# extends ono.org.process.openapi.OasSystemsApi
						downstream/
							...
					appservices/
						...
					domain/
						Function.java
						
				component/
					...
				location/
					...
```


```bash
fsl-project-service/
	src/
		main/
			java.org.ono.fscl/process/service/
				function/
					adapters/
						upstream/
							FunctionController.java
						downstream/
							...
					ports/
						upstream/
							FunctionLifecyclePort.java
						downstream/
							FunctionRepository.java
							...
					appservices/
						FunctionLifecycleService.java
					domain/
						Function.java
				system/
					adapters/
						upstream/
							...
						downstream/
							...
					ports/
						upstream/
							...
						downstream/
							...
					appservices/
						...
					domain/
						Function.java
				component/
					...
				location/
					...
```