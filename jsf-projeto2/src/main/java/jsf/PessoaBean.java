package jsf;

import java.io.IOException;
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
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.util.Base64;

import dao.DaoGenerico;
import datatablelazy.PessoaTable;
import model.Pessoa;
import model.Telefone;

@ViewScoped
@Named(value = "pessoaBean")
public class PessoaBean implements Serializable{
	 
	private static final long serialVersionUID = 1L;

	private Pessoa pessoa = new Pessoa();
	private PessoaTable<Pessoa> listaPessoa = new PessoaTable<Pessoa>();
	
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
	
	public void upload(FileUploadEvent imagem){
		
		String imagemBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(imagem.getFile().getContents());
		pessoa.setImagem(imagemBase64);
		
		System.out.println("metodo chamado");
	}
	
	public void download() throws Exception{
		
		String idPessoa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("idPessoa");
		
		Pessoa p = daoPessoa.pesquisar(Long.parseLong(idPessoa), new Pessoa());
		
		byte[] imagem = new Base64().decode(p.getImagem().split("\\,")[1]);
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		response.addHeader("Content-Disposition", "attachment; filename=download.png");
		response.setContentType("application/octet-stream");
		response.setContentLength(imagem.length);
		response.getOutputStream().write(imagem);
		response.getOutputStream().flush();
		FacesContext.getCurrentInstance().responseComplete();
		
		System.out.println(imagem);
		
	}
	
	
	//LISTAR
	@PostConstruct
	public void lista(){
		listaPessoa.load(0, 5, null, null);
	}

	
	// GET E SET
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa p) {
		this.pessoa = p;
	}

	public PessoaTable<Pessoa> getListaPessoa() {
		return listaPessoa;
	}

	public void setListaPessoa(PessoaTable<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}

	
	
	
}
