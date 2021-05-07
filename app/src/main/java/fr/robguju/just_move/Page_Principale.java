package fr.robguju.just_move;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Page_Principale extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    //declaration objects
    private Button onglet_accueil;
    private Button onglet_routine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__principale);

        onglet_accueil = findViewById(R.id.id_onglet_ressource);
        onglet_routine = findViewById(R.id.id_onglet_routine);

        onglet_accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_Principale.this, Page_Principale.class));
                }
            }
            );

        onglet_routine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_Principale.this, Page_Routine.class));
                 }
             }
             );

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
        Toast.makeText(getApplicationContext(),text , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}