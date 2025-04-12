# Order App

This is a Kotlin-based Android application for managing orders, built using Jetpack Compose and Hilt for dependency injection.

## Prerequisites

Before running the project, ensure you have the following installed:

- **Android Studio**: Version 2024.3.1 (or later)
- **JDK**: Version 11 or higher
- **Gradle**: Included with Android Studio
- **Android Device/Emulator**: Running Android 8.0 (API level 26) or higher

## Setup Instructions

### 1. Clone the Repository
Clone the repository to your local machine:
```bash
git clone https://github.com/nazarabdullah-dev/order_mn.git
cd order_mn
```

### 2. Open the Project
Open Android Studio and select "Open an existing Android Studio project". Navigate to the cloned repository and open it.
### 3. Sync Gradle
Once the project is open, Android Studio will prompt you to sync Gradle. Click "Sync Now" to download the necessary dependencies.
### 4. Build the Project
Build the project by selecting "Build" > "Rebuild Project/Assemble project" from the menu. This will compile the code and ensure everything is set up correctly.
### 5. Run the Application
Connect your Android device or start an emulator. Click the "Run" button in Android Studio to install and run the application on your device/emulator.


## Project Structure

The project is organized into the following modules and packages:

### 1. **Domain Layer**
- **Purpose**: Contains the core business logic and models.
- **Key Components**:
    - `model`: Defines data models such as `Item` and `Package`.
    - `repository`: Interfaces for data operations, ensuring separation of concerns.
    - `usecase`: Contains use cases that encapsulate business logic (e.g., `GetItemsUseCase`, `PlaceOrderUseCase`).

### 2. **Data Layer**
- **Purpose**: Handles data sources (e.g., local database, remote APIs).
- **Key Components**:
    - `repository`: Implements the domain layer's repository interfaces.
    - `datasource`: Manages data fetching and storage (e.g., API calls, database operations).

### 3. **UI Layer**
- **Purpose**: Manages the presentation logic and user interface.
- **Key Components**:
    - `component`: Reusable UI components (e.g., `ItemListContainer`, `FooterContainer`).
    - `order`: Screens and state management for the order flow (e.g., `OrderScreen`, `OrderViewModel`).
    - `theme`: Defines the app's visual style using Material Design 3.

### 4. **Common Utilities**
- **Purpose**: Provides shared utilities and extensions.
- **Key Components**:
    - `util`: Includes helper functions (e.g., `shimmerBrush` for loading animations).

---

## Technologies Used and Their Purpose

### 1. **Kotlin**
- **Why**: Kotlin is a modern, concise, and safe programming language for Android development. It improves code readability and reduces boilerplate.

### 2. **Jetpack Compose**
- **Why**: Compose simplifies UI development with a declarative approach. It allows building dynamic and reusable UI components efficiently.

### 3. **Hilt (Dependency Injection)**
- **Why**: Hilt simplifies dependency injection, making the codebase more modular, testable, and maintainable.

### 4. **StateFlow**
- **Why**: StateFlow is used for reactive state management, ensuring a seamless flow of data between the ViewModel and UI.

### 5. **Material Design 3**
- **Why**: Provides a modern and consistent design language, ensuring a polished and user-friendly interface.

### 6. **Coroutines**
- **Why**: Coroutines enable asynchronous programming, ensuring smooth and responsive user experiences.

---

## Benefits of the Chosen Architecture

1. **Separation of Concerns**: The layered architecture ensures that each layer has a specific responsibility, making the codebase easier to maintain and extend.
2. **Reusability**: Reusable components and utilities reduce duplication and improve consistency.
3. **Scalability**: The modular structure allows for easy addition of new features without affecting existing functionality.
4. **Testability**: The use of Hilt and separation of business logic into the domain layer makes the codebase highly testable.

---

## Conclusion

The **Order App** is built with a modern Android development stack, leveraging the latest tools and best practices to ensure a robust, maintainable, and user-friendly application.
