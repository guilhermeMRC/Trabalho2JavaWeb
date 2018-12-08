package controle;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Produto;
import service.ProdutoService;

@ManagedBean
@ViewScoped
public class ProdutoBean extends GenericBean {
		
		@EJB
		ProdutoService produtoservice;
		
		Produto produto = new Produto();
		
		String filtro = "";
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		@PostConstruct
		public void init(){
			atualizarLista();
		}
		
		
		public void gravar(){
			if(getEditar()){
				produtoservice.merge(produto);
				mensagem("Sucesso!", "Produto Atualizado!");
			}else{
				produtoservice.create(produto);
			mensagem("Sucesso!", "Produto Gravado!");
			}
			setEditar(false);
			produto = new Produto();
			atualizarLista();
		}
		
		public void editar(Produto p){
			produto = p;
			setEditar(true);
		}
		
		public void excluir(Produto p){
			produtoservice.remove(p);
			atualizarLista();
		}
		
		public void atualizarLista(){
			produtos = produtoservice.listAll();
		}
		
		public void filtraPorNome() {
			setProdutos(produtoservice.filtrarFuncionariosPorNome(filtro));
		}
		
		public void filtrarEstoqueBaixo() {
			setProdutos(produtoservice.filtrarEstoqueBaixo());
		}
		
		public String getFiltro() {
			return filtro;
		}

		public void setFiltro(String filtro) {
			this.filtro = filtro;
		}

		public ProdutoService getProdutoservice() {
			return produtoservice;
		}

		public void setProdutoservice(ProdutoService produtoservice) {
			this.produtoservice = produtoservice;
		}

		public Produto getProduto() {
			return produto;
		}

		public void setProduto(Produto produto) {
			this.produto = produto;
		}

		public List<Produto> getProdutos() {
			return produtos;
		}

		public void setProdutos(List<Produto> produtos) {
			this.produtos = produtos;
		}
		
		
	}
		