package com.thiago.HP12C;

import android.content.PeriodicSync;

import androidx.lifecycle.ViewModel;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.BiFunction;

public class Calculadora extends ViewModel {
    public static final int MODO_EDITANDO = 0;
    public static final int MODO_EXIBINDO = 1;
    public static final int MODO_ERRO = 2;

    private double numero;
    private double periodo;
    private double taxa;
    private double PMT;
    private double FV;
    private double PV;
    private String historico;

    private Deque<Double> operandos;
    private int modo = MODO_EXIBINDO;

    public Calculadora() {
        numero = 0;
        operandos = new LinkedList<>();
        periodo=0;
        taxa=0;
        PMT=0;
        FV=0;
        PV =0;
    }

    public void setPeriodo(double periodo) {
        this.periodo = periodo;
        modo = MODO_EXIBINDO;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
        modo = MODO_EXIBINDO;
    }

    public void setPMT(double PMT) {
        this.PMT = PMT;
        modo = MODO_EXIBINDO;
    }

    public void setFV(double FV) {
        this.FV = FV;
        modo = MODO_EXIBINDO;
    }

    public void setPV(double PV) {
        this.PV = PV;
        modo = MODO_EXIBINDO;
    }

    public void setNumero(double numero) {
        this.numero = numero;
        modo = MODO_EDITANDO;
    }

    public String getHistorico() {
        return historico;
    }

    public double getNumero() {
        return numero;
    }

    public int getModo() {
        return modo;
    }

    public double getPeriodo() {
        return periodo;
    }

    public double getTaxa() {
        return taxa;
    }

    public double getPMT() {
        return PMT;
    }

    public double getFV() {
        return FV;
    }

    public double getPV() {
        return PV;
    }

    public void enter() {
        if (modo == MODO_ERRO) {
            modo = MODO_EXIBINDO;
        }
        if (modo == MODO_EDITANDO) {
            operandos.push(numero);
            modo = MODO_EXIBINDO;
        }
    }

    protected void executarOperacao(BiFunction<Double, Double, Double> operacao, String tipo) {
        if (modo == MODO_EDITANDO || modo == MODO_ERRO) {
            enter();
        }
        double op1 = Optional.ofNullable(operandos.pollFirst()).orElse(0.0);
        double op2 = Optional.ofNullable(operandos.pollFirst()).orElse(0.0);
        numero = operacao.apply(op1, op2);
        historico = op2+tipo+op1;
        operandos.push(numero);
    }

    public void soma() {
        executarOperacao(((op1, op2) -> op1 + op2), "+");
    }

    public void subtracao() {
        executarOperacao(((op1, op2) -> op2 - op1),"-");
    }

    public void multiplicacao() {
        executarOperacao(((op1, op2) -> op1 * op2), "*");
    }

    public void prestacoes(){
        double denominador = ((Math.pow((1+taxa),periodo))-1);
        if(denominador==0){modo = MODO_ERRO; resetarValores(); return;}
        if (modo == MODO_EDITANDO || modo == MODO_ERRO) {
            enter();
        }
        double resultado =0;

        resultado = PV *(taxa*(Math.pow((1+taxa),periodo)))/denominador;



        setNumero(resultado);
        operandos.push(numero);
        resetarValores();

    }

    public void valorPresente(){
        double denominador =(Math.pow((1+taxa),periodo));
        if(denominador==0){modo = MODO_ERRO; resetarValores(); return;}
        if (modo == MODO_EDITANDO || modo == MODO_ERRO) {
            enter();
        }
        double resultado =0;
        // cenario 1: PMT NULO
        resultado = FV /(denominador);

        // cenario 2: PMT nao nulo

        setNumero(resultado);
        operandos.push(numero);
        resetarValores();
    }

    public void valorFuturo(){
        if(taxa==0){modo = MODO_ERRO; resetarValores(); return;}
        if (modo == MODO_EDITANDO || modo == MODO_ERRO) {enter();}
        double resultado =0;
        // cenario 1: PMT NULO
        // cenario 2: PMT nao nulo

        resultado = PV *((Math.pow((1+taxa),periodo))) + PMT*(Math.pow(1+taxa, periodo)-1)/taxa;

        setNumero(resultado);
        operandos.push(numero);

        resetarValores();
    }



    public void calculoTaxa(){
        if(PV==0 || periodo==0){modo = MODO_ERRO; resetarValores(); return;}
        if (modo == MODO_EDITANDO || modo == MODO_ERRO) {
            enter();
        }
        double resultado =0;

        // PMT NULO
        resultado =  ((Math.pow((FV/PV),(1/periodo))-1));


        setNumero(resultado);
        operandos.push(numero);
        resetarValores();
    }

    public void calculoPeriodo(){
        double denominador = Math.log(1+taxa);
        if(PV==0 || denominador==0){modo = MODO_ERRO; resetarValores(); return;}
        if (modo == MODO_EDITANDO || modo == MODO_ERRO) {
            enter();
        }
        double resultado =0;
        // PMT nulo
        resultado = Math.log(FV/PV)/denominador;

        setNumero(resultado);
        operandos.push(numero);
        resetarValores();
    }

    public void resetarValores(){
        PV=0;
        FV=0;
        periodo=0;
        taxa=0;
        PMT=0;
    }

    public String gerarHistoricoFinanceiro(){
        return "PV"+"("+PV+")"+"PMT"+"("+PMT+")"+"FV"+"("+FV+")"+"i"+"("+taxa*100+")"+"n"+"("+periodo+")";
    }

    public void divisao() {
        if (modo == MODO_EDITANDO) {
            enter();
        }
        double denominador = Optional.ofNullable(operandos.peek()).orElse(0.0);
        if (denominador == 0) {
            modo = MODO_ERRO;
            return;
        }
        executarOperacao(((op1, op2) -> op2 / op1), "/");
    }
}

