# Smart Pantry Manager

A Java Android application that suggests recipes based strictly on leftover ingredients to cut food waste.

## Project Overview

Smart Pantry Manager helps users reduce food waste by tracking the ingredients they have at home (their "pantry") and suggesting recipes they can cook using strictly those leftover ingredients. The app enforces a strict-matching rule: a recipe is only suggested if the user has every single ingredient it requires in their pantry.

## Features

- **Pantry Management**: Add, edit, and delete pantry items with name, quantity, unit, and optional expiry date
- **Pantry List Screen**: View all ingredients using a RecyclerView with custom adapter
- **Recipe Collection**: 20 pre-loaded recipes stored in SQLite database
- **Suggested Recipes**: Strict-matching logic shows only recipes you can make right now
- **Recipe Details**: View full ingredient list and preparation method for each recipe
- **Settings Screen**: Toggle for expiry alerts and unit preference (metric/imperial)
- **Empty State Feedback**: Clear message when no recipes match current pantry

## Technical Details

### Database Choice: SQLite

This application uses SQLite with SQLiteOpenHelper for local data persistence. SQLite was chosen because:
- Built into Android - no external dependencies required
- Perfect for this use case - lightweight, local-only data storage
- Consistent with module curriculum on persistent data storage
- Reliable and widely used in Android development

### Database Schema

**Pantry Items Table:**
- id (INTEGER PRIMARY KEY AUTOINCREMENT)
- name (TEXT NOT NULL)
- quantity (REAL NOT NULL)
- unit (TEXT NOT NULL)
- expiry_date (TEXT)

**Recipes Table:**
- id (INTEGER PRIMARY KEY AUTOINCREMENT)
- name (TEXT NOT NULL)
- ingredients (TEXT NOT NULL)
- method (TEXT NOT NULL)

### Strict-Matching Algorithm

The app implements a robust matching algorithm that:
- Normalizes ingredient names (lowercase, removes trailing 's' for plurals)
- Checks if ALL required ingredients are present in pantry
- Uses fuzzy matching to handle slight variations (e.g., "tomato" matches "tomatoes")
- Only displays recipes where every ingredient is available

### Screens

1. **Main Activity**: Home screen with navigation to Pantry, Suggested Recipes, and Settings
2. **Pantry List Activity**: Display all pantry items with edit/delete options
3. **Add/Edit Ingredient Activity**: Form with validation for adding or editing ingredients
4. **Suggested Recipes Activity**: List of recipes matching current pantry (strict-matching)
5. **Recipe Detail Activity**: Full recipe with ingredients and method
6. **Settings Activity**: User preferences for alerts and units

## Setup Instructions

### Prerequisites
- Android Studio (latest version recommended)
- JDK 8 or higher
- Android SDK (API level 21 or higher)

### Running the Project

1. Clone this repository:
   ```bash
   git clone <repository-url>
   cd SmartPantryManager
   ```

2. Open the project in Android Studio:
   - File → Open → Select the SmartPantryManager folder

3. Sync Gradle files:
   - Android Studio will prompt to sync Gradle
   - Click "Sync Now"

4. Run the app:
   - Connect an Android device or start an emulator
   - Click the Run button (green triangle) in Android Studio
   - Or use: `./gradlew installDebug`

### Building APK

To build a release APK:
```bash
./gradlew assembleRelease
```

The APK will be located at: `app/build/outputs/apk/release/app-release.apk`

## App Screenshots

*(Add screenshots of your running app here after testing)*

## Code Structure

```
app/src/main/java/com/example/smartpantrymanager/
├── MainActivity.java                    # Home screen with navigation
├── PantryListActivity.java              # Pantry list with RecyclerView
├── AddEditIngredientActivity.java      # Add/edit ingredient form
├── SuggestedRecipesActivity.java       # Recipe suggestions
├── RecipeDetailActivity.java           # Recipe details view
├── SettingsActivity.java               # User settings
├── DatabaseHelper.java                 # SQLite database operations
├── PantryItem.java                      # Pantry item model
├── Recipe.java                         # Recipe model
├── PantryAdapter.java                  # RecyclerView adapter for pantry
└── RecipeAdapter.java                  # RecyclerView adapter for recipes
```

## Key Implementation Details

### RecyclerView Implementation
- Custom adapters for both pantry items and recipes
- ViewHolder pattern for efficient scrolling
- Click listeners for edit/delete actions

### Database Operations
- Full CRUD (Create, Read, Update, Delete) for pantry items
- Read-only for recipes (pre-seeded)
- Data persists between app sessions

### Navigation
- Toolbar with back button on all screens except main
- Intents used to pass data between activities
- Activity lifecycle properly managed

### Input Validation
- Required fields checked before saving
- Numeric validation for quantity
- Toast messages for user feedback

## Testing

Test the following scenarios:
1. Add ingredients to pantry
2. Edit existing ingredient
3. Delete ingredient
4. View suggested recipes with empty pantry (should show empty message)
5. Add ingredients that match a recipe (recipe should appear)
6. Remove an ingredient (recipe should disappear)
7. View recipe details
8. Change settings
9. Close and reopen app (data should persist)

## Known Limitations

- Expiry date is optional and not used for automatic alerts (UI toggle only)
- Recipe matching is case-insensitive but doesn't handle all spelling variations
- No cloud sync - data is local only
- No ingredient quantity checking (presence/absence only)

## Future Improvements

If given more time, I would:
- Implement actual expiry date notifications using AlarmManager
- Add recipe rating/favorites system
- Implement quantity-aware matching (check if user has enough of each ingredient)
- Add barcode scanning for ingredient entry
- Create a shopping list for missing ingredients
- Add backup/restore functionality

## Author

[Your Name]
[Student Number]
Mobile App Development 700

## License

This project is for educational purposes only.
