package br.com.hotelsaopaulo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.hotelsaopaulo.conexao.HibernateUtil;
import br.com.hotelsaopaulo.enuns.EnumSituacao;
import br.com.hotelsaopaulo.orm.Pessoa;
import br.com.hotelsaopaulo.orm.PessoaContato;

public class PessoaDAO extends GenericDAO<Pessoa> implements Serializable{
	private static final long serialVersionUID = -7152116842103286900L;
	
	@SuppressWarnings("unchecked")
	public List<PessoaContato> listarContato(Long idPessoa) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {

			String hql = "from PessoaContato pc where pc.pessoa.id = :idPessoa order by pc.id asc";
			Query query = sessao.createQuery(hql);
			query.setParameter("idPessoa", idPessoa);

			return query.list();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			return new ArrayList<PessoaContato>();
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> pesquisaPessoaPorStatus(EnumSituacao enumSituacao) {
		Session sessao = HibernateUtil.getConexaoBaseLocal().openSession();
		try {

			String hql = "from Pessoa p where p.situacao = :enumSituacao order by p.nome asc";
			Query query = sessao.createQuery(hql);
			query.setParameter("enumSituacao", enumSituacao);

			return query.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
}
