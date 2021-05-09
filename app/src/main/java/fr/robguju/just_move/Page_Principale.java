package fr.robguju.just_move;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//on implemente une classe abstraite pour le spinner
public class Page_Principale extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    //declaration attributs
    private Button onglet_accueil;
    private Button onglet_routine;
    private ListView selec_article;

    //ce qu'on fait apparaître quand la page charge
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__principale);

        //on lie le XML à nos variables
        onglet_accueil = findViewById(R.id.id_onglet_ressource);
        onglet_routine = findViewById(R.id.id_onglet_routine);
        selec_article = findViewById(R.id.liste_article);


        //gestion du clic sur le bouton accueil
        onglet_accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_Principale.this, Page_Principale.class));
                }
            }
            );

        //gestion du clic sur le bouton routine
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

        //on crée une liste d' objet article
        List<Article> articles_justmove = new ArrayList<>();
        articles_justmove.add(new Article("Hydratation", "eau", "Comment s'hydrater au travail?", false));
        articles_justmove.add(new Article("Mieux bouger", "etirements", "Voici 5 étirements à réaliser sans matériel", true));
        articles_justmove.add(new Article("Posture au travail", "siege", "Qu'est-ce qu'une bonne chaise de bureau?", false));

        //on la lie avec le XML et on appelle une fonction speciale qui instancie chaque article (image,titre,resumé)
        ListView list_articles = findViewById(R.id.liste_article);
        list_articles.setAdapter(new Articles_Adapter(this,articles_justmove));

        /*//gestion du clic sur le bouton routine
        selec_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Page_Principale.this, Page_Routine.class));
            }
        });*/
    }


    //methodes abstraites
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),text , Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}