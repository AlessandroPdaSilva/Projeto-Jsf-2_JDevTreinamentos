package jsf;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.DaoGenerico;
import model.Pessoa;

@ViewScoped
@Named(value = "pessoaBean")
public class PessoaBean implements Serializable{
	 
	private static final long serialVersionUID = 1L;

	private Pessoa pessoa = new Pessoa();
	
	private DaoGenerico<Pessoa> daoPessoa;

	
	
	// GET E SET
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa p) {
		this.pessoa = p;
	}
	
	
}
