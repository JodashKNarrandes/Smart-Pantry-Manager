package com.example.smartpantrymanager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddEditIngredientActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText etName, etQuantity, etUnit, etExpiry;
    private long ingredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        dbHelper = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.add_ingredient);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etName = findViewById(R.id.etName);
        etQuantity = findViewById(R.id.etQuantity);
        etUnit = findViewById(R.id.etUnit);
        etExpiry = findViewById(R.id.etExpiry);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);

        // Check if editing existing ingredient
        if (getIntent().hasExtra("ingredient_id")) {
            ingredientId = getIntent().getLongExtra("ingredient_id", -1);
            toolbar.setTitle(R.string.edit_ingredient);

            etName.setText(getIntent().getStringExtra("ingredient_name"));
            etQuantity.setText(String.valueOf(getIntent().getDoubleExtra("ingredient_quantity", 0)));
            etUnit.setText(getIntent().getStringExtra("ingredient_unit"));
            etExpiry.setText(getIntent().getStringExtra("ingredient_expiry"));
        }

        btnSave.setOnClickListener(v -> saveIngredient());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void saveIngredient() {
        String name = etName.getText().toString().trim();
        String quantityStr = etQuantity.getText().toString().trim();
        String unit = etUnit.getText().toString().trim();
        String expiry = etExpiry.getText().toString().trim();

        // Validation
        if (name.isEmpty() || quantityStr.isEmpty() || unit.isEmpty()) {
            Toast.makeText(this, R.string.please_fill_all_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        double quantity;
        try {
            quantity = Double.parseDouble(quantityStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        PantryItem item = new PantryItem(name, quantity, unit, expiry);

        if (ingredientId != -1) {
            // Update existing
            item.setId(ingredientId);
            dbHelper.updatePantryItem(item);
            Toast.makeText(this, R.string.ingredient_updated, Toast.LENGTH_SHORT).show();
        } else {
            // Add new
            dbHelper.addPantryItem(item);
            Toast.makeText(this, R.string.ingredient_added, Toast.LENGTH_SHORT).show();
        }

        finish();
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
