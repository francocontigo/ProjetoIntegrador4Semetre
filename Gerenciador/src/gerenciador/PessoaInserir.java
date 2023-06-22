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

class PessoaInserir extends JFrame{
    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    private JLabel lbNome;
    private JLabel lbTelefone;
    private JLabel lbEmail;
    private JLabel lbCpf;
    private JTextField txNome;
    private JTextField txTelefone;
    private JTextField txEmail;
    private JTextField txCpf;
    
    public PessoaInserir(DefaultTableModel md){
        super("Pessoas");
        criarJanela();
        modelo = md;
    }
    
    public void criarJanela(){
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbNome = new JLabel("Nome:");
        lbTelefone = new JLabel("Telefone:");
        lbEmail = new JLabel("Email");
        lbCpf = new JLabel("CPF");
        txCpf = new JTextField();
        txNome = new JTextField();
        txTelefone = new JTextField();
        txEmail = new JTextField();
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(5, 2, 2, 5));
        painelFundo.add(lbNome);
        painelFundo.add(txNome);
        painelFundo.add(lbCpf);
        painelFundo.add(txCpf);
        painelFundo.add(lbEmail);
        painelFundo.add(txEmail);
        painelFundo.add(lbTelefone);
        painelFundo.add(txTelefone);
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
            if (txNome.getText().isEmpty() || txTelefone.getText().isEmpty() || txEmail.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Dados não preenchidos para pessoa!");
            } else{
            Pessoa p = new Pessoa();
            p.setNome(txNome.getText());
            p.setCpf(txCpf.getText());
            p.setEmail(txEmail.getText());
            p.setTelefone(txTelefone.getText());
            PessoaDAO dao = new PessoaDAO();
            dao.inserirPessoa(p);
            ListarPessoa.pesquisar(modelo);
            setVisible(false);
            }
        }
    }
    
    private class btLimparListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            txNome.setText("");
            txTelefone.setText("");
            txEmail.setText("");
        }
        
    }
}
