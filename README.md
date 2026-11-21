# Dictionary KMP & CMP App

A modern cross-platform Dictionary application built with **Kotlin Multiplatform (KMP)** and **Compose Multiplatform (CMP)** targeting **Android**, **iOS**, and **Desktop (JVM)**.

---

## 🚀 Features

- 🔍 **Real-Time Word Search**: Instant dictionary lookup using the Free Dictionary API.
- ⚡ **Debounced Search Input**: Optimized search requests using Kotlin Coroutines Flow (`debounce(500ms)`).
- 🎨 **Shared Compose Multiplatform UI**: Single codebase powering the UI across Android, Desktop, and iOS.
- 🧩 **Clean Architecture & DI**: Modular presentation, domain, and data layers wired together with **Koin**.
- 🌐 **Robust Networking**: Cross-platform networking using **Ktor 3** with JSON content negotiation and Kermit logging.

---

## 🛠 Tech Stack & Libraries

- **Language**: Kotlin `2.4.21` (Modern multiplatform language)
- **UI Framework**: Compose Multiplatform `1.12.2` (Declarative UI for Android, iOS & Desktop)
- **Architecture**: Clean Architecture (Presentation, Domain, Data)
- **Dependency Injection**: Koin `4.2.2` (Pragmatic lightweight DI framework for KMP)
- **Networking**: Ktor Client `3.6.1` (Asynchronous multiplatform HTTP client)
- **HTTP Engines**: OkHttp (Android/JVM), Darwin (iOS)
- **Serialization**: `kotlinx.serialization` `1.12.0` (Multiplatform JSON parsing)
- **Async & Reactive**: Kotlinx Coroutines & Flow `1.11.0` (Asynchronous state & stream handling)
- **Logging**: Kermit `2.2.0` (Multiplatform logging framework)
- **Design System**: Material 3 `1.12.0-alpha03` (Modern Material design components)

---

## 🏗 Architecture & Project Structure

The project follows **Clean Architecture** principles to separate concerns into testable layers inside the shared module:

```
Dictionary_KMP_CMP_App/
├── androidApp/                 # Android application entry point (ComponentActivity, SetContent)
├── desktopApp/                 # Desktop JVM application entry point (Compose Window)
├── iosApp/                     # iOS SwiftUI wrapper & Xcode project
└── shared/                     # Multiplatform shared core logic & UI
    └── src/
        ├── commonMain/
        │   └── kotlin/com/example/dictionary_kmp_cmp_app/
        │       ├── App.kt      # Main Compose entry composable
        │       ├── data/       # Data Layer: Repositories, DTOs, Ktor Client configuration
        │       │   ├── dto/
        │       │   ├── ktor/
        │       │   └── repository/
        │       ├── di/         # Dependency Injection (Koin shared modules)
        │       ├── domain/     # Domain Layer: Use cases, Interfaces, Models
        │       │   ├── models/
        │       │   ├── repository/
        │       │   └── usecase/
        │       └── presentation/# Presentation Layer: ViewModels, Composables, Screen state
        │           ├── common/
        │           └── screens/
        ├── androidMain/        # Android-specific implementations (e.g. OkHttp, PlatformText)
        ├── jvmMain/            # Desktop JVM-specific implementations (e.g. OkHttp)
        └── iosMain/            # iOS-specific implementations (e.g. Darwin engine, MainViewController)
```

### Layer Responsibilities

1. **Presentation Layer**:
   - `WordInfoScreenViewModel`: Manages search query state, executes `GetWordInfoUseCase`, handles debouncing, and exposes state via `StateFlow`.
   - `WordInfoScreenRoot` & `WordInfoScreen`: Compose UI rendering loading states, definitions, and errors.
2. **Domain Layer**:
   - `Word`, `Meaning`, `Definition`: Business entity models.
   - `Dictionary`: Domain repository contract.
   - `GetWordInfoUseCase`: Encapsulates business logic for retrieving word information.
3. **Data Layer**:
   - `DictionaryRepositoryImpl`: Implements `Dictionary` interface, executes network calls using Ktor `HttpClient`, maps DTOs to domain models.
   - `createHttpClient()`: Configures Ktor with JSON serialization and logging plugins.

---

## 🔧 Dependency Injection (Koin)

Dependencies are wired in `KoinModules.kt` via `sharedModule`:

```kotlin
val sharedModule = module {
    single<HttpClient> { createHttpClient() }
    single { DictionaryRepositoryImpl(get()) }.bind<Dictionary>()
    single { GetWordInfoUseCase(get()) }
    viewModel { WordInfoScreenViewModel(get()) }
}
```

Koin is initialized on app startup across all platforms via `initKoin()`.

---

## ⚙️ How to Build and Run

### Prerequisites

- **JDK 17+**
- **Android Studio** (Ladybug or newer recommended with KMP plugin)
- **Xcode** (for iOS builds, macOS only)

### Running Commands

#### Android
```bash
./gradlew :androidApp:assembleDebug
```
Or run `:androidApp` directly from Android Studio.

#### Desktop (JVM)
- **Standard Run**:
  ```bash
  ./gradlew :desktopApp:run
  ```
- **Hot Reload**:
  ```bash
  ./gradlew :desktopApp:hotRun --auto
  ```

#### iOS
1. Open the `./iosApp` folder in **Xcode**.
2. Select a target iOS simulator or device and press **Run**.

---

## 🧪 Running Tests

Unit tests are located in `shared/src/commonTest`, `shared/src/jvmTest`, and `shared/src/androidHostTest`:

```bash
# Desktop JVM tests
./gradlew :shared:jvmTest

# Android Host unit tests
./gradlew :shared:testAndroidHostTest

# iOS Simulator tests (macOS required)
./gradlew :shared:iosSimulatorArm64Test
```
