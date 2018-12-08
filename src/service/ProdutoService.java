package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import modelo.Produto;

@Stateless
public class ProdutoService extends GenericService<Produto> {
	
	public ProdutoService() {
		super(Produto.class);
	}
	
	public List<Produto> filtrarFuncionariosPorNome(String nome){
		final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Produto> cQuery = cb.createQuery(Produto.class);
		final Root<Produto> root = cQuery.from(Produto.class);
		Expression<String> nomeExp = root.get("nome");
		Expression<String> codigoExp = root.get("codigo");
		
		cQuery.select(root);
		cQuery.where(cb.or(cb.like(nomeExp, "%"+ nome + "%"),cb.like(codigoExp, "%"+ nome + "%")));
		cQuery.orderBy(cb.asc(nomeExp));
		
		List<Produto> nomes = getEntityManager().createQuery(cQuery).getResultList();
		
		return nomes;	
	}
	
	public List<Produto> filtrarEstoqueBaixo(){
		
		final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Produto> cQuery = cb.createQuery(Produto.class);
		final Root<Produto> root = cQuery.from(Produto.class);
		Expression<Integer> quantidadeExp = root.get("quantidade");
		
		cQuery.select(root);
		cQuery.where(cb.lessThanOrEqualTo(quantidadeExp, 5));
		
		cQuery.orderBy(cb.asc(quantidadeExp));
		
		List<Produto> nomes = getEntityManager().createQuery(cQuery).getResultList();
		
		return nomes;
	}

}
