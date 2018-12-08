package controle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class GenericBean {
	
	protected Boolean editar = false;
	
	
	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public void mensagem(String sumario, String detalhe){
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(sumario, detalhe));
	}
	
	public void mensagem(String detalhe){
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(detalhe));
	}
	
	
}
