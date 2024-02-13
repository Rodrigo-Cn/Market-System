package br.com.aula.heranca.sistema;

public class ProdutoNaoPerecivel extends Produto
{
	private String material;
	
	public ProdutoNaoPerecivel (int codigo, String nome, double preco, int quantEmEstoque, String material)
	{
		super(codigo,nome,preco,quantEmEstoque);
		this.material = material;
	}
	
	public String getMaterial()
	{
		return this.material;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + ", Material: " + material;
	}
}

