package fr.robguju.just_move;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserSettings extends AppCompatActivity {
    private EditText prenom;
    private EditText nom;
    private EditText age;
    private EditText tps_travail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

    }
}