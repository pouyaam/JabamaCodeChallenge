# GitHub Repositories Search App

This Android application allows users to search for GitHub repositories using the GitHub API. The app is built with **MVVM architecture**, follows the **Repository pattern**, and uses **Koin** for Dependency Injection.

## Features

- Search GitHub repositories by name.
- Display repository details, including name, description, and more.
- MVVM Architecture.
- Clean separation of concerns.
- Dependency Injection using Koin.

## Tech Stack

- **Kotlin**: The main programming language.
- **MVVM Architecture**: Clean separation between UI, ViewModel, and business logic.
- **Koin**: Dependency Injection framework.
- **Retrofit**: For making HTTP requests to the GitHub API.
- **LiveData**: For observing data changes in the ViewModel.
- **View Binding**: For interacting with UI components.
- **Coroutines**: For handling background tasks.
- **Material Design**: For the user interface.

## Setup and Installation

1. Clone this repository:
    ```bash
    git clone https://github.com/iamomidk/JabamaCodeChallenge.git
    ```

2. Open the project in Android Studio.

3. Build the project:
    ```bash
    ./gradlew build
    ```

4. Obtain a GitHub personal access token:
    - Go to your [GitHub Developer Settings](https://github.com/settings/tokens).
    - Create a new token with the required permissions.

5. Run the project on an Android device or emulator.

## Project Structure

```bash
├── data
│   ├── model
│   ├── repository
│   ├── api
├── domain
│   ├── model
│   ├── repository
│   └── usecase
├── ui
│   ├── adapter
│   └── viewmodel
├── di
└── utils
```
