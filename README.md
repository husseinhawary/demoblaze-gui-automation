

## Getting Started

### Prerequisites

The following software are required:

- Java JDK 11.
- IntelliJ.
- TestNG.
- Maven.
- Allure.
- Google Chrome version should be greater than 109 because I run in headless mode by "--headless=new".

### Installation

1. Clone the repo using below URL

```sh
https://github.com/husseinhawary/demoblaze-gui-automation
```

2. All project dependencies will be installed automatically from pom.xml file once you refresh it:



## Running local and show the report

1. Run local by clicking on testng.xml file and run it
   1. Running local will run in the headless.
   2. If you need to run local in the non-headless mode, please comment this line "chromeOptions.addArguments("headless=new");" in BaseTests class but make sure, that will fail github action job because it works in the headless mode. 
2. OR you can run the tests by using this command
```
mvn clean test
```
3. Show the report
```
allure serve allure-results
```

## Github Actions
The project integrated with github actions and you can see the runs build from here

```
https://github.com/husseinhawary/demoblaze-gui-automation/actions
```

