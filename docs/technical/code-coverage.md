# Code Coverage Documentation

## Overview

This document describes the code coverage setup and guidelines for the GithubCruise Android project.

## Coverage Tool

We use **JaCoCo** (Java Code Coverage) for measuring code coverage. JaCoCo is a free code coverage library for Java that integrates seamlessly with Android projects.

## Current Coverage

**Overall Coverage: 9%**

Coverage breakdown by module:
- Repository layer: 77-79% (Good coverage)
- Use cases: 70% (Good coverage)
- Domain models: 65% (Good coverage)
- ViewModels: 21-25% (Needs improvement)
- UI/Compose: 0-9% (Needs significant improvement)

## Running Coverage Reports

### Command Line

Generate coverage reports using Gradle:

```bash
# Run tests with coverage
./gradlew testDebugUnitTest jacocoTestReport

# Clean and run fresh coverage
./gradlew clean testDebugUnitTest jacocoTestReport
```

### View Reports

After running the coverage task, reports are generated in multiple formats:

- **HTML Report**: `app/build/reports/jacoco/jacocoTestReport/html/index.html`
- **XML Report**: `app/build/reports/jacoco/jacocoTestReport/jacocoTestReport.xml`

Open the HTML report in your browser for an interactive view:

```bash
open app/build/reports/jacoco/jacocoTestReport/html/index.html
```

## Coverage Configuration

### Excluded Classes

The following classes are excluded from coverage reports as they are generated code or UI boilerplate:

- `*Fragment` - Android Fragment classes
- `*Activity` - Android Activity classes
- `*.databinding.*` - Data binding generated classes
- `*.BuildConfig` - Build configuration
- `*_Factory` - Dagger/Hilt factories
- `*_HiltModules*` - Hilt modules
- `*Hilt_*` - Hilt generated classes
- `*ComposableSingletons*` - Compose generated classes
- `*_Impl` - Room database implementations
- `*Module_*` - Dagger modules

### Excluded Packages

- `*.di` - Dependency injection modules
- `dagger.hilt.*` - Hilt framework
- `hilt_aggregated_deps` - Hilt generated dependencies

## Coverage Goals

### Short-term Goals (3 months)
- Increase overall coverage to **30%**
- Focus on:
  - Repository layer: Maintain 75%+
  - Use cases: Maintain 70%+
  - ViewModels: Increase to 50%+

### Medium-term Goals (6 months)
- Increase overall coverage to **50%**
- Add UI/integration tests for critical flows
- Implement screenshot testing for UI components

### Long-term Goals (12 months)
- Achieve **80%** overall coverage
- Full coverage for business logic
- Comprehensive UI testing suite

## Writing Tests

### Unit Tests Location
- Repository tests: `app/src/test/java/.../repository/`
- UseCase tests: `app/src/test/java/.../domain/usecase/`
- ViewModel tests: `app/src/test/java/.../ui/features/`

### Test Naming Convention
```kotlin
class UserRepositoryImplTest {
    @Test
    fun `getUsers should return success when API call succeeds`() {
        // Test implementation
    }

    @Test
    fun `getUsers should return error when API call fails`() {
        // Test implementation
    }
}
```

### Testing Tools
- **JUnit 4**: Test framework
- **MockK**: Mocking library for Kotlin
- **Coroutines Test**: Testing async code

## Best Practices

1. **Write tests first**: Follow TDD principles for new features
2. **Test behavior, not implementation**: Focus on what the code does, not how
3. **Keep tests isolated**: Each test should be independent
4. **Use descriptive names**: Test names should clearly describe what they test
5. **Test edge cases**: Include tests for error conditions and boundary cases
6. **Mock external dependencies**: Use MockK for API, database, and other external dependencies

## CI/CD Integration

Coverage reports can be integrated with CI/CD pipelines:

```bash
# Generate XML report for CI tools
./gradlew testDebugUnitTest jacocoTestReport

# Coverage report is at: app/build/reports/jacoco/jacocoTestReport/jacocoTestReport.xml
```

Popular CI integration tools:
- **Codecov**: Upload XML report for detailed coverage tracking
- **SonarQube**: Static analysis with coverage integration
- **GitHub Actions**: Add coverage badges to README

## Troubleshooting

### No coverage data generated
- Ensure tests are actually running: `./gradlew testDebugUnitTest`
- Check that `enableUnitTestCoverage = true` is set in build.gradle.kts
- Verify JaCoCo plugin is applied

### Low coverage numbers
- Check excluded classes/packages configuration
- Ensure tests are in correct source set (`src/test/`)
- Verify test execution with `--info` flag

### Build errors
- Clean build directory: `./gradlew clean`
- Invalidate caches in Android Studio
- Sync project with Gradle files

## Resources

- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
- [Android Testing Guide](https://developer.android.com/training/testing)
- [MockK Documentation](https://mockk.io/)
- [Kotlin Coroutines Test](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/)
