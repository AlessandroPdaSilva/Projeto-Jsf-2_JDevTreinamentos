package jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.DaoGenerico;
import model.Pessoa;
import model.Telefone;

@ViewScoped
@Named(value = "telefoneBean")
public class TelefoneBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	List<Telefone> listaTelefone = new ArrayList<Telefone>();
	Telefone telefone = new Telefone();
	Pessoa pessoa = new Pessoa();
	
	
	@Inject
	DaoGenerico<Telefone> daoTelefone;
	
	@Inject
	DaoGenerico<Pessoa> daoPessoa;
	
	@PostConstruct
	public void init(){
		 
		String idPessoa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("idPessoa");
		 
		Pessoa aux = new Pessoa();
		pessoa = daoPessoa.pesquisar(Long.parseLong(idPessoa), aux);
		
		listaTelefone = daoTelefone.listarTelefones(Telefone.class, pessoa.getId());
		
	}
	
	public String listar() {
		listaTelefone = daoTelefone.listarTelefones(Telefone.class, pessoa.getId());
		return "";
	}
	
	public void salvar(){
		
		telefone.setPessoa(pessoa);
		daoTelefone.editar(telefone);
		
		listar();
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("salvo com sucesso!!"));
	}
	
	public String novo() {
		telefone = new Telefone();
		return "";
	}
	
	public String editar() {
		listar();
		return "";
	}
	
	public String deletar() {
		daoTelefone.deletar(telefone);
		listar();
		return "";
	}
	
	
	
	
	// GET E SET
	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}
	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	
	
}
