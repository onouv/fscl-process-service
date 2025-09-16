
# Process View

![Overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/onouv/fscl-process-service/refs/heads/main/doc/fscl-process-service/process-view/process-domain.puml)

[Overview source](process-domain.puml)
  
A *Function, Component*, ... may have any number of *Parameters*. These are intended to be referenced in other entities of this view or even other view. 

>**Example**  
>  
> A "Pump Feedwater" *ProcessFunction* object has a suction header, a discharge header and flow rate as inputs. From this, rated power and rated RPM can be determined, but this depends on load curves  of a concrete pumd component, so that calculation is deferred to the *Component* workflow

A *Parameter* may depend on any number of other parameters. This is expressed by the association to a *DependencyList* (not shown in diagram).  A *Function* therefore may have any number of *DependencyLists*. A *DependencyList* does not actually compute any new values from the *Parameter* but only keeps track on which *Parameter* depends on which other.   

[Worked Example...](example.md)

[More on the overall concept...](https://github.com/onouv/fscl/tree/main)