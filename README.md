# SauceDemo UI Automation Framework

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.47.0-green)
![TestNG](https://img.shields.io/badge/TestNG-7.12.0-red)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![Allure](https://img.shields.io/badge/Allure-Reports-yellow)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

A scalable and maintainable **UI automation framework** built using **Java, Selenium WebDriver, TestNG, Maven, and Page Object Model (POM)** for testing the [SauceDemo](https://www.saucedemo.com/) web application.

The framework is designed with real-world SDET practices in mind, including reusable page objects, centralized WebDriver management, configurable test execution, parallel-ready architecture, retry handling, failure screenshots, logging, and Allure reporting.

> **Project Goal:** Demonstrate how a maintainable Selenium automation framework can be designed for functional, regression, and CI/CD execution.

---

## 📌 Application Under Test

**SauceDemo**

https://www.saucedemo.com/

The automation suite covers the major user journeys across:

* Login
* Product inventory
* Product sorting
* Add to cart
* Cart validation
* Checkout information
* Checkout overview
* Order completion

---

# 🏗️ Framework Architecture

```text
                         ┌───────────────────────┐
                         │      TestNG Tests     │
                         │                       │
                         │ LoginPageTest         │
                         │ InventoryPageTest     │
                         │ CartPageTest          │
                         │ CheckoutInfoPageTest  │
                         │ CheckoutPageTest      │
                         └───────────┬───────────┘
                                     │
                                     ▼
                         ┌───────────────────────┐
                         │      Page Objects     │
                         │                       │
                         │ LoginPage             │
                         │ InventoryPage         │
                         │ CartPage              │
                         │ CheckoutInfoPage      │
                         │ CheckoutOverviewPage  │
                         │ CheckoutCompletePage  │
                         └───────────┬───────────┘
                                     │
                                     ▼
                         ┌───────────────────────┐
                         │     Utility Layer     │
                         │                       │
                         │ ElementUtil           │
                         │ JavaScriptUtil        │
                         └───────────┬───────────┘
                                     │
                                     ▼
                         ┌───────────────────────┐
                         │    Driver Factory     │
                         │                       │
                         │ Chrome                │
                         │ Firefox              │
                         │ Edge                  │
                         │ Safari                │
                         │ RemoteWebDriver       │
                         └───────────┬───────────┘
                                     │
                                     ▼
                         ┌───────────────────────┐
                         │     Selenium WebDriver│
                         └───────────────────────┘
```

---

# 🛠️ Technology Stack

| Technology         | Purpose                            |
| ------------------ | ---------------------------------- |
| Java               | Programming language               |
| Selenium WebDriver | Browser automation                 |
| TestNG             | Test execution and assertions      |
| Maven              | Build and dependency management    |
| Page Object Model  | Maintainable test architecture     |
| Allure             | Test reporting                     |
| Log4j2             | Application/framework logging      |
| AspectJ            | Allure `@Step` support             |
| Selenium Grid      | Remote browser execution           |
| Git                | Version control                    |
| CI/CD              | Automated build and test execution |

---

# 📂 Project Structure

```text
saucedemo_pom_se_testng
│
├── pom.xml
├── README.md
├── .gitignore
│
├── .mvn
│   ├── maven.config
│   └── jvm.config
│
├── src
│   │
│   ├── main
│   │   └── java
│   │       └── com.qa.saucedemo
│   │           │
│   │           ├── constants
│   │           │   └── AppConstants.java
│   │           │
│   │           ├── errors
│   │           │   └── AppError.java
│   │           │
│   │           ├── exceptions
│   │           │   ├── BrowserException.java
│   │           │   └── FrameworkException.java
│   │           │
│   │           ├── factory
│   │           │   ├── DriverFactory.java
│   │           │   └── OptionsManager.java
│   │           │
│   │           ├── listeners
│   │           │   ├── AnnotationTransformer.java
│   │           │   ├── Retry.java
│   │           │   └── TestAllureListener.java
│   │           │
│   │           ├── models
│   │           │   └── ProductDetails.java
│   │           │
│   │           ├── pages
│   │           │   ├── LoginPage.java
│   │           │   ├── InventoryPage.java
│   │           │   ├── CartPage.java
│   │           │   ├── CheckoutInfoPage.java
│   │           │   ├── CheckoutOverviewPage.java
│   │           │   └── CheckoutCompletePage.java
│   │           │
│   │           └── utils
│   │               ├── ElementUtil.java
│   │               └── JavaScriptUtil.java
│   │
│   └── test
│       │
│       ├── java
│       │   └── com.qa.saucedemo
│       │       ├── base
│       │       │   └── BaseTest.java
│       │       │
│       │       └── tests
│       │           ├── LoginPageTest.java
│       │           ├── InventoryPageTest.java
│       │           ├── CartPageTest.java
│       │           ├── CheckoutInfoPageTest.java
│       │           └── CheckoutPageTest.java
│       │
│       └── resources
│           ├── config
│           │   └── config.properties
│           │
│           ├── testrunners
│           │   └── testng_chrome.xml
│           │
│           └── allure.properties
│
└── target
```

> `target/` is generated by Maven and should not be committed to source control.

---

# 🧪 Test Coverage

## Login

* Verify Login page title
* Login with valid credentials
* Login with invalid username
* Login with invalid password
* Login with locked-out user

## Inventory

* Verify all products are displayed
* Validate product count
* Sort products by price: low → high
* Sort products by price: high → low
* Add one product to cart
* Add multiple products to cart

## Cart

* Validate cart page
* Validate product information
* Validate product quantity
* Validate product price
* Validate product description
* Navigate to checkout

## Checkout Information

Negative validation scenarios:

* Missing first name
* Missing last name
* Missing postal code

## Checkout

End-to-end checkout flow:

```text
Login
  ↓
Inventory
  ↓
Add Product
  ↓
Cart
  ↓
Checkout Information
  ↓
Checkout Overview
  ↓
Validate Order Summary
  ↓
Finish Purchase
  ↓
Order Confirmation
```

---

# 🧩 Framework Design

## Page Object Model

Each application page is represented by a dedicated page class.

Example:

```text
LoginPage
    ↓
InventoryPage
    ↓
CartPage
    ↓
CheckoutInfoPage
    ↓
CheckoutOverviewPage
    ↓
CheckoutCompletePage
```

Page classes contain:

* Locators
* Page actions
* Page-level validations
* Navigation between pages

Test classes are responsible for:

* Test scenarios
* Test data
* Assertions
* TestNG configuration

This keeps test cases readable and minimizes duplicated Selenium code.

---

# 🚗 WebDriver Management

The framework uses a centralized `DriverFactory` for browser initialization.

Supported browsers:

* Chrome
* Firefox
* Edge
* Safari

Local execution uses the corresponding Selenium browser driver.

Remote execution is supported using `RemoteWebDriver` and Selenium Grid.

The framework also uses:

```java
ThreadLocal<WebDriver>
```

to maintain a separate WebDriver instance per executing thread.

This makes the driver architecture suitable for parallel test execution.

---

# ⚙️ Configuration Management

Test execution can be controlled using configuration properties.

Example:

```properties
BROWSER=chrome
URL=https://www.saucedemo.com/
HEADLESS=false
INCOGNITO=false
HIGHLIGHT=true
REMOTE=false
HUB_URL=http://localhost:4444/wd/hub
```

The framework also supports overriding configuration values from the command line.

Example:

```bash
mvn test -Dbrowser=firefox -Dheadless=true
```

This allows CI/CD pipelines to control execution without modifying source files.

---

# 🌐 Environment Support

The framework supports environment-specific configuration:

```text
config.properties
dev.config.properties
stage.config.properties
uat.config.properties
prod.config.properties
```

Environment can be selected using:

```bash
mvn test -Denv=stage
```

If no environment is supplied, the framework defaults to the QA configuration.

---

# 🖥️ Headless Execution

Tests can be executed without opening a browser window.

Example:

```bash
mvn test -Dheadless=true
```

This is particularly useful for CI/CD environments.

---

# 🔄 Parallel Execution

The TestNG suite is configured for parallel execution.

Example:

```xml
<suite
    name="Sauce Lab Demo Test"
    thread-count="3"
    parallel="tests">
```

Different TestNG `<test>` blocks can execute independently while the framework maintains separate WebDriver instances.

---

# 🔁 Retry Mechanism

The framework implements a custom TestNG retry mechanism.

```text
Test Failure
     ↓
Retry Analyzer
     ↓
Retry test
     ↓
Maximum retry attempts
     ↓
Final test result
```

The retry functionality is attached globally using a TestNG `IAnnotationTransformer`.

This avoids adding:

```java
retryAnalyzer = Retry.class
```

to every individual test.

---

# 📊 Allure Reporting

The framework integrates Allure for rich test reporting.

Allure provides:

* Test execution status
* Test descriptions
* Test severity
* Test ownership
* Test steps
* Failure details
* Screenshots
* Execution history

Tests use Allure annotations such as:

```java
@Epic
@Feature
@Story
@Description
@Severity
@Owner
```

Page actions can also be documented using:

```java
@Step
```

---

# 📸 Failure Screenshots

The framework includes a custom Allure TestNG listener.

When a test fails:

```text
Test Failure
     ↓
TestAllureListener
     ↓
Capture screenshot
     ↓
Attach screenshot to Allure
     ↓
Attach failure information
```

This makes debugging failed UI tests significantly easier.

---

# 📝 Logging

The framework uses **Log4j2** for structured logging.

Logging is implemented across framework components such as:

* Driver initialization
* Browser selection
* Environment selection
* Login attempts
* Framework operations

Sensitive values such as passwords are masked in logs.

Example:

```text
user credentials - userName:standard_user, password:******
```

---

# 📦 Maven

The project uses Maven for:

* Dependency management
* Compilation
* Test execution
* Allure integration
* Packaging

Main dependencies include:

* Selenium WebDriver
* TestNG
* Allure TestNG
* Log4j2
* AspectJ

---

# ▶️ How to Run Tests

## Prerequisites

Install:

* Java 17+
* Maven 3.8+
* Git
* Chrome / Firefox / Edge

Verify installations:

```bash
java -version
mvn -version
git --version
```

---

## Clone the Repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd saucedemo_pom_se_testng
```

---

## Run TestNG Suite

```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testrunners/testng_chrome.xml
```

---

## Run in Headless Mode

```bash
mvn clean test \
-DsuiteXmlFile=src/test/resources/testrunners/testng_chrome.xml \
-Dheadless=true
```

---

## Run on Firefox

```bash
mvn clean test \
-DsuiteXmlFile=src/test/resources/testrunners/testng_chrome.xml \
-Dbrowser=firefox
```

---

## Run Using Selenium Grid

Start Selenium Grid and configure:

```properties
REMOTE=true
HUB_URL=http://localhost:4444/wd/hub
```

Then execute:

```bash
mvn clean test
```

---

# 📈 Allure Report

After test execution, Allure results are generated under:

```text
target/allure-results
```

Generate the report:

```bash
allure generate target/allure-results --clean -o target/allure-report
```

Open the report:

```bash
allure open target/allure-report
```

Alternatively:

```bash
allure serve target/allure-results
```

---

# 🧪 Test Execution Strategy

The framework uses different TestNG lifecycle strategies depending on the purpose of the test.

### Independent tests

Validation tests that should start from a clean application state can use:

```java
@BeforeMethod
```

### Sequential E2E workflows

A complete business flow can use:

```java
@BeforeClass
```

combined with:

```java
dependsOnMethods
```

For example:

```text
Checkout Customer Information
          ↓
Order Summary Validation
          ↓
Complete Purchase
```

This allows the framework to distinguish between independent functional tests and intentionally stateful end-to-end workflows.

---

# 🧱 Framework Principles

The framework follows several maintainability principles:

### Single Responsibility

Page objects manage page interactions.

Test classes manage test scenarios and assertions.

Utilities provide reusable Selenium operations.

### Reusability

Common browser and element operations are centralized in utility classes.

### Maintainability

Locators are maintained within page objects rather than test classes.

### Configurability

Browser, environment, headless mode, and remote execution can be controlled externally.

### Scalability

The framework structure allows additional:

* Pages
* Test classes
* Environments
* Browsers
* Test suites
* CI/CD pipelines

to be added without major architectural changes.

---

# 🔐 Test Data & Credentials

Credentials should not be hardcoded in production CI/CD environments.

For local execution, credentials can be supplied through configuration or system/environment variables.

For CI/CD execution, sensitive values should be stored using the CI platform's secret-management mechanism.

Example:

```bash
mvn test \
-Dusername=$APP_USERNAME \
-Dpassword=$APP_PASSWORD
```

> Never commit production credentials, API keys, tokens, or other secrets to Git.

---

# 🚀 CI/CD Integration

CI/CD integration is planned as part of this framework.

The pipeline will provide automated:

```text
Code Push / Pull Request
          ↓
Checkout Repository
          ↓
Setup Java
          ↓
Install Dependencies
          ↓
Run Selenium Tests
          ↓
Generate Allure Results
          ↓
Publish Test Results
          ↓
Archive Logs / Screenshots
```

Planned CI/CD capabilities include:

* Automated test execution
* Headless browser execution
* Maven build
* TestNG suite execution
* Environment selection
* Browser selection
* Allure report generation
* Failure screenshots
* Test result artifacts
* Pipeline failure on test failure

---

# 🔮 Future Enhancements

The framework is designed to evolve toward a more complete SDET automation platform.

Planned enhancements include:

* [ ] GitHub Actions CI/CD pipeline
* [ ] Allure report publishing in CI
* [ ] Dockerized Selenium Grid
* [ ] Parallel cross-browser execution
* [ ] Test data externalization
* [ ] API automation layer using REST Assured
* [ ] Database validation
* [ ] Docker integration
* [ ] Kubernetes-based execution
* [ ] Cloud execution using Selenium Grid providers
* [ ] Slack/Teams test notifications
* [ ] Scheduled regression execution
* [ ] Advanced test data management

---

# 🎯 What This Project Demonstrates

This project demonstrates practical SDET framework development skills including:

* Selenium WebDriver automation
* Java programming
* Object-oriented design
* Page Object Model
* TestNG
* Maven
* Test lifecycle management
* Data-driven testing
* Parallel execution
* ThreadLocal WebDriver management
* Remote WebDriver / Selenium Grid
* Configuration management
* Retry mechanisms
* Custom listeners
* Logging
* Allure reporting
* Failure diagnostics
* End-to-end testing
* Negative testing
* CI/CD readiness

---

# 👨‍💻 Author

**Manish Kumar**

Automation Test Engineer / SDET

14+ years of experience in test automation and software quality engineering.

### Technical Focus

* Java
* Selenium
* Playwright
* TestNG / JUnit
* Cucumber / BDD
* REST Assured
* API Automation
* CI/CD
* Docker
* Kubernetes
* AWS
* DevOps
* AI-assisted Test Automation

---

# ⭐ If You Find This Project Useful

If this project helps you understand Selenium framework design, feel free to ⭐ star the repository and explore the implementation.

---

## 📄 License

This project is intended for learning, demonstration, and portfolio purposes.
