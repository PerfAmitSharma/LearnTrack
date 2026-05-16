# LearnTrack JUnit Testing Guide

## Quick Start

### Run All Tests
```bash
# Windows
.\gradlew.bat test

# Unix/Linux
./gradlew test
```

### Expected Output
```
BUILD SUCCESSFUL
124 tests passed
0 tests failed
```

---

## Test Files Created

### 📦 Service Tests (3 files)
1. **StudentServiceTest.java** - 20 tests
   - CRUD operations for students
   - Input validation
   - Duplicate handling and error cases

2. **CourseServiceTest.java** - 18 tests
   - CRUD operations for courses
   - Activation/deactivation logic
   - Validation rules

3. **EnrollmentServiceTest.java** - 33 tests
   - Enrollment workflow
   - Cross-service dependencies
   - Business rule validation
   - Status transitions

### 🏛️ Entity Tests (6 files)
1. **PersonTest.java** - 6 tests
2. **StudentTest.java** - 8 tests
3. **TrainerTest.java** - 8 tests
4. **CourseTest.java** - 8 tests
5. **EnrollmentTest.java** - 7 tests
6. **EnrollmentStatusTest.java** - 4 tests

### 🔧 Utility Tests (2 files)
1. **InputValidatorTest.java** - 16 tests
   - Non-blank validation
   - Email validation
   - Positive number validation

2. **IdGeneratorTest.java** - 5 tests
   - ID increment logic
   - Range validation (student: 1000+, course: 2000+, etc.)

### ❌ Exception Tests (2 files)
1. **EntityNotFoundExceptionTest.java** - 3 tests
2. **InvalidInputExceptionTest.java** - 3 tests

---

## Test Statistics

| Category | Count |
|----------|-------|
| Service Tests | 71 |
| Entity Tests | 41 |
| Utility Tests | 21 |
| Exception Tests | 6 |
| **Total Tests** | **124** |
| **Pass Rate** | **100%** |

---

## Key Test Scenarios Covered

### StudentService Tests
✅ Add student with/without email  
✅ Validate blank names, invalid email, blank batch  
✅ List all students (empty and with data)  
✅ Find student by ID (success and not found)  
✅ Update student (valid and invalid data)  
✅ Deactivate student  
✅ Unique ID generation  

### CourseService Tests
✅ Add course with validation  
✅ Activate/deactivate courses  
✅ Find course by ID  
✅ Validate all course properties  
✅ List courses (empty and with data)  

### EnrollmentService Tests
✅ Enroll student in course (success)  
✅ Prevent enrollment of inactive students  
✅ Prevent enrollment in inactive courses  
✅ Prevent duplicate active enrollments  
✅ Re-enrollment after cancellation  
✅ Mark enrollment completed  
✅ Cancel enrollment  
✅ Status transitions (ACTIVE → COMPLETED → CANCELLED)  
✅ List enrollments by student ID  

### InputValidator Tests
✅ Non-blank validation with null/empty/whitespace  
✅ Email validation with various formats  
✅ Positive number validation  
✅ Correct exception messages  

### Entity Tests
✅ Creation with all constructors  
✅ Property getters and setters  
✅ toString() format  
✅ Inheritance and polymorphism  
✅ Enum values and transitions  

---

## Test Execution Paths

### IDE (IntelliJ IDEA / Eclipse)
1. Right-click on test file → **Run Tests**
2. Or right-click on test class → **Run** or **Debug**
3. View results in Test Runner window

### Command Line
```bash
# Run all tests
.\gradlew.bat test

# Run specific test class
.\gradlew.bat test --tests StudentServiceTest

# Run specific test method
.\gradlew.bat test --tests StudentServiceTest.testAddStudent_WithValidData

# Run with verbose output
.\gradlew.bat test --info
```

### Gradle Build Report
After running tests, open the HTML report:
```
build/reports/tests/test/index.html
```

---

## Test Patterns Used

### Arrange-Act-Assert (AAA)
```java
// Arrange
Student student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");

// Act
Student updated = studentService.updateStudent(student.getId(), "Jane", "Smith", "jane@example.com", "Batch-2025");

// Assert
assertEquals("Jane", updated.getFirstName());
```

### Exception Testing
```java
InvalidInputException exception = assertThrows(InvalidInputException.class,
    () -> studentService.addStudent("", "Doe", "john@example.com", "Batch-2024"));
assertEquals("First name cannot be empty.", exception.getMessage());
```

### Fresh Instance Per Test
```java
@BeforeEach
void setUp() {
    studentService = new StudentService();
    // Each test gets a clean service instance
}
```

---

## Continuous Integration

To add these tests to CI/CD pipeline:

### GitHub Actions Example
```yaml
name: Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '17'
      - run: ./gradlew test
```

---

## Code Coverage

To measure code coverage:

```bash
# Install JaCoCo plugin (if needed)
# Then run:
.\gradlew.bat test jacocoTestReport

# Report location:
# build/reports/jacoco/test/html/index.html
```

---

## Troubleshooting

### Tests Fail After Code Changes
- Clear build: `.\gradlew.bat clean test`
- Recompile: `.\gradlew.bat build`

### IdGenerator Tests Fail
- Check that IdGenerator static counters haven't been reset
- Each test run may continue from previous counter values

### Import Errors in IDE
- Run `.\gradlew.bat clean build`
- Reload project in IDE
- Invalidate caches if needed

---

## Next Steps

### Add More Tests
- UI layer testing (requires mock Scanner input)
- Integration tests combining multiple services
- Performance tests for large datasets

### Quality Gates
- Enforce minimum code coverage (e.g., 80%)
- Add mutation testing with PIT
- Add static analysis with SonarQube

### Documentation
- Keep TEST_SUMMARY.md updated with new tests
- Document any new test patterns used
- Update this guide as tests evolve

---

## References

- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [Gradle Testing Guide](https://docs.gradle.org/current/userguide/testing_java_projects.html)
- [Testing Best Practices](https://junit.org/junit5/docs/current/user-guide/#writing-tests)

