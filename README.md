
# Demo Test Task

## 1. Project Overview

This project is a demonstration test task written in Java, focusing on backend testing. It includes API-level tests structured with Junit and Spring to interact with external services.

## 2. Architecture Overview

The project follows a simple modular structure typical for Java-based test automation suites:

In java folder there basic infrastructure to make application be able to work with REST APIs. Test folder contains tests and test-related infrastructure. Build configuration is set up in maven pom.xml

## 3. Technologies Used

- **Java 21**: Programming language used for writing the project.
- **JUnit 5**: Test framework used for organizing and running test cases.
- **Spring**: As application framework used mainly for dependency injection.
- **RestClient**: Spring HTTP client library used to send HTTP requests to REST APIs.
- **Jackson**: Library for JSON serialization/deserialization (under the hood of RestClient).
- **Maven**: Build automation tool used to compile the code and manage dependencies.
- **GitHub Actions**: Used for continuous integration and running tests automatically on code changes.
- **Allure**: Used for generating test reports

## 4. Project Setup

To set up the project locally:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/jeenser/demo-test-task.git
   cd demo-test-task
   ```

2. **Build the Project**:
   ```bash
   mvn clean install
   ```

   This will download dependencies and build the project.

## 5. Running the Tests

You can run the tests using Maven:

```bash
mvn clean test
```

To host allure reports locally you can run (Allure local installation is required): 

```bash
allure serve
```

## 6. CI Pipeline

GitHub Actions is used as the CI tool. The workflow is defined in:

```text
.github/workflows/maven.yml
```

The CI pipeline performs the following steps:

- Checks out the repository.
- Sets up Java and Maven environments.
- Builds the project and runs the tests.
- Reports success/failure of the build and test execution.
- Generates Allure reports
- Hosts it in separate branch using GitHub Pages
