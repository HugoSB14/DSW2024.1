package bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.CanetaDao;
import entidade.Caneta;

@ViewScoped
@ManagedBean
public class CanetaBean {
	private Caneta caneta = new Caneta();
	private List<Caneta> lista = new ArrayList<Caneta>();
	
	public String salvar() {
		CanetaDao.salvar(caneta);
		caneta = new Caneta();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso! Caneta salva.", "Caneta salva.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return null;
	}
	
	public String excluir(Caneta caneta) {
		CanetaDao.excluir(caneta);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso! Caneta excluida.", "Caneta excluida.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return null;
	}
	
	  public void selecionar(Caneta caneta){
		
	    FacesMessage msg = new FacesMessage("Selecionado: " + caneta.getMarca() + " - " + caneta.getModelo() + " - " + caneta.getCor());
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	  
	  public void contarCanetasPorCor() {
	        Map<String, Long> contagens = CanetaDao.contarCanetasPorCor();
	        StringBuilder msg = new StringBuilder();
	        for (Map.Entry<String, Long> entry : contagens.entrySet()) {
	            msg.append(entry.getValue()).append(" ").append(entry.getKey()).append(" ");
	        }
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg.toString()));
	    }

	public Caneta getCaneta() {
		return caneta;
	}

	public void setCaneta(Caneta caneta) {
		this.caneta = caneta;
	}

	public List<Caneta> getLista() {
		lista = CanetaDao.listar();
		return lista;
	}

	public void setLista(List<Caneta> lista) {
		this.lista = lista;
	}
	

}
