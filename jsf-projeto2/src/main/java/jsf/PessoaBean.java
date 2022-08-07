package jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.DaoGenerico;
import model.Pessoa;
import model.Telefone;

@ViewScoped
@Named(value = "pessoaBean")
public class PessoaBean implements Serializable{
	 
	private static final long serialVersionUID = 1L;

	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
	
	@Inject
	private DaoGenerico<Pessoa> daoPessoa;

	public String salvar() {
		pessoa = daoPessoa.editar(pessoa);
		lista();
		
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("salvo com sucesso!!"));
		return "";
	}
	
	public String editar() {
		
		lista();
		return "";
	}
	
	public String novo() {
		pessoa = new Pessoa();
		return "";
	}
	
	//DELETAR
	public String deletar(){
		
		if(pessoa.getListaTelefone() != null) {
			daoPessoa.deletarTelefones(pessoa, new Telefone());
		}
		
		daoPessoa.deletar(pessoa);
		novo();
		lista();
		
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("deletado com sucesso!!"));
		return "";
	}
	
	//LISTAR
	@PostConstruct
	public void lista(){
		listaPessoa = daoPessoa.listar(Pessoa.class,10);
	}

	
	// GET E SET
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa p) {
		this.pessoa = p;
	}

	public List<Pessoa> getListaPessoa() {
		return listaPessoa;
	}

	public void setListaPessoa(List<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}
	
	
}
