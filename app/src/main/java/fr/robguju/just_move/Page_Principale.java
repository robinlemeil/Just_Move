package fr.robguju.just_move;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Page_Principale extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    String[] type = { "Video", "Article", "Audio"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__principale);

        //creation du spinner
        Spinner spinner = (Spinner) findViewById(R.id.id_type);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.array.liste_type, android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),text , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}