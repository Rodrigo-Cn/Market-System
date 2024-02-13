package br.com.aula.heranca.sistema;

import java.util.ArrayList;

public class Caixa {
    public void setNomeMercado(String nomeMercado) {
        this.nomeMercado = nomeMercado;
    }
    private int codigo;
    private String nomeMercado;
    private double saldo;
    private ArrayList<Venda> vendas = new ArrayList<>();
    public Caixa(){}
    public Caixa(int codigo, String nomeMercado){
        this.codigo = codigo;
        this.nomeMercado = nomeMercado;
        this.saldo = 0;
    }
    public void realizarVenda(Venda venda){
        this.saldo += venda.getTotal();
        vendas.add(venda);
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setVendas(ArrayList<Venda> vendas) {
        this.vendas = vendas;
    }

    public void zerarCaixa(){
        this.saldo = 0;
    }

    public String getNomeMercado() {
        return nomeMercado;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getCodigo() {
        return codigo;
    }
}
