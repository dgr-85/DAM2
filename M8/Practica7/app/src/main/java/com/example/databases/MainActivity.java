package com.example.databases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText id_card = findViewById(R.id.id_card);
        EditText name = findViewById(R.id.name);
        EditText surname = findViewById(R.id.surname);
        Spinner cycleSpinner = findViewById(R.id.cycle);
        RadioGroup course = findViewById(R.id.course);
        RadioButton first = findViewById(R.id.first);
        RadioButton second = findViewById(R.id.second);

        String[] cycleValues = {"ASIX", "DAW", "DAM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cycleValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cycleSpinner.setAdapter(adapter);

        // Operación 1: añadir students
        Button add = findViewById(R.id.add);
        add.setOnClickListener(view -> {

            StudentsSQLiteHelper dbHelper = new StudentsSQLiteHelper(this);

            String idCardValue = id_card.getText().toString();
            String nameValue = name.getText().toString();
            String surnameValue = surname.getText().toString();
            String cycleValue = cycleSpinner.getSelectedItem().toString();
            String courseValue = getCourseText(course);

            if (idCardValue.isEmpty() || nameValue.isEmpty() || surnameValue.isEmpty() || cycleValue.isEmpty() || courseValue.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            if (isStudentExists(db, idCardValue)) {
                Toast.makeText(this, "This student has already exist in database", Toast.LENGTH_SHORT).show();
                db.close();
                return;
            }

            ContentValues values = new ContentValues();
            values.put(StudentsDatabaseContract.StudentsTable.COLUMN_ID_CARD, idCardValue);
            values.put(StudentsDatabaseContract.StudentsTable.COLUMN_NAME, nameValue);
            values.put(StudentsDatabaseContract.StudentsTable.COLUMN_SURNAME, surnameValue);
            values.put(StudentsDatabaseContract.StudentsTable.COLUMN_CYCLE, cycleValue);
            values.put(StudentsDatabaseContract.StudentsTable.COLUMN_COURSE, courseValue);

            long insertId = db.insert(StudentsDatabaseContract.StudentsTable.TABLE, null, values);

            db.close();

            if (insertId != -1) {
                Toast.makeText(this, "Studend added!" , Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "It seems that you have an error", Toast.LENGTH_SHORT).show();
            }
        });

        // Operación 2: consultar student por DNI
        Button findbyDNI = findViewById(R.id.findDNI);
        findbyDNI.setOnClickListener(view -> {

            String idCardValue = id_card.getText().toString();
            if (idCardValue.isEmpty()){
                Toast.makeText(this, "Please type an ID card", Toast.LENGTH_SHORT).show();
                return;
            }

            StudentsSQLiteHelper dbHelper = new StudentsSQLiteHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] projection = {
                    BaseColumns._ID,
                    StudentsDatabaseContract.StudentsTable.COLUMN_NAME,
                    StudentsDatabaseContract.StudentsTable.COLUMN_SURNAME,
                    StudentsDatabaseContract.StudentsTable.COLUMN_CYCLE,
                    StudentsDatabaseContract.StudentsTable.COLUMN_COURSE
            };

            String selection = StudentsDatabaseContract.StudentsTable.COLUMN_ID_CARD + " = ?";
            String[] selectionArgs = { idCardValue };

            Cursor cursor = db.query(
                    StudentsDatabaseContract.StudentsTable.TABLE,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursor.moveToFirst()) {
                String nameStudent = cursor.getString(cursor.getColumnIndexOrThrow(StudentsDatabaseContract.StudentsTable.COLUMN_NAME));
                String surnameStudent = cursor.getString(cursor.getColumnIndexOrThrow(StudentsDatabaseContract.StudentsTable.COLUMN_SURNAME));
                String cycleStudent = cursor.getString(cursor.getColumnIndexOrThrow(StudentsDatabaseContract.StudentsTable.COLUMN_CYCLE));
                String courseStudent = cursor.getString(cursor.getColumnIndexOrThrow(StudentsDatabaseContract.StudentsTable.COLUMN_COURSE));

                name.setText(nameStudent);
                surname.setText(surnameStudent);
                int cycleNumForSpinner = -1;
                for (int i = 0; i < cycleValues.length; i++){
                    if (cycleValues[i].equals(cycleStudent)){
                        cycleNumForSpinner = i;
                    }
                }
                if (cycleNumForSpinner != -1){
                    cycleSpinner.setSelection(cycleNumForSpinner);
                }
                if (courseStudent.equals("First")){
                    first.setChecked(true);
                } else {
                    second.setChecked(true);
                }
            } else {
                Toast.makeText(this, "No student found with the provided ID card", Toast.LENGTH_SHORT).show();
            }
        });

        // Operación 3: Consultar por ciclo
        Button findByCicle = findViewById(R.id.findCycle);
        findByCicle.setOnClickListener(view -> {
            StudentsSQLiteHelper dbHelper = new StudentsSQLiteHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        });

        // Operación 4: Consultar students por curso (es necesario ciclo)
        Button findByCourse = findViewById(R.id.findCourse);
        findByCourse.setOnClickListener(view -> {
            StudentsSQLiteHelper dbHelper = new StudentsSQLiteHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        });

        // Operación 5: Borrar un student
        Button remove = findViewById(R.id.remove);
        remove.setOnClickListener(view -> {
            StudentsSQLiteHelper dbHelper = new StudentsSQLiteHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String idCardValue = id_card.getText().toString();
            String selection = StudentsDatabaseContract.StudentsTable.COLUMN_ID_CARD + " LIKE ?";
            String[] selectionArgs = { idCardValue };

            int deletedRows = db.delete(StudentsDatabaseContract.StudentsTable.TABLE, selection, selectionArgs);

            if (deletedRows > 0) {
                Toast.makeText(this, "Student removed successfully", Toast.LENGTH_SHORT).show();
                id_card.getText().clear();
                name.getText().clear();
                surname.getText().clear();
                cycleSpinner.setSelection(0);
                course.clearCheck();
            } else {
                Toast.makeText(this, "No student found with the provided name", Toast.LENGTH_SHORT).show();
            }
        });

        // Operación 6: Actualizar un student
        Button update = findViewById(R.id.update);
        update.setOnClickListener(view -> {
            StudentsSQLiteHelper dbHelper = new StudentsSQLiteHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String idCardValue = id_card.getText().toString();
            String nameValue = name.getText().toString();
            String surnameValue = surname.getText().toString();
            String cycleValue = cycleSpinner.getSelectedItem().toString();
            String courseValue = getCourseText(course);

            ContentValues values = new ContentValues();
            values.put(StudentsDatabaseContract.StudentsTable.COLUMN_ID_CARD, idCardValue);
            values.put(StudentsDatabaseContract.StudentsTable.COLUMN_NAME, nameValue);
            values.put(StudentsDatabaseContract.StudentsTable.COLUMN_SURNAME, surnameValue);
            values.put(StudentsDatabaseContract.StudentsTable.COLUMN_CYCLE, cycleValue);
            values.put(StudentsDatabaseContract.StudentsTable.COLUMN_COURSE, courseValue);


            String selection = StudentsDatabaseContract.StudentsTable.COLUMN_ID_CARD + " LIKE ?";
            String[] selectionArgs = { idCardValue };

            int count = db.update(
                    StudentsDatabaseContract.StudentsTable.TABLE,
                    values,
                    selection,
                    selectionArgs);

            if (count > 0) {
                Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No student found with the provided ID card", Toast.LENGTH_SHORT).show();
            }
        });

        // Operación añadida: Limpiar todos los campos del formulario de la aplicación
        Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(view -> {
            id_card.getText().clear();
            name.getText().clear();
            surname.getText().clear();
            cycleSpinner.setSelection(0);
            course.clearCheck();
        });
    }

    private String getCourseText(RadioGroup course) {
        int selected = course.getCheckedRadioButtonId();

        if (selected != -1) {
            RadioButton courseSelected = findViewById(selected);
            return courseSelected.getText().toString();
        }

        return "";
    }

    private boolean isStudentExists(SQLiteDatabase db, String idCard) {
        String selection = StudentsDatabaseContract.StudentsTable.COLUMN_ID_CARD + " = ?";
        String[] selectionArgs = {idCard};

        long count = DatabaseUtils.queryNumEntries(db, StudentsDatabaseContract.StudentsTable.TABLE, selection, selectionArgs);

        return count > 0;
    }
}