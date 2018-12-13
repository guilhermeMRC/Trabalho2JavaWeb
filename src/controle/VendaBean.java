package controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.codehaus.jackson.map.ext.JodaSerializers.LocalDateTimeSerializer;
import org.jboss.classpool.spi.SystemClassPool;
import org.primefaces.component.calendar.Calendar;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;
import com.sun.tools.xjc.reader.xmlschema.ExpressionBuilder;

import antlr.debug.SyntacticPredicateListener;
import modelo.ItemVenda;
import modelo.Pagamento;
import modelo.Produto;
import modelo.TipoPagamento;
import modelo.Venda;
import service.ProdutoService;

@ManagedBean
@ViewScoped
public class VendaBean {

	@EJB
	ProdutoService produtoservice;
	
	private List<Produto> produtos = new ArrayList<>();
	private List<Produto> produtosFiltrados;
	
	private List<ItemVenda> itensVenda = new ArrayList<>();
	
	private ItemVenda itemVenda = new ItemVenda();
	private Produto produto = new Produto();
	private Venda venda = new Venda();
	private Pagamento pagamento = new Pagamento();
	private TipoPagamento tipoDinheiro = TipoPagamento.DINHEIRO;
	private TipoPagamento tipoCart�o = TipoPagamento.CARTAO;
	
	private boolean exibirCart�o = false;
	private boolean exibirDinheiro = false;
	
	@PostConstruct
	public void init(){
		
		atualizarLista();
		itensVenda = new ArrayList<>();
		pagamento.setQuantidadeParcelas(1);
		
	}
	
	public void AddCarrinho(Produto produtoEstoque) {
		
		produto = produtoEstoque;
		produtos.remove(produtoEstoque);
		
		itemVenda = new ItemVenda();
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(1);
		itemVenda.setValorUnitario(produtoEstoque.getPreco());
		itensVenda.add(itemVenda);
		
	}
	
	public Double valorTotal(List<ItemVenda> itensCarrinho) {
		
		Double valorTotal = 0D; 
		for(ItemVenda iv : itensCarrinho) {
			
			valorTotal = valorTotal + iv.getTotalItem();
		}
		
		//setar o valor direto na classe pagamento
		pagamento.setValorTotal(valorTotal);
		return pagamento.getValorTotal();
	}
	
	public void atualizarLista(){
		produtos.clear();
		produtos = produtoservice.listAll();
	}
	
	public void escolhaPagamentoCartao(TipoPagamento tipoPagamento) {
		
		
		switch (tipoPagamento.toString()) {
		case "CARTAO":
			
			exibirCart�o = true;
			exibirDinheiro = false;
			break;
			
		case "DINHEIRO":
			
			exibirCart�o = false;
			exibirDinheiro = true;
			break;
			
		default:
			
			exibirCart�o = false;
			exibirDinheiro = false;
			break;
		}
		
	}
	
	public void gravarVenda() {
		
		System.out.println("");
	}
	
	public boolean isExibirCart�o() {
		return exibirCart�o;
	}

	public void setExibirCart�o(boolean exibirCart�o) {
		this.exibirCart�o = exibirCart�o;
	}

	public boolean isExibirDinheiro() {
		return exibirDinheiro;
	}

	public void setExibirDinheiro(boolean exibirDinheiro) {
		this.exibirDinheiro = exibirDinheiro;
	}

	public TipoPagamento getTipoDinheiro() {
		return tipoDinheiro;
	}

	public void setTipoDinheiro(TipoPagamento tipoDinheiro) {
		this.tipoDinheiro = tipoDinheiro;
	}

	public TipoPagamento getTipoCart�o() {
		return tipoCart�o;
	}

	public void setTipoCart�o(TipoPagamento tipoCart�o) {
		this.tipoCart�o = tipoCart�o;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public ItemVenda getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
}