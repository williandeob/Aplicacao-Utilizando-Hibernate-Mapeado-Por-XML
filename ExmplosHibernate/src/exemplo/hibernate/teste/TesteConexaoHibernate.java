package exemplo.hibernate.teste;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import exemplo.hibernate.conexao.HibernateUtil;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteConexaoHibernate {
	  SessionFactory testeSessionFactory;
	  Session testeSession;

	@Before
	public void setUp() throws Exception {
	  testeSessionFactory=HibernateUtil.getSessionFactory();
	  testeSession=testeSessionFactory.openSession();
	}

	@After
	public void tearDown() throws Exception {
		testeSession.close();
	}

	@Test
	public void test1RetornoSessionFactory() {
		assertNotNull(testeSessionFactory);
	}
	
	@Test
	public void teste2ConexaoRealizada(){
		assertNotNull(testeSession);
	}

}
