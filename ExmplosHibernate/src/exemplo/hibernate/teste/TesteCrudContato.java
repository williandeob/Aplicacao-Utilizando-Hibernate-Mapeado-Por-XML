package exemplo.hibernate.teste;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import exemplo.hibernate.model.Contato;
import exemplo.hibernate.model.ContatoDAO;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteCrudContato {
Contato testeContato;
ContatoDAO testeContatoDAO;
	@Before
	public void setUp() throws Exception {
		Date testeDataCadastro=new Date(System.currentTimeMillis());
		testeContato=new Contato();
		testeContato.setNome("nome");
		testeContato.setTelefone("telefone");
		testeContato.setEmail("email");
		testeContato.setDataCadastro(testeDataCadastro);
		testeContato.setObservacao("obs");
		testeContatoDAO=new ContatoDAO();
	}

	@After
	public void tearDown() throws Exception {
	}

	//Para a execução desse método a tabela contato deve estar vazia;
	@Test
	public void test1SalvarContato() {
		Contato testeContatoretornado;
		testeContatoDAO.salvar(testeContato);
		testeContatoretornado=testeContatoDAO.buscarContato(1);
		assertNotNull(testeContatoretornado);
		assertEquals(1, testeContatoretornado.getCodigo());
		assertEquals(testeContato.getNome(), testeContatoretornado.getNome());
		assertEquals(testeContato.getTelefone(), testeContatoretornado.getTelefone());
		assertEquals(testeContato.getObservacao(), testeContatoretornado.getObservacao());

	}
	
	@Test
	public void test2DeletarContato() {
		Contato testeContatoRetornado=testeContatoDAO.listar().get(0);
		testeContatoDAO.excluir(testeContatoRetornado);
		assertNull(testeContatoDAO.buscarContato(testeContatoRetornado.getCodigo()));
	}

}
