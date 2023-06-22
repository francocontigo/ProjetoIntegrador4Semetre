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

class ViagemInserir extends JFrame{
    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    private JLabel lbPessoa;
    private JLabel lbTransporte;
    private JLabel lbHospedagem;
    private JTextField txPessoa;
    private JTextField txTransporte;
    private JTextField txHospedagem;
    
    public ViagemInserir(DefaultTableModel md){
        super("Viagens");
        criarJanela();
        modelo = md;
    }
    
    public void criarJanela(){
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbPessoa = new JLabel("Pessoa:");
        lbTransporte = new JLabel("Transporte:");
        lbHospedagem = new JLabel("Hospedagem:");
        txHospedagem = new JTextField();
        txPessoa = new JTextField();
        txTransporte = new JTextField();
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(4, 2, 2, 4));
        painelFundo.add(lbPessoa);
        painelFundo.add(txPessoa);
        painelFundo.add(lbHospedagem);
        painelFundo.add(txHospedagem);
        painelFundo.add(lbTransporte);
        painelFundo.add(txTransporte);
        painelFundo.add(btLimpar);
        painelFundo.add(btSalvar);
        
        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300,150);
        btSalvar.addActionListener(new btSalvarListener());
        btLimpar.addActionListener(new btLimparListener());
        
    }
    
    private class btSalvarListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (txPessoa.getText().isEmpty() || txTransporte.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Dados não preenchidos para viagem!");
            } else{
            Viagem v = new Viagem();
            v.setPessoa(Integer.parseInt(txPessoa.getText()));
            v.setTransporte(Integer.parseInt(txTransporte.getText()));
            v.setHospedagem(Integer.parseInt(txHospedagem.getText()));
            ViagemDAO dao = new ViagemDAO();
            dao.inserirViagem(v);
            ListarViagem.pesquisar(modelo);
            setVisible(false);
            }
        }
    }
    
    private class btLimparListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            txPessoa.setText("");
            txTransporte.setText("");
            txHospedagem.setText("");
        }
        
    }
}


