## How to start "Activity Finder" application

* download code from:
    * https://github.com/andrzej-t/backendActivityFinder
    * server port: default
* download frontend application from:
    * https://github.com/andrzej-t/frontendActivityFinder
    * server port: 8081
* In order to see fully working application with database You should:
    * create locally empty database activities_db
    * create activities_user and grant him all privileges on activities_db
    * import activities_db_activity.csv, which You can find in backendActivityFinder in resources package
        * spring.datasource.username=activities_user
        * spring.datasource.password=1_act_Pass
* run a backend application in IntelliJ IDEA
* run a frontend application.
    * url: http://localhost:8081