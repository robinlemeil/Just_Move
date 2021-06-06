package fr.robguju.just_move;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Page_ModifParametre extends AppCompatActivity implements View.OnClickListener   {

    private FirebaseAuth Auth;
    private ImageView id_logo;
    private EditText id_prenom, id_nom, id_age, id_taille;
    private EditText id_poids, id_temps_bureau, id_mail, id_password;
    private Button id_enregistrer;
    private DatabaseReference mDatabase;
    private FirebaseUser currentUser;
    private String iduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__modif_parametre);

        currentUser = Auth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("Utilisateurs");
        iduser =currentUser.getUid();
        id_logo = (ImageView) findViewById(R.id.id_logo);
        id_prenom = (EditText) findViewById(R.id.prenom);
        id_nom = (EditText) findViewById(R.id.nom);
        id_age = (EditText) findViewById(R.id.age);
        id_taille = (EditText) findViewById(R.id.taille);
        id_poids = (EditText) findViewById(R.id.poids);
        id_temps_bureau = (EditText) findViewById(R.id.temps_bureau);
        /*id_mail = (EditText) findViewById(R.id.mail);
        id_password = (EditText) findViewById(R.id.password);*/
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
        String mnom = id_nom.getText().toString().trim();
        String mage = id_age.getText().toString();
        String mtaille = id_taille.getText().toString();
        String mpoids = id_poids.getText().toString();
        String mtemps = id_temps_bureau.getText().toString();
        /*String mmail = id_mail.getText().toString().trim();
        String mpassword = id_password.getText().toString().trim();*/

        if (mprenom.length()>0) {
            mDatabase.child(iduser).child("prenom").setValue(mprenom);

        }
        if (mnom.length()>0) {
            mDatabase.child(iduser).child("nom").setValue(mnom);

        }

        if (mage.length()>0) {
            mDatabase.child(iduser).child("age").setValue(mage);
        }


        if (mtaille.length()>0) {
            mDatabase.child(iduser).child("taille").setValue(mtaille);
        }


        if (mpoids.length()>0) {
            mDatabase.child(iduser).child("poids").setValue(mpoids);
        }


        if (mtemps.length()>0) {
            mDatabase.child(iduser).child("temps_bureau").setValue(mtemps);
        }
        if(true){
            Toast.makeText(Page_ModifParametre.this,"Informations changees !",Toast.LENGTH_LONG).show();
            startActivity(new Intent(Page_ModifParametre.this, Page_Principale.class));
        }




    }
}