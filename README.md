fusepool-prediction-reactor
===========================

Set of bundles to deploy "prediction services" on the Fusepool/Stanbol OSGi platform.

- Xerox Services API: a bundle that exposes packages from the library com.xerox/xeroxservicesapi/1.0-SNAPSHOT/
- PredictionHub: a component that provides a service for any LUP to get registered into a "learning-predicting" index, and an other one to provide any OSGi component to access prediction for any LUPs on the platform
- OpenXerox Client: a component that provides access to the OpenXerox server. This server hosts most of the learning models used by LUPs developed by Xerox
- LearningProbes: a maven reactor which hosts Xerox LUPs
  - LUP::25: a LUP component which allows search boosting; used by the ECS ('xerox' branch)
  - LUP::34: a LUP component which allows labelling prediction; used by Firstswim
