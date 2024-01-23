package com.example.p7_bbdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
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

    EditText etIdCard;
    EditText etName;
    EditText etSurname;
    Spinner spCycles;
    RadioGroup rgCourses;
    RadioButton rbFirst;
    RadioButton rbSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etIdCard=findViewById(R.id.etIdCard);
        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        spCycles = findViewById(R.id.spCycles);
        rgCourses = findViewById(R.id.rgCourses);
        rbFirst = findViewById(R.id.rbFirst);
        rbSecond = findViewById(R.id.rbSecond);

        String[] cycles={"ASIX","DAM","DAW"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,cycles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCycles.setAdapter(adapter);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> {
            addStudent();
        });

        Button btnFindDNI = findViewById(R.id.btnFindDNI);
        btnFindDNI.setOnClickListener(view -> {
            findStudentById(cycles);
        });

        Button btnRemove = findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(view -> {
            removeStudent();
        });
    }

    public void addStudent(){
        StudentsSQLiteHelper dbHelper = new StudentsSQLiteHelper(this);

        String strIdCard = etIdCard.getText().toString();
        String strName = etName.getText().toString();
        String strSurname = etSurname.getText().toString();
        String strCycle = spCycles.getSelectedItem().toString();
        String strCourse = getSelectedCourse(rgCourses);

        if (strIdCard.isEmpty() || strName.isEmpty() || strSurname.isEmpty() || strCycle.isEmpty() || strCourse.isEmpty()) {
            Toast.makeText(this, getString(R.string.warningEmptyFields), Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (doesStudentExist(db, strIdCard)) {
            Toast.makeText(this, getString(R.string.warningDuplicateStudent), Toast.LENGTH_SHORT).show();
            db.close();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(StudentsDatabaseContract.StudentsTable.COLUMN_ID_CARD, strIdCard);
        values.put(StudentsDatabaseContract.StudentsTable.COLUMN_NAME, strName);
        values.put(StudentsDatabaseContract.StudentsTable.COLUMN_SURNAME, strSurname);
        values.put(StudentsDatabaseContract.StudentsTable.COLUMN_CYCLE, strCycle);
        values.put(StudentsDatabaseContract.StudentsTable.COLUMN_COURSE, strCourse);

        long insertId = db.insert(StudentsDatabaseContract.StudentsTable.TABLE, null, values);

        db.close();

        if (insertId != -1) {
            Toast.makeText(this, getString(R.string.studentAdded) , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.unknownError), Toast.LENGTH_SHORT).show();
        }
    }

    public void findStudentById(String[] cycles){
        String strIdCard = etIdCard.getText().toString();
        if (strIdCard.isEmpty()){
            Toast.makeText(this, getString(R.string.introId), Toast.LENGTH_SHORT).show();
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
        String[] selectionArgs = { strIdCard };

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
            String resName = cursor.getString(cursor.getColumnIndexOrThrow(StudentsDatabaseContract.StudentsTable.COLUMN_NAME));
            String resSurname = cursor.getString(cursor.getColumnIndexOrThrow(StudentsDatabaseContract.StudentsTable.COLUMN_SURNAME));
            String resCycle = cursor.getString(cursor.getColumnIndexOrThrow(StudentsDatabaseContract.StudentsTable.COLUMN_CYCLE));
            String resCourse = cursor.getString(cursor.getColumnIndexOrThrow(StudentsDatabaseContract.StudentsTable.COLUMN_COURSE));

            etName.setText(resName);
            etSurname.setText(resSurname);
            int spCycleSelected = -1;
            for (int i = 0; i < cycles.length; i++){
                if (cycles[i].equals(resCycle)){
                    spCycleSelected = i;
                }
            }
            if (spCycleSelected != -1){
                spCycles.setSelection(spCycleSelected);
            }
            if (resCourse.equals("First")){
                rbFirst.setChecked(true);
            } else {
                rbSecond.setChecked(true);
            }
        } else {
            Toast.makeText(this, getString(R.string.noStudentFound), Toast.LENGTH_SHORT).show();
        }
    }

    public void removeStudent(){
        StudentsSQLiteHelper dbHelper = new StudentsSQLiteHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String idCardValue = etIdCard.getText().toString();
        String selection = StudentsDatabaseContract.StudentsTable.COLUMN_ID_CARD + " LIKE ?";
        String[] selectionArgs = { idCardValue };

        int deletedRows = db.delete(StudentsDatabaseContract.StudentsTable.TABLE, selection, selectionArgs);

        if (deletedRows > 0) {
            Toast.makeText(this, getString(R.string.studentDeleted), Toast.LENGTH_SHORT).show();
            etIdCard.getText().clear();
            etName.getText().clear();
            etSurname.getText().clear();
            spCycles.setSelection(0);
            rgCourses.clearCheck();
        } else {
            Toast.makeText(this, getString(R.string.noStudentFound), Toast.LENGTH_SHORT).show();
        }
    }
    public String getSelectedCourse(RadioGroup radioGroup){
        int selected=radioGroup.getCheckedRadioButtonId();
        if(selected==-1){
            return null;
        }
        RadioButton selectedRb=findViewById(selected);
        return selectedRb.getText().toString();
    }

    private boolean doesStudentExist(SQLiteDatabase db, String idCard) {
        String selection = StudentsDatabaseContract.StudentsTable.COLUMN_ID_CARD + " = ?";
        String[] selectionArgs = {idCard};

        long count = DatabaseUtils.queryNumEntries(db, StudentsDatabaseContract.StudentsTable.TABLE, selection, selectionArgs);

        return count > 0;
    }
}