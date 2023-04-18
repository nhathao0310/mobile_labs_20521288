package com.example.mobile_labs_20521288;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ListView employeeListView;
    private ArrayList<Employee> employeeList = new ArrayList<>();
    private ArrayAdapter<Employee> employeeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        EditText employeeNameEditText = findViewById(R.id.nameEditText);
        EditText employeeSalaryEditText = findViewById(R.id.salaryEditText);
        employeeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeList);
        ListView employeeListView = findViewById(R.id.employeeListView);
        employeeListView.setAdapter(employeeAdapter);
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  name = employeeNameEditText.getText().toString();
                String employeeSalary = employeeSalaryEditText.getText().toString();
                double sal = Double.parseDouble(employeeSalary);
                double salary = sal - sal * 0.105;
                if (sal > 11000000)
                    salary = salary - (salary - 11000000) * 0.05;

                Employee employee = new Employee(name, salary);
                employeeList.add(employee);
                employeeAdapter.notifyDataSetChanged();
            }
        });

    }

}
