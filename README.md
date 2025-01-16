# Android Calculator

An Android calculator developed in Kotlin.
(my first kotlin project)

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Setup](#setup)
- [Usage](#usage)
- [Dependencies](#dependencies)
- [Build](#build)

## Overview

Calculator application with an intuitive user interface, supporting basic arithmetic operations and decimal number handling.

## Features

- Basic operations (+, -, ×, ÷)
- Percentage calculation
- Decimal numbers (comma separator)
- Character-by-character deletion
- Input validation
- Adaptive number formatting

## Prerequisites

- Android Studio (version 2023.1.1 or higher)
- JDK 17 or higher
- Android SDK minimum: API 24 (Android 7.0)
- Android SDK target: API 34 (Android 14)

## Installation

1. Clone the repository
```bash
git clone https://github.com/your-username/calculator.git
```

2. Open Android Studio
3. Select "Open an existing project"
4. Navigate to the cloned folder and select the project

## Setup

### Emulator Setup

1. Open "Device Manager" in Android Studio
2. Click "Create Device"
3. Select a device (e.g., Pixel 6)
4. Choose an Android system image (recommended: API 34)
5. Finish setup with default settings

### Gradle Configuration

The project uses Kotlin DSL and Version Catalogs for dependency management. Main dependencies are defined in `gradle/libs.versions.toml`.

Key dependencies versions:
```toml
core-ktx = "1.12.0"
appcompat = "1.6.1"
material = "1.11.0"
exp4j = "0.4.8"
```

## Usage

1. Launch Android Studio
2. Select the configured emulator
3. Click "Run" (green button ▶️)
4. The application will automatically launch on the emulator

## Dependencies

- AndroidX Core KTX - Core Kotlin extensions
- AndroidX AppCompat - Backward compatibility support
- Material Design Components - UI elements
- ConstraintLayout - Flexible layouts
- exp4j - Mathematical expression evaluation

## Build

### Debug APK

1. Using Android Studio:
    - Go to `Build` > `Build Bundle(s) / APK(s)` > `Build APK(s)`
    - Click on `locate` in the popup to find the generated APK

2. Using Terminal:
```bash
./gradlew assembleDebug
```

The debug APK will be generated at:
```
app/build/outputs/apk/debug/app-debug.apk
```

### Release APK

1. Using Android Studio:
    - Go to `Build` > `Generate Signed Bundle / APK`
    - Choose `APK`
    - Fill in the keystore information or create a new keystore
    - Select release build variant
    - Click `Finish`

2. Using Terminal (requires signing configuration in build.gradle.kts):
```bash
./gradlew assembleRelease
```

The release APK will be generated at:
```
app/build/outputs/apk/release/app-release.apk
```

## Project Structure

```
app/
├── build.gradle.kts
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.calculator/
│   │   │       └── MainActivity.kt
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml
│   │   │   └── values/
│   │   │   │   └── styles.xml
│   │   └── AndroidManifest.xml
├── gradle/
│   └── libs.versions.toml
└── settings.gradle.kts
```