package fr.robguju.just_move;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Page_connexion extends AppCompatActivity {

    //declaration attributs
    private EditText eName;
    private EditText ePassword;
    private TextView eEssai;
    private Button eLogin;
    public Button continu;
    private int counter = 5;
    boolean isValid = false;
    String userName = "";
    String userPassword = "";

    //identifiants (à gérer en BDD)
    class Identifiants
    {
        String name = "Admin";
        String password = "1234";
    }


    //ce qu'on fait apparaître quand la page charge
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_connexion);

        //liens variable java-XML
        eName = findViewById(R.id.id_username);
        ePassword = findViewById(R.id.id_password);
        eEssai = findViewById(R.id.id_essais);
        eLogin = findViewById(R.id.id_connexion);
        continu = findViewById(R.id.id_continue);

        //gestion du bouton continuer en tant qu'invite
        continu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_connexion.this, Page_Principale.class));
            }
        });


        //gestion du bouton connexion avec validation identifiants
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //on obtient le texte tapé
                userName = eName.getText().toString();
                userPassword = ePassword.getText().toString();

                //on vérifie si c'est vide
                if(userName.isEmpty() || userPassword.isEmpty())
                {
                    //si oui, on affiche un message d'erreur
                    Toast.makeText(Page_connexion.this, "Please enter name and password!", Toast.LENGTH_LONG).show();
                }else {
                    //sinon on vérifie les champs avec la fonction speciale
                    isValid = validate(userName, userPassword);
                    //si ce n'est pas valide
                    if (!isValid) {
                        //on decremente le compteur d'essais
                        counter--;
                        //on met à jour l'indicateur d'essais
                        eEssai.setText("Attempts Remaining: " + String.valueOf(counter));
                        // si le compteur est à 0, on desactive la connexion et on affiche un message d'erreue
                        if (counter == 0) {
                            eLogin.setEnabled(false);
                            Toast.makeText(Page_connexion.this, "You have used all your attempts try again later!", Toast.LENGTH_LONG).show();
                        }
                        //sinon on affiche un message d'erreur pour dire de reessayer
                        else {
                            Toast.makeText(Page_connexion.this, "Incorrect credentials, please try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                    //si les identifiants sont valides
                    else {
                        //on entre dans la page principale
                        startActivity(new Intent(Page_connexion.this, Page_Principale.class));
                    }

                }
            }
        });
    }

    // fonction de validation des identifiants
    private boolean validate(String userName, String userPassword)
    {
        //on instancie un objet Identifiant correct
        Identifiants credentials = new Identifiants();

        //on vérifie si les identifiants tapés correspondent aux identifiants corrects
        if(userName.equals(credentials.name) && userPassword.equals(credentials.password))
        {
            return true;
        }

        return false;
    }
}