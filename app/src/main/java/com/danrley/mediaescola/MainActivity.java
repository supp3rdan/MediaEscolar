package com.danrley.mediaescola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etNota1, etNota2;
    TextView tvResultado, tvSituacao;
    LinearLayout layResultado;
    Button btnCalcular;
    ImageView imgSituacao;
    InputMethodManager imn; //ocultar teclado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNota1 = findViewById(R.id.etNota1);
        etNota2 = findViewById(R.id.etNota2);
        tvResultado = findViewById(R.id.tvResultado);
        tvSituacao = findViewById(R.id.tvSituacao);
        btnCalcular = findViewById(R.id.btnCalcular);
        imgSituacao = findViewById(R.id.imgSituacao);
        layResultado = findViewById(R.id.layResultado);
        imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //Ocultar teclado

        layResultado.setVisibility(View.INVISIBLE); //Ocultar layout de resultado
    }

    public void calcular(View view){
        if(dadosPreenchidos()){
            float n1 = Float.parseFloat(etNota1.getText().toString());
            float n2 = Float.parseFloat(etNota2.getText().toString());

            if(intervaloValido(n1, n2)){
                layResultado.setVisibility(View.VISIBLE);
                imn.hideSoftInputFromWindow(etNota1.getWindowToken(), 0); //Ocultaodo teclado
                float media = (n1 + n2) / 2;

                tvResultado.setText(String.format("%.2f", media));

                if(aprovado(media)){
                    tvSituacao.setText("Aprovado!");
                    tvSituacao.setTextColor(Color.parseColor("#0e801b"));
                    imgSituacao.setImageResource(R.drawable.emojiaprovado);
                } else {
                    tvSituacao.setText("Reprovado");
                    tvSituacao.setTextColor(Color.parseColor("#7e1010"));
                    imgSituacao.setImageResource(R.drawable.emojireprovado);
                }
            }
        }
    }
    public boolean aprovado(float m){
        boolean situacao;
        situacao = m >= 7 ? true: false;

        return situacao;
    }

    public boolean dadosPreenchidos(){
        boolean ok = true;
        if(etNota1.getText().toString().trim().isEmpty()){
            ok = false;
            etNota1.setError("Informe a nota");
            layResultado.setVisibility(View.INVISIBLE);
        }
        if(etNota2.getText().toString().trim().isEmpty()){
            ok = false;
            etNota2.setError("Informe a nota");
            layResultado.setVisibility(View.INVISIBLE);
        }
        return ok;
    }

    public boolean intervaloValido(float n1, float n2){
        boolean validos = true;

        if(n1 < 0 || n1 > 10){
            validos = false;
            etNota1.setError("Somente valores entre 0 e 10");
            layResultado.setVisibility(View.INVISIBLE);
        }

        if(n2 < 0 || n2 > 10){
            validos = false;
            etNota2.setError("Somente valores entre 0 e 10");
            layResultado.setVisibility(View.INVISIBLE);
        }
        return validos;
    }
}