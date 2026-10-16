package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        dbHelper = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.suggested_recipes);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerViewRecipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get pantry items and find matching recipes using strict-matching logic
        List<PantryItem> pantryItems = dbHelper.getAllPantryItems();
        List<Recipe> matchingRecipes = dbHelper.getMatchingRecipes(pantryItems);

        if (matchingRecipes.isEmpty()) {
            // Show empty state message
            setContentView(R.layout.activity_suggested_recipes_empty);
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle(R.string.suggested_recipes);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            TextView tvEmptyMessage = findViewById(R.id.tvEmptyMessage);
            if (tvEmptyMessage != null) {
                tvEmptyMessage.setText(R.string.no_recipes_match);
            }
        } else {
            adapter = new RecipeAdapter(matchingRecipes, recipe -> {
                Intent intent = new Intent(SuggestedRecipesActivity.this, RecipeDetailActivity.class);
                intent.putExtra("recipe_id", recipe.getId());
                intent.putExtra("recipe_name", recipe.getName());
                intent.putExtra("recipe_ingredients", recipe.getIngredients());
                intent.putExtra("recipe_method", recipe.getMethod());
                startActivity(intent);
            });
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
