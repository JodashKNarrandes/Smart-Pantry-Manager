package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PantryListActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private PantryAdapter adapter;
    private List<PantryItem> pantryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_list);

        dbHelper = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.pantry_title);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPantry);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btnAdd = findViewById(R.id.btnAddIngredient);

        loadPantryItems();

        adapter = new PantryAdapter(pantryItems, new PantryAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(PantryItem item) {
                Intent intent = new Intent(PantryListActivity.this, AddEditIngredientActivity.class);
                intent.putExtra("ingredient_id", item.getId());
                intent.putExtra("ingredient_name", item.getName());
                intent.putExtra("ingredient_quantity", item.getQuantity());
                intent.putExtra("ingredient_unit", item.getUnit());
                intent.putExtra("ingredient_expiry", item.getExpiryDate());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(PantryItem item) {
                new AlertDialog.Builder(PantryListActivity.this)
                        .setTitle("Delete Ingredient")
                        .setMessage("Are you sure you want to delete " + item.getName() + "?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            dbHelper.deletePantryItem(item.getId());
                            Toast.makeText(PantryListActivity.this, R.string.ingredient_deleted, Toast.LENGTH_SHORT).show();
                            loadPantryItems();
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(PantryListActivity.this, AddEditIngredientActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPantryItems();
        if (adapter != null) {
            adapter.updateData(pantryItems);
        }
    }

    private void loadPantryItems() {
        pantryItems = dbHelper.getAllPantryItems();
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
