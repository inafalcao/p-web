package br.com.trabalho02.bean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.trabalho02.entidade.Entidade;
import br.com.trabalho02.repository.Repository;
import br.com.trabalho02.servico.Service;

public abstract class BaseControllerBean<E extends Entidade> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public void addMessageError(String message){
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO: ", message));
	}
	
	public void addMessageInfo(String message){
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", message));
	}
	
	public void addMessageWarn(String message){
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta: ", message));
	}
	
	public FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
	
	public ExternalContext getFacesExternalContext(){
		return getFacesContext().getExternalContext();
	}
	
	protected abstract Repository<E> getRepository();
	
	private Map<String, Object> parameters = new HashMap<String, Object>();

	protected boolean closed = false;
	
	protected E entity;

	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

	private Class<E> persistentClass = null;

	private String idDataTable;
	
	public String getIdDataTable() {
		return idDataTable;
	}

	public void setIdDataTable(String idDataTable) {
		this.idDataTable = idDataTable;
	}

	@SuppressWarnings("unchecked")
	public Class<E> getPersistentClass() {
		if (persistentClass == null)
			this.persistentClass = (Class<E>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];

		return persistentClass;
	}
	
	public void save() {
		Long id = getEntity().getId();
		try{
			setEntity(getRepository().save(getEntity()));
			if (id == null) {
				addMessageInfo("Registro Salvo com sucesso");
			} else {
				addMessageInfo("Registro atualizado com sucesso");
			}
			
		}catch (Exception e){
			if (id == null) {
				addMessageError("Erro ao Salvar o Registro");
				e.printStackTrace();
			} else {
				addMessageInfo("Erro ao Atualizar o Registro");
				e.printStackTrace();
			}
		}
		
		
	}

	public String remove() {
		try {
			getRepository().remove(getEntity());
			newInstance();
			//search(idDataTable);
			addMessageInfo("Registro removido com sucesso.");
		} catch (Exception e) {
			
			if (e.getCause().getClass().equals(javax.persistence.RollbackException.class))
			addMessageInfo("O registro não pode ser excluído pois está vinculado com outras entidades.");
			else
			addMessageInfo("Erro ao remover o registro.");
			e.printStackTrace();
		}
		return null;
	}

	public void newInstance(){
		try {
			setEntity(getPersistentClass().newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}		
	}
	
}
