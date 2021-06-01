#Runeterra Reporter

This project aims to allow data reporting for Legends of Runeterra. It is intended to generate meta reports
on archetypes playrate and winrate.

The project is structured in a way that the data gathering, processing and output are decoupled. The processing
is defined in the `runeterra-reporter-core` module which contains the core business rules.

A `MetaDatasource`, `CardRepository` and `MetaReportWriter` interface are also defined in the core module.

This way other modules can provide different implementation for those services.

At first there will be a MongoDB implementation for the `CardRepository` service and a Mobalytics API
implementation for the `MetaDataSource`. A CSV writer is also included in the core module.