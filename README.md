# Weather App

This is an Android application that allows users to fetch weather data for a given date and location using a free weather API. The app also provides the functionality to store this information in a local database for offline access.

## Features

- Fetch weather data from a free weather API based on user input (date and location).
- Display maximum and minimum temperature for the given date.
- Store weather data in a local database for offline access.
- Handle future dates by providing the average temperature for the last 10 available years.
- Follows MVVM architecture for clean and organized code.

## Libraries Used

- Retrofit: For making network requests and handling API responses.
- Room Persistence Library: For local database storage and retrieval.
- Kotlin Coroutines: For asynchronous programming and background tasks.
- LiveData & ViewModel: For observing and managing UI-related data in a lifecycle-aware manner.
- Gson: For JSON serialization and deserialization.

## Implementation Details

The app follows the MVVM (Model-View-ViewModel) architecture pattern:

- **Model**: Contains data classes for API responses (`WeatherResponse`) and database entities (`WeatherEntity`). Also includes a repository class (`WeatherRepository`) to manage data operations.
- **View**: Consists of activities and layout files responsible for presenting data to the user and handling user interactions.
- **ViewModel**: Acts as a bridge between the View and the Model, containing business logic and maintaining UI-related data.

The application consists of the following components:

- **API Package**: Contains Retrofit setup (`RetrofitInstance`) and service interface (`WeatherService`) for making API requests.
- **Database Package**: Includes Room database setup (`WeatherDatabase`), data access object (`WeatherDao`), and database entities (`WeatherEntity`, `AverageWeather`).
- **Repository Package**: Contains the `WeatherRepository` class responsible for coordinating data operations between the network and the local database.
- **ViewModel Package**: Contains the `WeatherViewModel` class that interacts with the repository and provides data to the UI components.
- **UI Components**: Main activity (`MainActivity`) responsible for handling user input and displaying weather information.

## Setup Instructions

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the application on an emulator or a physical device.

## Usage

1. Launch the app on your device.
2. Enter the desired date (YYYY-MM-DD) and location.
3. Click the "Search" button to fetch weather data.
4. View the maximum and minimum temperature for the specified date.

## Contributors

- Ujjwal Godara - Developer


