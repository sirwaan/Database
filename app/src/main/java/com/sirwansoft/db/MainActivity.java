package com.sirwansoft.db;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText_name, editTextSurnName, editTextMark, edittext_id;
    Button btnAddData, btn_viewAll, btn_update, btn_delete;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editText_name = findViewById(R.id.edit_text_name);
        editTextSurnName = findViewById(R.id.edit_text_Sum_name);
        editTextMark = findViewById(R.id.edit_Mark);
        edittext_id = findViewById(R.id.edittext_id_data);


        btnAddData = findViewById(R.id.btn_add_data);
        btn_viewAll = findViewById(R.id.btn_view_data);
        btn_update = findViewById(R.id.btn_update_data);
        btn_delete = findViewById(R.id.btn_delete);

        addData();
        viewAll();
        updateData();
        deleteData();
    }

    public void addData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editText_name.getText().toString(),
                        editTextSurnName.getText().toString(),
                        editTextMark.getText().toString());

                if (isInserted = true) {
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "Data Not inserted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void viewAll() {
        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {

                    //show massage  if it is no result

                    showMassage("Errore ", "NoThing Found");

                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    //this 0 is mean for first column  and 1 is 2nd column and ...
                    buffer.append("Id :" + res.getString(0) + "\n");
                    buffer.append("Name :" + res.getString(1) + "\n");
                    buffer.append("Surname :" + res.getString(2) + "\n");
                    buffer.append("Mark :" + res.getString(3) + "\n\n");
                }
                //show all data
                showMassage("Data", buffer.toString());

            }
        });

    }

    public void showMassage(String title, String massage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(massage);
        builder.show();


    }

    public void updateData() {

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(
                        edittext_id.getText().toString(),
                        editText_name.getText().toString(),
                        editTextSurnName.getText().toString(),
                        editTextMark.getText().toString());
                if (isUpdate) {
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "Data Not updated", Toast.LENGTH_SHORT).show();


            }
        });


    }

    public void deleteData() {

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = myDb.deleteData(edittext_id.getText().toString());
                if (deleteRows > 0){

                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
