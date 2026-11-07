package com.example.e_reader;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText etBookTitle;
    private EditText etAuthorName;
    private RadioButton Complete;
    DatabaseSupport databaseSupport;
    private RecyclerView recyclerView = findViewById(R.id.recyclerView);
    Book selectedBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        etBookTitle = findViewById(R.id.etBookTitle);
        etAuthorName = findViewById(R.id.etAuthorName);
        Button btnAddBook = findViewById(R.id.btnAddBook);
        Button btnDeleteBook = findViewById(R.id.btnDeleteBook);
        Complete = findViewById(R.id.rbtnComplete);

        databaseSupport = new DatabaseSupport(MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        MyAdapter arrayAdapter = new MyAdapter(databaseSupport.getAll());
        recyclerView.setAdapter(arrayAdapter);

    } //onCreate
    public void onClickAdd(View view){
        Book book = null;
        String title = etBookTitle.getText().toString();
        String author = etAuthorName.getText().toString();
        boolean complete = Complete.isChecked();
        try{
            book = new Book(-1,title,author,complete);
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error while adding book", Toast.LENGTH_LONG).show();
        }
        boolean addedToDb = databaseSupport.addBook(book);
        textView.setText("Book added: "+addedToDb);

        MyAdapter arrayAdapter = new MyAdapter(databaseSupport.getAll());
        recyclerView.setAdapter(arrayAdapter);

    }// add

    public void onClickDelete(View view){
        databaseSupport=new DatabaseSupport(MainActivity.this);
        databaseSupport.deleteBook(selectedBook);
        textView.setText("Book deleted");

        MyAdapter arrayAdapter = new MyAdapter(databaseSupport.getAll());
        recyclerView.setAdapter(arrayAdapter);

    }// delete

}//main