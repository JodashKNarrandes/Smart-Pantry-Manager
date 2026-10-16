package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SmartPantry.db";
    private static final int DATABASE_VERSION = 1;

    // Pantry Items Table
    private static final String TABLE_PANTRY = "pantry_items";
    private static final String COLUMN_PANTRY_ID = "id";
    private static final String COLUMN_PANTRY_NAME = "name";
    private static final String COLUMN_PANTRY_QUANTITY = "quantity";
    private static final String COLUMN_PANTRY_UNIT = "unit";
    private static final String COLUMN_PANTRY_EXPIRY = "expiry_date";

    // Recipes Table
    private static final String TABLE_RECIPES = "recipes";
    private static final String COLUMN_RECIPE_ID = "id";
    private static final String COLUMN_RECIPE_NAME = "name";
    private static final String COLUMN_RECIPE_INGREDIENTS = "ingredients";
    private static final String COLUMN_RECIPE_METHOD = "method";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Pantry Items table
        String CREATE_PANTRY_TABLE = "CREATE TABLE " + TABLE_PANTRY + "("
                + COLUMN_PANTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PANTRY_NAME + " TEXT NOT NULL,"
                + COLUMN_PANTRY_QUANTITY + " REAL NOT NULL,"
                + COLUMN_PANTRY_UNIT + " TEXT NOT NULL,"
                + COLUMN_PANTRY_EXPIRY + " TEXT"
                + ")";
        db.execSQL(CREATE_PANTRY_TABLE);

        // Create Recipes table
        String CREATE_RECIPES_TABLE = "CREATE TABLE " + TABLE_RECIPES + "("
                + COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_RECIPE_NAME + " TEXT NOT NULL,"
                + COLUMN_RECIPE_INGREDIENTS + " TEXT NOT NULL,"
                + COLUMN_RECIPE_METHOD + " TEXT NOT NULL"
                + ")";
        db.execSQL(CREATE_RECIPES_TABLE);

        // Seed recipes
        seedRecipes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    private void seedRecipes(SQLiteDatabase db) {
        String[][] recipes = {
            {"Tomato Pasta", "tomato, pasta, garlic, olive oil, salt, pepper", "1. Boil pasta in salted water for 10 minutes. 2. Sauté garlic in olive oil. 3. Add chopped tomatoes and simmer. 4. Mix with pasta and season."},
            {"Eggs and Toast", "eggs, bread, butter, salt", "1. Toast bread. 2. Fry eggs in butter. 3. Season with salt. 4. Serve together."},
            {"Banana Smoothie", "banana, milk, honey", "1. Blend banana with milk. 2. Add honey to taste. 3. Serve cold."},
            {"Grilled Cheese Sandwich", "bread, cheese, butter", "1. Butter bread slices. 2. Place cheese between bread. 3. Grill until golden and cheese melts."},
            {"Rice and Beans", "rice, beans, onion, garlic, salt", "1. Cook rice. 2. Sauté onion and garlic. 3. Add beans and cook. 4. Serve over rice."},
            {"Oatmeal", "oats, milk, sugar, cinnamon", "1. Boil milk with oats. 2. Add sugar and cinnamon. 3. Simmer until thick."},
            {"Potato Salad", "potato, mayonnaise, mustard, salt, pepper", "1. Boil and cube potatoes. 2. Mix with mayonnaise and mustard. 3. Season and chill."},
            {"Scrambled Eggs", "eggs, butter, milk, salt", "1. Whisk eggs with milk. 2. Scramble in butter. 3. Season with salt."},
            {"Peanut Butter Toast", "bread, peanut butter", "1. Toast bread. 2. Spread peanut butter. 3. Serve."},
            {"Vegetable Stir Fry", "carrot, broccoli, soy sauce, garlic, oil", "1. Sauté garlic in oil. 2. Add vegetables. 3. Stir fry with soy sauce."},
            {"Fruit Salad", "apple, banana, orange, grapes", "1. Chop all fruits. 2. Mix together. 3. Serve fresh."},
            {"Cheese Omelette", "eggs, cheese, butter, salt", "1. Beat eggs with salt. 2. Cook in butter. 3. Add cheese and fold."},
            {"Mashed Potatoes", "potato, butter, milk, salt", "1. Boil potatoes. 2. Mash with butter and milk. 3. Season with salt."},
            {"Cereal with Milk", "cereal, milk, sugar", "1. Pour cereal in bowl. 2. Add milk. 3. Add sugar if desired."},
            {"Tomato Soup", "tomato, onion, garlic, water, salt", "1. Sauté onion and garlic. 2. Add tomatoes and water. 3. Simmer and blend. 4. Season."},
            {"Baked Potato", "potato, butter, salt, pepper", "1. Wash and prick potato. 2. Bake at 200°C for 1 hour. 3. Top with butter and season."},
            {"Quesadilla", "tortilla, cheese, butter", "1. Place cheese on tortilla. 2. Fold and cook in butter. 3. Serve hot."},
            {"Garlic Bread", "bread, butter, garlic", "1. Mix butter with minced garlic. 2. Spread on bread. 3. Toast until golden."},
            {"Yogurt Parfait", "yogurt, granola, honey, berries", "1. Layer yogurt with granola. 2. Add berries. 3. Drizzle with honey."},
            {"Rice Pudding", "rice, milk, sugar, cinnamon", "1. Cook rice in milk. 2. Add sugar and cinnamon. 3. Simmer until creamy."}
        };

        for (String[] recipe : recipes) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_RECIPE_NAME, recipe[0]);
            values.put(COLUMN_RECIPE_INGREDIENTS, recipe[1]);
            values.put(COLUMN_RECIPE_METHOD, recipe[2]);
            db.insert(TABLE_RECIPES, null, values);
        }
    }

    // Pantry CRUD Operations
    public long addPantryItem(PantryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PANTRY_NAME, normalizeIngredientName(item.getName()));
        values.put(COLUMN_PANTRY_QUANTITY, item.getQuantity());
        values.put(COLUMN_PANTRY_UNIT, item.getUnit());
        values.put(COLUMN_PANTRY_EXPIRY, item.getExpiryDate());
        long id = db.insert(TABLE_PANTRY, null, values);
        db.close();
        return id;
    }

    public List<PantryItem> getAllPantryItems() {
        List<PantryItem> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PANTRY, null, null, null, null, null, COLUMN_PANTRY_NAME + " ASC");

        if (cursor.moveToFirst()) {
            do {
                PantryItem item = new PantryItem();
                item.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PANTRY_ID)));
                item.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PANTRY_NAME)));
                item.setQuantity(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PANTRY_QUANTITY)));
                item.setUnit(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PANTRY_UNIT)));
                item.setExpiryDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PANTRY_EXPIRY)));
                items.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return items;
    }

    public PantryItem getPantryItem(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PANTRY, null, COLUMN_PANTRY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);

        PantryItem item = null;
        if (cursor.moveToFirst()) {
            item = new PantryItem();
            item.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PANTRY_ID)));
            item.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PANTRY_NAME)));
            item.setQuantity(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PANTRY_QUANTITY)));
            item.setUnit(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PANTRY_UNIT)));
            item.setExpiryDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PANTRY_EXPIRY)));
        }
        cursor.close();
        db.close();
        return item;
    }

    public int updatePantryItem(PantryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PANTRY_NAME, normalizeIngredientName(item.getName()));
        values.put(COLUMN_PANTRY_QUANTITY, item.getQuantity());
        values.put(COLUMN_PANTRY_UNIT, item.getUnit());
        values.put(COLUMN_PANTRY_EXPIRY, item.getExpiryDate());

        int rows = db.update(TABLE_PANTRY, values, COLUMN_PANTRY_ID + "=?",
                new String[]{String.valueOf(item.getId())});
        db.close();
        return rows;
    }

    public void deletePantryItem(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PANTRY, COLUMN_PANTRY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Recipe Operations
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RECIPES, null, null, null, null, null, COLUMN_RECIPE_NAME + " ASC");

        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_RECIPE_ID)));
                recipe.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RECIPE_NAME)));
                recipe.setIngredients(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RECIPE_INGREDIENTS)));
                recipe.setMethod(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RECIPE_METHOD)));
                recipes.add(recipe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recipes;
    }

    public Recipe getRecipe(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RECIPES, null, COLUMN_RECIPE_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);

        Recipe recipe = null;
        if (cursor.moveToFirst()) {
            recipe = new Recipe();
            recipe.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_RECIPE_ID)));
            recipe.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RECIPE_NAME)));
            recipe.setIngredients(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RECIPE_INGREDIENTS)));
            recipe.setMethod(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RECIPE_METHOD)));
        }
        cursor.close();
        db.close();
        return recipe;
    }

    // Strict Matching Logic
    public List<Recipe> getMatchingRecipes(List<PantryItem> pantryItems) {
        List<Recipe> allRecipes = getAllRecipes();
        List<Recipe> matchingRecipes = new ArrayList<>();

        // Create a normalized set of pantry ingredients
        List<String> pantryIngredients = new ArrayList<>();
        for (PantryItem item : pantryItems) {
            pantryIngredients.add(normalizeIngredientName(item.getName()));
        }

        for (Recipe recipe : allRecipes) {
            String[] requiredIngredients = recipe.getIngredients().split(",");
            boolean allIngredientsAvailable = true;

            for (String ingredient : requiredIngredients) {
                String normalizedRequired = normalizeIngredientName(ingredient.trim());
                boolean found = false;

                for (String pantryIngredient : pantryIngredients) {
                    if (pantryIngredient.equalsIgnoreCase(normalizedRequired) ||
                        pantryIngredient.contains(normalizedRequired) ||
                        normalizedRequired.contains(pantryIngredient)) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    allIngredientsAvailable = false;
                    break;
                }
            }

            if (allIngredientsAvailable) {
                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes;
    }

    // Normalize ingredient name for better matching
    private String normalizeIngredientName(String name) {
        if (name == null) return "";
        name = name.toLowerCase().trim();
        // Remove plural 's' for basic normalization
        if (name.endsWith("s") && !name.endsWith("ss")) {
            name = name.substring(0, name.length() - 1);
        }
        return name;
    }
}
