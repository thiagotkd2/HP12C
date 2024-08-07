package com.thiago.HP12C;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // lembrar de escrever o codigo apos setcontentview
        // *para achar a atividade atual e não dar nullpointerexcp
        inicializaBotoes();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn0) {
            Log.i("teste", "0");
        } else if (id == R.id.btn1) {
            Log.i("teste", "1");
        } else if (id == R.id.btn2) {
            Log.i("teste", "2");
        } else if (id == R.id.btn3) {
            Log.i("teste", "3");
        } else if (id == R.id.btn4) {
            Log.i("teste", "4");
        } else if (id == R.id.btn5) {
            Log.i("teste", "5");
        } else if (id == R.id.btn6) {
            Log.i("teste", "6");
        } else if (id == R.id.btn7) {
            Log.i("teste", "7");
        } else if (id == R.id.btn8) {
            Log.i("teste", "8");
        } else if (id == R.id.btn9) {
            Log.i("teste", "9");
        } else if (id == R.id.btnComma) {
            Log.i("teste", ",");
        } else if (id == R.id.btnEnter) {
            Log.i("teste", "enter()");
        } else if (id == R.id.btnClear) {
            Log.i("teste", "Clear");
        } else if (id == R.id.btnDelete) {
            Log.i("teste", "Del");
        } else if (id == R.id.btnMult) {
            Log.i("teste", "x");
        } else if (id == R.id.btnDiv) {
            Log.i("teste", "/");
        } else if (id == R.id.btnSoma) {
            Log.i("teste", "+");
        } else if (id == R.id.btnSub) {
            Log.i("teste", "-");
        }

    }
    private void inicializaBotoes(){

        Button zeroBtn = (Button) findViewById(R.id.btn0);
        Button umBtn = (Button) findViewById(R.id.btn1);
        Button doisBtn = (Button) findViewById(R.id.btn2);
        Button tresBtn = (Button) findViewById(R.id.btn3);
        Button quatroBtn = (Button) findViewById(R.id.btn4);
        Button cincoBtn = (Button) findViewById(R.id.btn5);
        Button seisBtn = (Button) findViewById(R.id.btn6);
        Button seteBtn = (Button) findViewById(R.id.btn7);
        Button oitoBtn = (Button) findViewById(R.id.btn8);
        Button noveBtn = (Button) findViewById(R.id.btn9);
        Button delBtn = (Button) findViewById(R.id.btnDelete);
        Button clrBtn = (Button) findViewById(R.id.btnClear);
        Button commaBtn = (Button) findViewById(R.id.btnComma);
        Button enterBtn = (Button) findViewById(R.id.btnEnter);
        Button multBtn = (Button) findViewById(R.id.btnMult);
        Button subBtn = (Button) findViewById(R.id.btnSub);
        Button somaBtn = (Button) findViewById(R.id.btnSoma);
        Button divBtn = (Button) findViewById(R.id.btnDiv);

        zeroBtn.setOnClickListener(this);
        umBtn.setOnClickListener(this);
        doisBtn.setOnClickListener(this);
        tresBtn.setOnClickListener(this);
        quatroBtn.setOnClickListener(this);
        cincoBtn.setOnClickListener(this);
        seisBtn.setOnClickListener(this);
        seteBtn.setOnClickListener(this);
        oitoBtn.setOnClickListener(this);
        noveBtn.setOnClickListener(this);
        delBtn.setOnClickListener(this);
        clrBtn.setOnClickListener(this);
        commaBtn.setOnClickListener(this);
        enterBtn.setOnClickListener(this);
        multBtn.setOnClickListener(this);
        subBtn.setOnClickListener(this);
        somaBtn.setOnClickListener(this);
        divBtn.setOnClickListener(this);
    }
}