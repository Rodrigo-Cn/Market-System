package br.com.aula.heranca.sistema;

import java.util.ArrayList;

public class Venda {
    private int codigo;
    private Cliente cliente;
    private ArrayList<ItemVenda> itensVendas = new ArrayList<>();
    private double total;
    public Venda(Cliente cliente, int codigo){
        this.cliente = cliente;
        this.codigo = codigo;
    }
    public Venda(int codigo, double total){
        this.codigo = codigo;
        this.total = total;
    }
    public void adicionarItem(ItemVenda itemVenda){
        itensVendas.add(itemVenda);
        this.total += itemVenda.getSubtotal();
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ArrayList<ItemVenda> getItensVendas() {
        return itensVendas;
    }
    public double getTotal() {
        return total;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public int getCodigo() {
        return codigo;
    }
}
