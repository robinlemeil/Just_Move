package fr.robguju.just_move;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Page_Creation_Compte extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth Auth;
    private ImageView id_logo;
    private EditText id_prenom,id_nom, id_age, id_taille;
    private EditText id_poids, id_temps_bureau, id_mail, id_password;
    private Button id_enregistrer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation__compte);

        Auth = FirebaseAuth.getInstance();


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
        switch (v.getId()){
            case R.id.id_logo :
                startActivity(new Intent(Page_Creation_Compte.this, Page_connexion.class));
            case R.id.enregistrer :
                creation_compte();
        }
    }

    private void creation_compte() {

        String prenom = id_prenom.getText().toString().trim();
        String nom = id_nom.getText().toString().trim();
        String age = id_age.getText().toString();
        String taille = id_taille.getText().toString();
        String poids = id_poids.getText().toString();
        String temps = id_temps_bureau.getText().toString();
        String mail = id_mail.getText().toString().trim();
        String password = id_password.getText().toString().trim();

        if (prenom.isEmpty()){
            id_prenom.setError("Prénom manquant");
            id_prenom.requestFocus();
            return;}

        if (nom.isEmpty()){
            id_nom.setError("Nom manquant");
            id_nom.requestFocus();
            return;}

        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            id_mail.setError("Adresse mail incorrecte");
            id_mail.requestFocus();
            return;}
        if (password.isEmpty()){
            id_password.setError("Mot de passe manquant");
            id_password.requestFocus();
            return;}

        if(age.isEmpty()){
            id_age.setError("Age manquant");
            id_age.requestFocus(); }


        if(taille.isEmpty()){
            id_taille.setError("Taille manquante");
            id_taille.requestFocus(); }


        if(poids.isEmpty()){
            id_poids.setError("Poids manquant");
            id_poids.requestFocus(); }


        if(temps.isEmpty()){
            id_temps_bureau.setError("Temps au bureau manquant");
            id_temps_bureau.requestFocus(); }

        Auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Utilisateur user = new Utilisateur(prenom,nom,age,taille,poids,temps,mail,password);
                    FirebaseDatabase.getInstance().getReference("Utilisateurs")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Page_Creation_Compte.this,"Profil vérifié !",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Page_Creation_Compte.this, Page_Principale.class));
                            }
                            else {
                                Toast.makeText(Page_Creation_Compte.this,"Profil incorrect",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Page_Creation_Compte.this,"Profil incorrect",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}