package br.com.aula.heranca.sistema;

public class Produto 
{
	private int codigo;
	private String nome;
	private double preco;
	private int quantEmEstoque;
	
	public Produto(int codigo, String nome, double preco, int quantEmEstoque)
	{
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
		this.quantEmEstoque = quantEmEstoque;
	}
	
	public void adicionarEstoque(int quantidade)
	{
		this.quantEmEstoque += quantidade;
		System.out.println("NOVA QUANTIDADE: " + quantEmEstoque);
	}
	
	@Override
	public String toString()
	{
		return "Código: " + codigo + ", Nome: " + nome + ", Preco: R$" + preco + ", Estoque: " + quantEmEstoque;
	}
	public void setQuantEmEstoque(int quantEmEstoque) {
		this.quantEmEstoque = quantEmEstoque;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantEmEstoque() {
		return quantEmEstoque;
	}

}
