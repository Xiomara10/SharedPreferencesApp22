package com.xtorres.sharedpreferencesapp2.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.xtorres.sharedpreferencesapp2.R;

public class QuizActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText carreraInput;
    private RadioGroup generoRadiogroup;
    private CheckBox terminosCheckbox;
    private Button quizButton;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        nameInput = findViewById(R.id.name_input);
        carreraInput = findViewById(R.id.carrera_input);
        generoRadiogroup = findViewById(R.id.genero_radiogroup);
        terminosCheckbox = findViewById(R.id.terminos_checkbox);
        quizButton = findViewById(R.id.quiz_button);

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendQuiz();
            }
        });

    }

    private void sendQuiz(){
        String name = nameInput.getText().toString();
        String carrera = carreraInput.getText().toString();

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit()
                .putString("name", name)
                .putString("carrera", carrera)
                .commit();

        generoRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.masculino_rbutton:
                        sp.edit().putString("genero","M").commit();
                        break;
                    case R.id.femenino_rbutton:
                        sp.edit().putString("genero", "F").commit();
                        break;
                }
            }
        });


        //Recuperar valores del sp

        String username = sp.getString("name", null);
        if(name != null) {
            nameInput.setText(name);
        }

        String genero = sp.getString("genero", null);
        if (genero != null){
            if("M".equals(genero)){
                generoRadiogroup.check(R.id.masculino_rbutton);
            }else if ("F".equals(genero)){
                generoRadiogroup.check(R.id.femenino_rbutton);
            }
        }

        terminosCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (terminosCheckbox.isChecked()){
                    sp.edit().putBoolean("checked", true).commit();
                }else {
                    sp.edit().putBoolean("checked", false).commit();
                }
            }
        });

    }
}
