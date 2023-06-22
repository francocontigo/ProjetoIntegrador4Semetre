package gerenciador;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

class TransporteInserir extends JFrame{
    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    private JLabel lbNome;
    private JLabel lbLocal;
    private JLabel lbDiaViagem;
    private JLabel lbTipo;
    private JTextField txNome;
    private JTextField txLocal;
    private JTextField txDiaViagem;
    private JTextField txTipo;
    
    public TransporteInserir(DefaultTableModel md){
        super("Transportes");
        criarJanela();
        modelo = md;
    }
    
    public void criarJanela(){
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbNome = new JLabel("Nome:");
        lbLocal = new JLabel("Local:");
        lbDiaViagem = new JLabel("Dia da Viagem:");
        lbTipo = new JLabel("Tipo:");
        txTipo = new JTextField();
        txNome = new JTextField();
        txLocal = new JTextField();
        txDiaViagem = new JTextField();
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(5, 2, 2, 5));
        painelFundo.add(lbNome);
        painelFundo.add(txNome);
        painelFundo.add(lbTipo);
        painelFundo.add(txTipo);
        painelFundo.add(lbDiaViagem);
        painelFundo.add(txDiaViagem);
        painelFundo.add(lbLocal);
        painelFundo.add(txLocal);
        painelFundo.add(btLimpar);
        painelFundo.add(btSalvar);
        
        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400,180);
        btSalvar.addActionListener(new btSalvarListener());
        btLimpar.addActionListener(new btLimparListener());
        
    }
    
    private class btSalvarListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (txNome.getText().isEmpty() || txLocal.getText().isEmpty() || txDiaViagem.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Dados não preenchidos para transporte!");
            } else{
            Transporte t = new Transporte();
            t.setNome(txNome.getText());
            t.setTipo(txTipo.getText());
            t.setDiaViagem(txDiaViagem.getText());
            t.setLocal(txLocal.getText());
            TransporteDAO dao = new TransporteDAO();
            dao.inserirTransporte(t);
            ListarTransporte.pesquisar(modelo);
            setVisible(false);
            }
        }
    }
    
    private class btLimparListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            txNome.setText("");
            txLocal.setText("");
            txTipo.setText("");
            txDiaViagem.setText("");
        }
        
    }
}

