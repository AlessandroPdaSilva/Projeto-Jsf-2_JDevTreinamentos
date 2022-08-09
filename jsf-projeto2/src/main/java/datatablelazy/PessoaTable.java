package datatablelazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import dao.DaoGenerico;
import model.Pessoa;

@Named
public class PessoaTable<T> extends LazyDataModel<Pessoa> {
 
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager daoUsuario;
	
	public List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
	
	@Override
	public List<Pessoa> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		
		
		/**
		listaPessoa = daoUsuario.createQuery(" FROM Pessoa ").
				getResultList();
		
		
		listaPessoa = daoUsuario.createQuery(" FROM Pessoa ").
				setFirstResult(first).
				setMaxResults(pageSize).getResultList();
		
		
		
		setPageSize(pageSize);
		
		Integer qtdRegistro = Integer.parseInt(daoUsuario.createQuery("select count(1)  FROM Pessoa ")
				.getSingleResult().toString());
		
		setRowCount(qtdRegistro);
		
		*/
		listaPessoa.add(new Pessoa());
		return listaPessoa;
	}
	
	
	
}
