package br.com.aula.heranca.sistema;

public class ProdutoPerecivel extends Produto
{
	private String dataVencimento;
	
	public ProdutoPerecivel(int codigo, String nome, double preco, int quantEmEstoque, String dataVencimento)
	{
		super(codigo,nome,preco,quantEmEstoque);
		this.dataVencimento = dataVencimento;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ", Vencimento: " + dataVencimento;
	}
	
	public String getDataVencimento()
	{
		return this.dataVencimento;
	}
}
