

```plantuml
hide empty members
package fscl {

	package core {

		package entity {
			class Entity
		}
		package function {
			class FunctionBase
		}

		package component {
			class ComponentBase
		}
		
		package parameter {
			abstract class Parameter {
				name
				value
			}		
			Parameter --> "0..*" Parameter
		}
		Entity *--> "0..*" Parameter
		FunctionBase --|> Entity
		ComponentBase --|> Entity
	}

	package shadow{ 
		package component {
			class ShadowComponent
		}
		package function {
			class ShadowFunction
		}
	}

	ShadowFunction --|> FunctionBase
	ShadowComponent --|> ComponentBase
	
	package process.service.domain {
	
		package function {
			class Function <<Aggregate Root>>
			Function --|> FunctionBase
			Function --> "1" ShadowFunction
		}
		
		package component {
			class Component <<Aggregate Root>>
			Component --|> ComponentBase
			Component --> "1" ShadowComponent
		}
	}
```

An `fscl.process.service.domain.function.Function` may have any number of *Parameters.* These are intended to be referenced in other entities of this view or even other views. 

>[!info] [[Example]]
> A "Pump Feedwater" *Function* object has a suction header, a discharge header and flow rate as inputs. From this, rated power and rated RPM can be determined, but this depends on load curves  of a concrete pum component, so that calculation is deferred to a later workflow specifying the *Component*
> 


A *Parameter* may depend on any number of other parameters. This is expressed by the association to itself.  A `Function` or any `Entity` in fact therefore may have any number of such dependencies. A dependency does not necessarily compute any new values from the `Parameter` but at least keeps track on which `Parameter` depends on which other, so an implementation can indicate a changed dependency to the user.