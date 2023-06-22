package gerenciador;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TransporteDAOTest {
    
    public TransporteDAOTest() {
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
     * Test of inserirTransporte method, of class TransporteDAO.
     */
    @Test
    public void testInserirTransporte() {
        System.out.println("inserirTransporte");
        Transporte transporte = new Transporte();
        transporte.setNome("Onibus de Testes");
        transporte.setLocal("Galpão de Testes");
        transporte.setTipo("Onibus");
        transporte.setDiaViagem("2023/08/13");
        TransporteDAO instance = new TransporteDAO();
        instance.inserirTransporte(transporte);
    }

    /**
     * Test of remover method, of class TransporteDAO.
     */
    @Test
    public void testRemover() {
        System.out.println("remover");
        int id = -1;
        TransporteDAO instance = new TransporteDAO();
        instance.remover(id);
    }
    
}
