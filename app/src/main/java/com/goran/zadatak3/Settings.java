package com.goran.zadatak3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Spinner language,color,fontStyle;
    TextView lang,col,fstyle;
    Button saveSett;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences=getSharedPreferences("settings",MODE_PRIVATE);
        language=findViewById(R.id.language);
        color=findViewById(R.id.color);
        fontStyle=findViewById(R.id.fontStyle);
        lang=findViewById(R.id.lang);
        col=findViewById(R.id.col);
        fstyle=findViewById(R.id.fstyle);
        saveSett=findViewById(R.id.saveSett);

        setLanguage();
        setFontStyle();
    }


    @Override
    protected void onStart() {
        super.onStart();
        ArrayAdapter<CharSequence>adapter1=ArrayAdapter.createFromResource(this,R.array.languages, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language.setAdapter(adapter1);

        ArrayAdapter<CharSequence>adapter2=ArrayAdapter.createFromResource(this,R.array.fontStyle, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontStyle.setAdapter(adapter2);

        ArrayAdapter<CharSequence>adapter3=ArrayAdapter.createFromResource(this,R.array.color, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        color.setAdapter(adapter3);



    }

    private void setFontStyle() {
        switch (sharedPreferences.getString("style","")){
            case "bold":
                lang.setTypeface(null, Typeface.BOLD);
                col.setTypeface(null,Typeface.BOLD);
                fstyle.setTypeface(null,Typeface.BOLD);
                break;
            case "italic":
                lang.setTypeface(null, Typeface.ITALIC);
                col.setTypeface(null,Typeface.ITALIC);
                fstyle.setTypeface(null,Typeface.ITALIC);
                break;
            case "normal":
                lang.setTypeface(null, Typeface.NORMAL);
                col.setTypeface(null,Typeface.NORMAL);
                fstyle.setTypeface(null,Typeface.NORMAL);
                break;
            default:
                break;

        }
    }


    private void setLanguage() {
        switch (sharedPreferences.getString("language","")){
            case "English":
                lang.setText("Jezik");
                col.setText("Boja");
                fstyle.setText("Stil fonta");
                saveSett.setText("Sacuvaj podesavanja");
                break;
            case "Srpski":
                lang.setText("Language");
                col.setText("Color");
                fstyle.setText("Font style");
                saveSett.setText("Save settings");
                break;
            default:
                break;
        }
    }

    public void saveSettings(View view) {
        String lang=language.getSelectedItem().toString();
        String style=fontStyle.getSelectedItem().toString();
        String col=color.getSelectedItem().toString();
        String fon="Arial";
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("language",lang);
        editor.putString("style",style);
        editor.putString("color",col);
        editor.putString("font",fon);
        editor.apply();
        finish();

    }
}