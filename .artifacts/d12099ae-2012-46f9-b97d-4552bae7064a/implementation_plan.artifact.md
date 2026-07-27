# Implementation Plan - Fix and Improve Dictionary UI

The user reported that the design "is not coming out right" (dizyni chimayapti). The primary issues are build-breaking syntax errors and incomplete UI elements.

## User Review Required

> [!IMPORTANT]
> The package name and theme references in `MainActivity.kt` are currently incorrect and preventing the app from building. I will align them with the project structure (`com.example.dasturrrrrr`).

## Proposed Changes

### UI Core
#### [MODIFY] [MainActivity.kt](file:///C:/Users/Lenova/AndroidStudioProjects/dasturrrrrr2/app/src/main/java/com/example/dasturrrrrr/MainActivity.kt)
- **Fix Package & Imports**: Change package to `com.example.dasturrrrrr` and fix the theme import.
- **Theme Alignment**: Replace `DictionaryuiTheme` and `dacturTheme` with the correct `DasturrrrrrTheme`.
- **Content Fixes**:
    - Populate the "Bugungi so'z" (Today's Word) card with data.
    - Improve search bar aesthetics.
- **Visual Refinement**:
    - Adjust `NavigationBar` item labels.
    - Improve card elevations and padding.
    - Fix the `Category` grid implementation to be more robust.

## Verification Plan

### Automated Tests
- Run `./gradlew assembleDebug` to ensure the project builds correctly after package fixes.

### Manual Verification
- Render the `HomeScreenPreview` to verify the visual changes.
