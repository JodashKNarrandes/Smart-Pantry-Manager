package com.example.smartpantrymanager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Recipe Details");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView tvName = findViewById(R.id.tvRecipeName);
        TextView tvIngredients = findViewById(R.id.tvIngredients);
        TextView tvMethod = findViewById(R.id.tvMethod);

        String name = getIntent().getStringExtra("recipe_name");
        String ingredients = getIntent().getStringExtra("recipe_ingredients");
        String method = getIntent().getStringExtra("recipe_method");

        tvName.setText(name);
        tvIngredients.setText(ingredients);
        tvMethod.setText(method);
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
