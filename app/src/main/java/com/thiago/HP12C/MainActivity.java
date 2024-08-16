package com.thiago.HP12C;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText visor;
    TextView historicoView;

    Calculadora calc = new Calculadora();
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
        // *para achar a atividade atual e não dar

        // calculadora = new ViewModelProvider(this).get(Calculadora.class);
        //
        inicializaBotoes();
        visor = (EditText)findViewById(R.id.visor);
        historicoView = (TextView) findViewById(R.id.historico);


    }

    @Override
    public void onClick(View view) {
        if(calc.getModo()==1){limpaVisor();}// 1 = Modo Exibindo
        double numero;
        if(calc.getModo()==2){
            limpaVisor();
            calc = new Calculadora();
        }
        int id = view.getId();

        if (id == R.id.btn0) {

            numero = converteStringDouble(atualizaVisor("0"));
            calc.setNumero(numero);
            Log.i("teste", "0");
        } else if (id == R.id.btn1) {
            numero = converteStringDouble(atualizaVisor("1"));
            calc.setNumero(numero);
            Log.i("teste", "1");
        } else if (id == R.id.btn2) {
            numero = converteStringDouble(atualizaVisor("2"));
            calc.setNumero(numero);
            Log.i("teste", "2");
        } else if (id == R.id.btn3) {
            numero = converteStringDouble(atualizaVisor("3"));
            calc.setNumero(numero);
            Log.i("teste", "3");
        } else if (id == R.id.btn4) {
            numero = converteStringDouble(atualizaVisor("4"));
            calc.setNumero(numero);
            Log.i("teste", "4");
        } else if (id == R.id.btn5) {
            numero = converteStringDouble(atualizaVisor("5"));
            calc.setNumero(numero);
            Log.i("teste", "5");
        } else if (id == R.id.btn6) {
            numero = converteStringDouble(atualizaVisor("6"));
            calc.setNumero(numero);
            Log.i("teste", "6");
        } else if (id == R.id.btn7) {
            numero = converteStringDouble(atualizaVisor("7"));
            calc.setNumero(numero);
            Log.i("teste", "7");
        } else if (id == R.id.btn8) {
            numero = converteStringDouble(atualizaVisor("8"));
            calc.setNumero(numero);
            Log.i("teste", "8");
        } else if (id == R.id.btn9) {
            numero = converteStringDouble(atualizaVisor("9"));
            calc.setNumero(numero);
            Log.i("teste", numero+"");
        } else if (id == R.id.btnComma) {
            numero = converteStringDouble(atualizaVisor(","));

            Log.i("teste", ",");
        } else if (id == R.id.btnEnter) {
            calc.enter();
            visor.setTextColor(Color.parseColor("#33FF88"));
            Log.i("teste", "enter()");
        } else if (id == R.id.btnClear) {
            limpaVisor();
            calc = new Calculadora();
            Log.i("teste", "Clear");
        } else if (id == R.id.btnDelete) {
            numero = converteStringDouble(deletaEntrada());
            calc.setNumero(numero);
            Log.i("teste", "Del");
        } else if (id == R.id.btnMult) {
            calc.multiplicacao();
            limpaVisor();
            atualizaVisor(String.valueOf(calc.getNumero()));
            imprimeHistorico(calc.getHistorico());
            // visor.setTextColor(Color.parseColor("#33EE88"));
            Log.i("teste", "x");
        } else if (id == R.id.btnDiv) {
            calc.divisao();
            limpaVisor();
            atualizaVisor(String.valueOf(calc.getNumero()));
            if(calc.getModo()==2){
                visor.setText("ERRO");
            }else{
                imprimeHistorico(calc.getHistorico());
            }
            Log.i("teste", "/");
        } else if (id == R.id.btnSoma) {
            calc.soma();
            limpaVisor();
            atualizaVisor(String.valueOf(calc.getNumero()));
            imprimeHistorico(calc.getHistorico());
            Log.i("teste", "+");
        } else if (id == R.id.btnSub) {
            calc.subtracao();
            limpaVisor();
            atualizaVisor(String.valueOf(calc.getNumero()));
            imprimeHistorico(calc.getHistorico());
            Log.i("teste", "-");
        } else if (id == R.id.btnPv) {
            Log.i("Teste2", calc.getModo()+"");
            if(calc.getModo()==1) {
                imprimeHistorico(calc.gerarHistoricoFinanceiro());
                calc.valorPresente();
                // se der erro nesse meio tempo, print ERRO
                atualizaVisor(String.valueOf(calc.getModo()==2 ? "ERRO":calc.getNumero()));


            }else{calc.setPV(converteStringDouble(visor.getText().toString()));}
        }else if (id == R.id.btnFv) {
            if(calc.getModo()==1) {
                imprimeHistorico(calc.gerarHistoricoFinanceiro());
                calc.valorFuturo();
                // se der erro nesse meio tempo, print ERRO
                atualizaVisor(String.valueOf(calc.getModo()==2 ? "ERRO":calc.getNumero()));


            }else{calc.setFV(converteStringDouble(visor.getText().toString()));}
            Log.i("teste", "-");
        }else if (id == R.id.btnPmt) {
            if(calc.getModo()==1) {
                imprimeHistorico(calc.gerarHistoricoFinanceiro());
                calc.prestacoes();
                // se der erro nesse meio tempo, print ERRO
                atualizaVisor(String.valueOf(calc.getModo()==2 ? "ERRO":calc.getNumero()));


            }else{calc.setPMT(converteStringDouble(visor.getText().toString()));}


            Log.i("teste", "-");
        }else if (id == R.id.btnN) {
            if(calc.getModo()==1) {
                imprimeHistorico(calc.gerarHistoricoFinanceiro());
                calc.calculoPeriodo();
                // se der erro nesse meio tempo, print ERRO
                atualizaVisor(String.valueOf(calc.getModo()==2 ? "ERRO":calc.getNumero()));


            }else{calc.setPeriodo(converteStringDouble(visor.getText().toString()));}
        }else if (id == R.id.btnI) {
            if(calc.getModo()==1) {
                imprimeHistorico(calc.gerarHistoricoFinanceiro());
                calc.calculoTaxa();
                // se der erro nesse meio tempo, print ERRO
                atualizaVisor(String.valueOf(calc.getModo()==2 ? "ERRO":calc.getNumero()));


            }else{calc.setTaxa(converteStringDouble(visor.getText().toString())/100.00);}

        }
        if(calc.getModo()==0){visor.setTextColor(Color.parseColor("#112233"));}// 0 = Modo Editando
        if(calc.getModo()==1){visor.setTextColor(Color.parseColor("#00FFAA"));}// 1 = Modo Exibindo
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
        Button pvBtn = (Button) findViewById(R.id.btnPv);
        Button pmtBtn = (Button) findViewById(R.id.btnPmt);
        Button fvBtn = (Button) findViewById(R.id.btnFv);
        Button iBtn = (Button) findViewById(R.id.btnI);
        Button nBtn = (Button) findViewById(R.id.btnN);

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
        pvBtn.setOnClickListener(this);
        pmtBtn.setOnClickListener(this);
        fvBtn.setOnClickListener(this);
        iBtn.setOnClickListener(this);
        nBtn.setOnClickListener(this);
    }

    private String atualizaVisor(String i){
        EditText editText = (EditText)findViewById(R.id.visor);
        String textoAtual =editText.getText().toString(); // pega o valor do visor
        String novoValor;
        // previne a adição de multiplos zeros à esquerda
        if(!i.equals(",")){
            novoValor = textoAtual.equals("0")? i:textoAtual + i;
            editText.setText(novoValor);
            return novoValor;
        }

        novoValor = textoAtual.contains(",") ? textoAtual:textoAtual.concat(",");
        editText.setText(novoValor);
        Log.i("teste", novoValor);
        return novoValor;
    }

    private String deletaEntrada(){
        String texto = visor.getText().toString();
        String novoTexto = texto.length()<2? "0":texto.substring(0, texto.length()-1);
        visor.setText(novoTexto);
        return novoTexto;
    }

    private void limpaVisor(){
        visor.setText("0");
    }

    private Double converteStringDouble(String s){

        return Double.parseDouble(s.replace(",","."));
    }

    private void imprimeHistorico(String historico){
        historicoView.setText(historico);
    }


}
