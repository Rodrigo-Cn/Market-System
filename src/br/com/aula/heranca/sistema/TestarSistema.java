package br.com.aula.heranca.sistema;
import br.com.aula.heranca.dao.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TestarSistema
{
	public static VendaDao vendaDao = new VendaDao();
	public static ItemVendaDao itemVendaDao = new ItemVendaDao();
    public static ProdutoDao produtoDao = new ProdutoDao();
	public static ClienteDao clienteDao = new ClienteDao();
	public static CaixaDao caixaDao = new CaixaDao();
	public static ArrayList<Produto> produtos = new ArrayList<>();
	public static ArrayList<Cliente> clientes = new ArrayList<>();
	public static Scanner scanner = new Scanner(System.in);
	public static Caixa caixa = new Caixa();
	public static void main(String[] args)
	{
        produtos = produtoDao.ConsultarProduto();
		clientes = clienteDao.ConsultarClientes();
		caixa.setVendas(vendaDao.ConsultarVendas());
		caixa = caixaDao.ConsultarCaixa();
        menu();
	}
	public static void menu(){
			System.out.println("#### Sistema do "+caixa.getNomeMercado()+" ####");
			System.out.println("1 - Gerenciar Clientes");
			System.out.println("2 - Gerenciar Produtos");
			System.out.println("3 - Realizar Venda");
			System.out.println("4 - Saldo do Caixa");
			System.out.println("5 - Sair");
			System.out.println("ESCOLHA UMA OPÇÃO: ");
			int escolha = scanner.nextInt();
			switch (escolha){
				case 1:
					limparTela();
					gerenciarClientes();
					break;
				case 2:
					limparTela();
					gerenciarProdutos();
					break;
				case 3:
					limparTela();
					scanner.nextLine();
					realizarVenda();
					break;
				case 4:
					limparTela();
					System.out.println("||| Saldo em caixa é "+caixa.getSaldo()+"R$ |||");
					menu();
					break;
				default:
					System.exit(0);
			}
	}

	/***
	 * Gerenciar Cliente
	 */
	public static void gerenciarClientes(){
		System.out.println("#### Gerenciar Clientes ####");
		System.out.println("1 - Adicionar Cliente");
		System.out.println("2 - Editar Cliente");
		System.out.println("3 - Excluir Cliente");
		System.out.println("4 - Consultar Clientes");
		System.out.println("5 - Voltar para o Menu");
		System.out.println("ESCOLHA UMA OPÇÃO: ");
		int escolha = scanner.nextInt();
		scanner.nextLine();

		switch (escolha) {
			case 1: {
				adicionarCliente();
				break;
			}
			case 2: {
				editarCliente();
				break;
			}
			case 3: {
				excluirCliente();
				break;
			}
			case 4: {
				consultarClientes();
				break;
			}
			case 5: {
				limparTela();
				menu();
			}
		}
	}
	/***
	 * Opções do menu do cliente
	 */
	public static void adicionarCliente(){
		limparTela();
		System.out.println("#### Cadastrar Cliente ###");
		System.out.println("NOME: ");
		String nomeCliente = scanner.nextLine();
		System.out.println("CPF: ");
		String cpfCliente = scanner.nextLine();
		System.out.println("ENDEREÇO: ");
		String enderecoCliente = scanner.nextLine();
		Cliente novoCliente = new Cliente(nomeCliente,cpfCliente,enderecoCliente);
		clientes.add(novoCliente);
		clienteDao.cadastrarCliente(novoCliente);
		limparTela();
		System.out.println("CLIENTE INSERIDO COM SUCESSO !!!\n");
		gerenciarClientes();
	}
	public static void editarCliente() {
		System.out.println("###### EDITAR CLIENTE ######");
		System.out.println("DIGITE O CPF DO CLIENTE: ");
		String cpfEditar = scanner.nextLine();
		int i = 0;
		for (Cliente cliente:clientes){
			if (cliente.getCpf().equals(cpfEditar)){
				clienteDao.deletarCliente(cliente);
				System.out.println("NOME: ");
				String nomeCliente = scanner.nextLine();
				cliente.setNome(nomeCliente);
				System.out.println("CPF: ");
				String cpfCliente = scanner.nextLine();
				cliente.setCpf(cpfCliente);
				System.out.println("ENDEREÇO: ");
				String enderecoCliente = scanner.nextLine();
				cliente.setEndereco(enderecoCliente);
				clienteDao.cadastrarCliente(cliente);
			}
			i++;
			if (i == clientes.size()){
				System.out.println("CPF INVÁLIDO !!!");
				gerenciarClientes();
			}
		}
		limparTela();
		System.out.println("CLIENTE EDITADO COM SUCESSO !!!");
		gerenciarClientes();
	}
	public static void excluirCliente() {
		System.out.println("###### EXCLUIR CLIENTE ######");
		System.out.println("DIGITE O CPF DO CLIENTE: ");
		String cpfExcluir = scanner.nextLine();

		Iterator<Cliente> iterator = clientes.iterator();
		while (iterator.hasNext()) {
			Cliente cliente = iterator.next();
			if (cliente.getCpf().equals(cpfExcluir)) {
				clienteDao.deletarCliente(cliente);
				iterator.remove();
				limparTela();
				System.out.println("CLIENTE EXCLUIDO COM SUCESSO !!!");
				gerenciarClientes();
				return;
			}
		}
		System.out.println("CLIENTE NÃO ENCONTRADO. VERIFIQUE O CPF.");
		limparTela();
		gerenciarClientes();
	}
	public static void consultarClientes(){
		System.out.println("###### CONSULTAR CLIENTES ######");
		for (Cliente cliente : clientes) {
			System.out.println(cliente.toString());
		}
		scanner.nextLine();
		System.out.println();
		gerenciarClientes();
	}

	/***
	 * Gerenciar Produto
	 */
	public static void gerenciarProdutos(){

			System.out.println("#### Gerenciar Produtos ####");
			System.out.println("1 - Inserir Produto Perecível");
			System.out.println("2 - Inserir Produto Não Perecível");
			System.out.println("3 - Consultar Produtos");
			System.out.println("4 - Adicionar Estoque");
			System.out.println("5 - Voltar para o Menu");
			System.out.println("ESCOLHA UMA OPÇÃO: ");
			int escolha = scanner.nextInt();
			scanner.nextLine();

			switch (escolha) {
				case 1: {
					inserirProdutoPerecivel();
					break;
				}
				case 2: {
					inserirProdutoNaoPerecivel();
					break;
				}
				case 3: {
					consultarProdutos();
					break;
				}
				case 4: {
					adicionarEstoque();
					break;
				}
				case 5: {
					limparTela();
					menu();
				}
			}
	}

	/**
	 * Procedimentos de Gerenciar Produto
	 */
	public static void inserirProdutoPerecivel(){
		System.out.println("### INSERIR PRODUTO PERECÍVEL ###");
		System.out.println("CÓDIGO: ");
		int codigoPerecivel = scanner.nextInt();
		scanner.nextLine();
		System.out.print("NOME: ");
		String nomePerecivel = scanner.nextLine();
		System.out.print("PREÇO: R$");
		double precoPerecivel = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("QUANTIDADE EM ESTOQUE: ");
		int quantPerecivel = scanner.nextInt();
		scanner.nextLine();
		System.out.print("DATA DE VENCIMENTO: ");
		String dataVencimento = scanner.nextLine();
		ProdutoPerecivel produtoPerecivel = new ProdutoPerecivel
				(codigoPerecivel, nomePerecivel, precoPerecivel, quantPerecivel, dataVencimento);
		produtos.add(produtoPerecivel);
        produtoDao.CadastrarProdutoPerecivel(produtoPerecivel);
		System.out.println("PRODUTO PERECÍVEL INSERIDO\n");
		gerenciarProdutos();
	}
	public static void inserirProdutoNaoPerecivel(){
		System.out.println("### INSERIR PRODUTO NÃO PERECÍVEL ###");
		System.out.println("CÓDIGO: ");
		int codigoNaoPerecivel = scanner.nextInt();
		scanner.nextLine();
		System.out.print("NOME: ");
		String nomeNaoPerecivel = scanner.nextLine();
		System.out.print("PREÇO: R$");
		double precoNaoPerecivel = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("QUANTIDADE EM ESTOQUE: ");
		int quantNaoPerecivel = scanner.nextInt();
		scanner.nextLine();
		System.out.print("MATERIAL: ");
		String material = scanner.nextLine();
		ProdutoNaoPerecivel produtoNaoPerecivel = new ProdutoNaoPerecivel
				(codigoNaoPerecivel, nomeNaoPerecivel, precoNaoPerecivel, quantNaoPerecivel, material);
		produtos.add(produtoNaoPerecivel);
        produtoDao.CadastrarProdutoNaoPerecivel(produtoNaoPerecivel);
		System.out.println("PRODUTO NÃO PERECÍVEL INSERIDO\n");
		gerenciarProdutos();
	}
	public static void consultarProdutos(){
		System.out.println("###### CONSULTAR PRODUTOS ######");
		for (Produto produto : produtos) {
			if (produto instanceof ProdutoPerecivel) {
				ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;
				System.out.println(produtoPerecivel.toString());
			}
			if (produto instanceof ProdutoNaoPerecivel) {
				ProdutoNaoPerecivel produtoNaoPerecivel = (ProdutoNaoPerecivel) produto;
				System.out.println(produtoNaoPerecivel.toString());
			}
		}
		scanner.nextLine();
		System.out.println();
		gerenciarProdutos();
	}
	public static void adicionarEstoque(){
		System.out.println("###### ADICIONAR ESTOQUE ######");
		System.out.println("DIGITE O CÓDIGO DO PRODUTO: ");
		int codigoEstoque = scanner.nextInt();
		scanner.nextLine();
		System.out.println("DIGITE A QUANTIDADE A SER ADICIONADA: ");
		int quantidadeAdicionar = scanner.nextInt();
		scanner.nextLine();
		for (Produto produto:produtos){
			if (produto.getCodigo() == codigoEstoque){
				produto.adicionarEstoque(quantidadeAdicionar);
                produtoDao.adicionarEstoque(produto.getCodigo(),quantidadeAdicionar);
			}
		}
		gerenciarProdutos();
	}

	/***
	 * RealizarVenda
	 */
	public static void realizarVenda(){

		Venda novaVenda = new Venda(verificarCliente(),vendaDao.retornarCodigo());
		vendaDao.cadastrarVenda(novaVenda,novaVenda.getCliente().getCpf(),caixa.getCodigo());

		int escolha = 1;

		while (escolha !=5){
			System.out.println("### Adicione Produtos à compra de "+novaVenda.getCliente().getNome()+" ###");
			System.out.println("1 - Adicionar Produto a lista");
			System.out.println("2 - Vizualizar produtos da lista");
			System.out.println("3 - Finalizar Compras");
			System.out.println("ESCOLHA UMA OPÇÃO: ");
			escolha = scanner.nextInt();
			scanner.nextLine();
			switch (escolha){
				case 1:
					novaVenda.adicionarItem(adicionarItemPedido(novaVenda));
					escolha = 1;
					break;
				case 2:
					listarProdutosDaVenda(novaVenda);
					escolha = 1;
					break;
				case 3:
					escolha = 5;
					break;
			}
		}
		finalizarCompra(novaVenda);
	}
	/***
	 * Procedimentos da Venda
	 */
	public static Cliente verificarCliente(){
		Cliente clienteCompra = new Cliente();

		System.out.println("Digite o cpf do cliente: ");
		String cpfCliente = scanner.nextLine();

		for (Cliente cliente:clientes){
			if (cliente.getCpf().equals(cpfCliente)){
				clienteCompra = cliente;
			}
		}
		return clienteCompra;
	}
	public static ItemVenda adicionarItemPedido(Venda venda) {
		ItemVenda item = new ItemVenda();
		System.out.println("Digite o código do produto: ");
		int codigo = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Digite a quantidade que deseja: ");
		int quantidade = scanner.nextInt();
		scanner.nextLine();

		for (Produto produto:produtos) {
			if (produto.getCodigo() == codigo) {
				item.setProduto(produto);
				break;
			}
		}

		while (item.getProduto() == null) {
			System.out.println("Digite um código válido: ");
			codigo = scanner.nextInt();
			scanner.nextLine();
			for (Produto produto:produtos) {
				if (produto.getCodigo() == codigo) {
					item.setProduto(produto);
					break;
				}
			}
		}

		item.getProduto().setQuantEmEstoque(item.getProduto().getQuantEmEstoque()-quantidade);

		while (item.getQuantidade()>item.getProduto().getQuantEmEstoque()){
			System.out.println("Pedido maior que estoque");
			System.out.println("Digite uma quantidade válida: ");
			quantidade = scanner.nextInt();
			scanner.nextLine();
		}
		item.setQuantidade(quantidade);
		itemVendaDao.cadastrarItemPedido(venda.getCodigo(),codigo,item);
		return item;
	}

	public static void listarProdutosDaVenda(Venda venda){
		System.out.println("#### Lista de Produtos ####");
		for (ItemVenda item: venda.getItensVendas()){
			System.out.println(item.toString());
		}
		scanner.nextLine();
		limparTela();
	}
	public static void finalizarCompra(Venda venda){
		System.out.println("### Deseja Finalizar a Compra ? ###");
		System.out.println("Total da Compra: "+venda.getTotal()+"R$");
		System.out.println("1 - Sim");
		System.out.println("2 - Cancelar");
		System.out.println("##################################");
		int escolha = scanner.nextInt();
		if (escolha == 1){
			caixaDao.adicionarSaldo(caixa.getSaldo()+venda.getTotal());
			vendaDao.inserirTotal(venda.getTotal(),venda.getCodigo());
			for (ItemVenda itemVenda : venda.getItensVendas()){
				produtoDao.retirarEstoque(itemVenda.getProduto().getCodigo(), (itemVenda.getProduto().getQuantEmEstoque()));
			}
			caixa.realizarVenda(venda);
			limparTela();
			System.out.println("COMPRA REALIZADA COM SUCESSO !!!");
			menu();
		}
		else {
			itemVendaDao.deletarItensPedidos(venda.getCodigo());
			vendaDao.deletarVenda(venda.getCodigo());
			for (ItemVenda itemVenda:venda.getItensVendas()){
				itemVenda.getProduto().setQuantEmEstoque(itemVenda.getProduto().getQuantEmEstoque()+itemVenda.getQuantidade());
			}
			menu();
		}
	}
	/***
	 * LimparTela
	 */
	public static void limparTela(){
		for (int i=0; i<20; i++){
			System.out.println("\n");
		}
	}
}
