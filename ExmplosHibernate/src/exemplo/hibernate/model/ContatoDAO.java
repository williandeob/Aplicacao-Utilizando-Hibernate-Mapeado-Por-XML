package exemplo.hibernate.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import exemplo.hibernate.conexao.HibernateUtil;

public class ContatoDAO {

	public void salvar(Contato contato) {
		Session sessao = null;
		Transaction transacao = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.save(contato);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel inserir o contato. Erro: "
					+ e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar a operação. Erro: "
						+ e.getMessage());
			}
		}
	}

	public void atualizar(Contato contato) {
		Session sessao = null;
		Transaction transacao = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.update(contato);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel altera o contato. Erro: "
					+ e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar a operação de atualização. Erro: "
								+ e.getMessage());
			}
		}
	}

	public void excluir(Contato contato) {
		Session sessao = null;
		Transaction transacao = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.delete(contato);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel excluir o contato. Erro: "
					+ e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar a operação de exclusão. Erro: "
								+ e.getMessage());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contato> listar() {
		Session sessao = null;
		Transaction transacao = null;
		/*
		 * Objeto do tipo Query estará presente em método do tipo DML.
		 */
		Query consulta = null;
		List<Contato> resultado = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			consulta = sessao.createQuery("from Contato");
			resultado = consulta.list();
			transacao.commit();
			return resultado;
		} catch (HibernateException e) {
			System.out.println("Não foi possivel listar os contatos. Erro: "
					+ e.getMessage());
			throw new HibernateException(e);
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar operação de consulta. Mensagem: "
								+ e.getMessage());
			}
		}
	}
	
	@SuppressWarnings("finally")
	public Contato buscarContato(int codigo){
		Session sessao=null;
		Transaction transacao=null;
		Query consulta=null;
		Contato contato=null;
		try{
		sessao=HibernateUtil.getSessionFactory().openSession();
		transacao=sessao.beginTransaction();
		consulta=sessao.createQuery("from Contato where codigo = :parametro");
		consulta.setInteger("parametro", codigo);
		contato=(Contato) consulta.uniqueResult();
		transacao.commit();
		}catch(HibernateException e){
			System.out.println("Não foi possivel buscar contato. Erro: "+e.getMessage());
		}finally{
			try{
			sessao.close();
			}catch(Throwable e){
				System.out.println("Erro ao fechar operação de busca. Mensagem: "+e.getMessage());
			}
			return contato;
		}		
	}
}
