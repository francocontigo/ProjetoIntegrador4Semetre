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

class HospedagemInserir extends JFrame{
    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    private JLabel lbNome;
    private JLabel lbLocal;
    private JLabel lbCheckin;
    private JLabel lbTipo;
    private JTextField txNome;
    private JTextField txLocal;
    private JTextField txDCheckin;
    private JTextField txTipo;
    
    public HospedagemInserir(DefaultTableModel md){
        super("Hospedagens");
        criarJanela();
        modelo = md;
    }
    
    public void criarJanela(){
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbNome = new JLabel("Nome:");
        lbLocal = new JLabel("Local:");
        lbCheckin = new JLabel("Check-in:");
        lbTipo = new JLabel("Tipo:");
        txTipo = new JTextField();
        txNome = new JTextField();
        txLocal = new JTextField();
        txDCheckin = new JTextField();
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(5, 2, 2, 5));
        painelFundo.add(lbNome);
        painelFundo.add(txNome);
        painelFundo.add(lbTipo);
        painelFundo.add(txTipo);
        painelFundo.add(lbCheckin);
        painelFundo.add(txDCheckin);
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
            if (txNome.getText().isEmpty() || txLocal.getText().isEmpty() || txDCheckin.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Dados não preenchidos para hospedagem!");
            } else{
            Hospedagem h = new Hospedagem();
            h.setNome(txNome.getText());
            h.setTipo(txTipo.getText());
            h.setCheckin(txDCheckin.getText());
            h.setLocal(txLocal.getText());
            HospedagemDAO dao = new HospedagemDAO();
            dao.inserirHospedagem(h);
            ListarHospedagem.pesquisar(modelo);
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
            txDCheckin.setText("");
        }
        
    }
}


