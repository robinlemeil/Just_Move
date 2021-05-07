package fr.robguju.just_move;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserPage extends AppCompatActivity {

    private Button eSettings;
    private Button eFavoris;
    private Button eTutoriels;
    private Button eDeconnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        eSettings = findViewById(R.id.button_Utilisateur);
        eFavoris = findViewById(R.id.button_Favoris);
        eTutoriels = findViewById(R.id.button_Tutoriels);
        eDeconnexion = findViewById(R.id.button_Deconexion);

        eSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserSettings.class));
            }
    }
}