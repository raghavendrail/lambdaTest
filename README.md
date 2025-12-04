
## 📘 Documentation

This project automates the Internet Speed Test workflow on a real Android device using LambdaTest. It is designed following best practices in mobile automation, including Page Object Model, TestNG execution, retries, and parallel runs.

---

### 📍 1. Objective

The purpose of this automation suite is to:

* Launch the LambdaTest-hosted mobile application
* Navigate to the browser section
* Run automated speed tests (Download, Upload, Combined, Latency)
* Extract the resulting speed values
* Log and verify the values
* Execute tests in parallel mode
* Generate reports and logs for analysis

---

### 🏗 2. Architecture Overview

| Layer        | Responsibility                                      |
| ------------ | --------------------------------------------------- |
| `base/`      | WebDriver/Appium session management, setup/teardown |
| `pages/`     | Page Object Model — encapsulates app UI logic       |
| `tests/`     | Contains TestNG test cases                          |
| `listeners/` | Retry logic and reporting hooks                     |
| `utils/`     | Helpers such as config reader and encryption        |
| `enums/`     | Enum for test types (Download, Upload, etc.)        |

---

### 📦 3. Dependencies (Maven)

Key libraries used:

* **Selenium**
* **Appium Java Client**
* **TestNG**
* **SLF4J Logging**
* **Jackson Parser**

All dependencies are defined inside `pom.xml`.

---

### 🔐 4. Credentials & Security

No credentials are stored in the repository.

The framework uses environment variables:

```text
LT_USERNAME
LT_ACCESS_KEY
LT_APP_ID
LT_DEVICE
LT_PLATFORM_VERSION
LT_PLATFORM
```

This approach ensures:

✔ No secrets in GitHub
✔ CI/CD friendly
✔ Assignment compliance

---

### ⚙️ 5. Configuration

All runtime values are dynamically injected via environment variables.

Example:

```java
String username = System.getenv("LT_USERNAME");
String accessKey = System.getenv("LT_ACCESS_KEY");
String deviceName = System.getenv("LT_DEVICE");
String appUrl = System.getenv("LT_APP_ID");
```

---

### 🎭 6. Test Execution Flow

Each test follows this flow:

```
DriverFactory → App Launch → SpeedTestPage Actions → Capture Result → Logging & Validation
```

---

### 🧪 7. Test Scenarios Covered

| Test Case     | Action                    | Verification                     |
| ------------- | ------------------------- | -------------------------------- |
| Download Test | Run speed download test   | Extract and print Mbps value     |
| Upload Test   | Run upload speed test     | Extract and print Mbps value     |
| Combined Test | Run combined network test | Extract and validate response    |
| Latency Test  | Run ping test             | Extract and display latency (ms) |

---

### 🚦 8. Parallel Execution

The test suite is parallelized with TestNG:

```xml
parallel="tests"  
thread-count="2"
```

This ensures faster execution and better resource utilization.

---

### 🔁 9. Retry Mechanism

A custom `RetryAnalyzer` reruns failed tests automatically without developer intervention — a standard requirement in mobile automation due to network or device availability changes.

---

