package service;

import javax.ejb.Stateless;

import modelo.Venda;

@Stateless
public class VendaService extends GenericService<Venda>{

	public VendaService() {
		super(Venda.class);
	}
	
}
