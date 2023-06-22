package gerenciador;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PessoaDAOTest {
    
    public PessoaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of inserirPessoa method, of class PessoaDAO.
     */
    @Test
    public void testInserirPessoa() {
        System.out.println("inserirPessoa");
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João Testinho Filho");
        pessoa.setTelefone("49999999999");
        pessoa.setEmail("email@email.com");
        pessoa.setCpf("06506506532");
        PessoaDAO instance = new PessoaDAO();
        instance.inserirPessoa(pessoa);
    }

    /**
     * Test of remover method, of class PessoaDAO.
     */
    @Test
    public void testRemover() {
        System.out.println("remover");
        int id = -1;
        PessoaDAO instance = new PessoaDAO();
        instance.remover(id);
    }
    
}
