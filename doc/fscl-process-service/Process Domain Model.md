

```plantuml
hide empty members
package fscl {

	package shadow{ 
		package component {
			class Component
		}
		
		package function {
			class Function
		}
		abstract class Parameter {
			name
			value
		}		
		Parameter --> "0..*" Parameter
	}

	package process.service.domain {
	
		package function {
			class ProcessFunction <<Aggregate Root>>
		
			ProcessFunction --|> Function
			ProcessFunction *--> "0..*" Parameter	
			
		}
		
		package component {
			class ProcessComponent <<Aggregate Root>>
		
			ProcessComponent --|> Component
			ProcessComponent *--> "0..*" Parameter	
			
		}
		
	}
```

A *ProcessFunction, ProcessComponent*, ... may have any number of *Parameters*. These are intended to be referenced in other entities of this view or even other view. 

>[!info] [[Example]]
> A "Pump Feedwater" *ProcessFunction* object has a suction header, a discharge header and flow rate as inputs. From this, rated power and rated RPM can be determined, but this depends on load curves  of a concrete pumd component, so that calculation is deferred to the *ProcessComponent* workflow

A *Parameter* may depend on any number of other parameters. This is expressed by the association to a *DependencyList* (not shown in diagram).  A *ProcessFunction* therefore may have any number of *DependencyLists*. A *DependencyList* does not actually compute any new values from the *Parameter* but only keeps track on which *Parameter* depends on which other.   

[Elaborated Example](Example.md)