package fr.robguju.just_move;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Page_connexion extends AppCompatActivity implements View.OnClickListener {

    //declaration attributs
    private EditText id_mail;
    private EditText id_password;
    private Button connexion;
    public Button continuer;
    private Button creer_compte;
    private FirebaseAuth Auth;



    //ce qu'on fait apparaître quand la page charge
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_connexion);

        //liens variable java-XML
        id_mail = (EditText) findViewById(R.id.id_username);
        id_password = (EditText) findViewById(R.id.id_password);
        connexion = (Button) findViewById(R.id.id_connexion);
        continuer = (Button) findViewById(R.id.id_continue);
        creer_compte = (Button) findViewById(R.id.creation_compte);
        Auth = FirebaseAuth.getInstance();

        continuer.setOnClickListener(this);
        creer_compte.setOnClickListener(this);
        connexion.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_continue:
                startActivity(new Intent(Page_connexion.this, Page_Principale.class));
            case R.id.creation_compte:
                startActivity(new Intent(Page_connexion.this, Page_Creation_Compte.class));
            case R.id.id_connexion:
                connexion_utilisateur();
        }
    }

    private void connexion_utilisateur() {
        String mail = id_mail.getText().toString().trim();
        String password = id_password.getText().toString().trim();

        if (mail.isEmpty()){
            id_mail.setError("Adresse mail manquante");
            id_mail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            id_mail.setError("Adresse mail incorrect");
            id_mail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            id_password.setError("Mot de passe manquant");
            id_mail.requestFocus();
            return;
        }

        Auth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(Page_connexion.this, Page_Principale.class));
                }
                else {
                    Toast.makeText(Page_connexion.this, "Connexion impossible, veuillez vérifier vos identifiants",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}