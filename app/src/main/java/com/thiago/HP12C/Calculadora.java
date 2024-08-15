package com.thiago.HP12C;

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
    private Deque<Double> operandos;
    private int modo = MODO_EXIBINDO;

    public Calculadora() {
        numero = 0;
        operandos = new LinkedList<>();
    }

    public void setNumero(double numero) {
        this.numero = numero;
        modo = MODO_EDITANDO;
    }

    public double getNumero() {
        return numero;
    }

    public int getModo() {
        return modo;
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

    protected void executarOperacao(BiFunction<Double, Double, Double> operacao) {
        if (modo == MODO_EDITANDO || modo == MODO_ERRO) {
            enter();
        }
        double op1 = Optional.ofNullable(operandos.pollFirst()).orElse(0.0);
        double op2 = Optional.ofNullable(operandos.pollFirst()).orElse(0.0);
        numero = operacao.apply(op1, op2);
        operandos.push(numero);
    }

    public void soma() {
        executarOperacao((op1, op2) -> op1 + op2);
    }

    public void subtracao() {
        executarOperacao((op1, op2) -> op2 - op1);
    }

    public void multiplicacao() {
        executarOperacao((op1, op2) -> op1 * op2);
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
        executarOperacao((op1, op2) -> op2 / op1);
    }
}

