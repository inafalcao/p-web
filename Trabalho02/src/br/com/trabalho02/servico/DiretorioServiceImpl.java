package br.com.trabalho02.servico;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Service;

import br.com.trabalho02.entidade.Diretorio;
import br.com.trabalho02.repository.DiretorioRepository;
import br.com.trabalho02.repository.Repository;

@Service("diretorioService")
public class DiretorioServiceImpl extends ServiceImpl<Diretorio>
	implements DiretorioService {
	
	private static final long serialVersionUID = -7579434482940907523L;
	
	@Resource(name = "diretorioRepository")
	private DiretorioRepository diretorioRepository;
	
	@Override
	public Repository<Diretorio> getRepository() {
		return diretorioRepository;
	}

	@Override
	public CriteriaQuery<?> montaCriteriaPesquisa(Diretorio diretorio) {
		return diretorioRepository.montaCriteriaPesquisa(diretorio);
	}
	
}