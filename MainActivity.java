package com.example.elavi.alertmenu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=this.getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
         return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
         switch (item.getItemId())
         {
             case R.id.english:
                 setlanguage("English");
                 return true;
             case R.id.spanish:
                 setlanguage("Spanish");
                 return true;
              default:
                  return false;
         }
    }

    public void setlanguage(String language)
    {
      sharedPreferences.edit().putString("Language",language).apply();
      textView.setText(language);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textview);
        sharedPreferences=this.getSharedPreferences( "com.example.elavi.alertmenu", Context.MODE_PRIVATE);
        String lang=sharedPreferences.getString("language","error");
        MenuInflater menuInflater=this.getMenuInflater();
        if(lang.equals("error")) {
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Are you sure?")
                    .setMessage("Do you definetely want to do this")
                    .setPositiveButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "It's Done", Toast.LENGTH_SHORT).show();
                            setlanguage("Spanish");
                        }
                    })
                    .setNegativeButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setlanguage("English");
                        }
                    }).show();
        }
        else
        {
           setlanguage(lang);
        }
    }
}
