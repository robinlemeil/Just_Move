package fr.robguju.just_move;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Page_ModifParametre extends AppCompatActivity implements View.OnClickListener   {

    private FirebaseAuth Auth;
    private ImageView id_logo;
    private EditText id_prenom, id_nom, id_age, id_taille;
    private EditText id_poids, id_temps_bureau, id_mail, id_password;
    private Button id_enregistrer;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__modif_parametre);

        Auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        id_logo = (ImageView) findViewById(R.id.id_logo);
        id_prenom = (EditText) findViewById(R.id.prenom);
        id_nom = (EditText) findViewById(R.id.nom);
        id_age = (EditText) findViewById(R.id.age);
        id_taille = (EditText) findViewById(R.id.taille);
        id_poids = (EditText) findViewById(R.id.poids);
        id_temps_bureau = (EditText) findViewById(R.id.temps_bureau);
        id_mail = (EditText) findViewById(R.id.mail);
        id_password = (EditText) findViewById(R.id.password);
        id_enregistrer = (Button) findViewById(R.id.enregistrer);

        id_logo.setOnClickListener(this);
        id_enregistrer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enregistrer:
                modif_compte();
        }
    }

    private void modif_compte() {

        String mprenom = id_prenom.getText().toString().trim();
        String nom = id_nom.getText().toString().trim();
        String age = id_age.getText().toString();
        String taille = id_taille.getText().toString();
        String poids = id_poids.getText().toString();
        String temps = id_temps_bureau.getText().toString();
        String mail = id_mail.getText().toString().trim();
        String password = id_password.getText().toString().trim();

        if (mprenom.length()>0) {
            mDatabase.child("Utilasateurs").child("username").setValue(mprenom);
            return;
        }
        if (nom.isEmpty()) {
            id_nom.setError("Nom manquant");
            id_nom.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            id_mail.setError("Adresse mail incorrecte");
            id_mail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            id_password.setError("Mot de passe manquant");
            id_password.requestFocus();
            return;
        }

        if (age.isEmpty()) {
            id_age.setError("Age manquant");
            id_age.requestFocus();
        }


        if (taille.isEmpty()) {
            id_taille.setError("Taille manquante");
            id_taille.requestFocus();
        }


        if (poids.isEmpty()) {
            id_poids.setError("Poids manquant");
            id_poids.requestFocus();
        }


        if (temps.isEmpty()) {
            id_temps_bureau.setError("Temps au bureau manquant");
            id_temps_bureau.requestFocus();
        }


    }
}