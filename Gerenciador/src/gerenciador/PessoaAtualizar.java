package gerenciador;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class PessoaAtualizar extends JFrame{
    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    private JLabel lbId;
    private JLabel lbNome;
    private JLabel lbCpf;
    private JLabel lbTelefone;
    private JLabel lbEmail;
    private JTextField txId;
    private JTextField txNome;
    private JTextField txCpf;
    private JTextField txTelefone;
    private JTextField txEmail;
    Pessoa pessoa;
    private int linhaselecionada;
    
    public PessoaAtualizar(DefaultTableModel md, int id, int linha){
        super("Pessoas");
        criarJanela();
        modelo = md;
        PessoaDAO dao = new PessoaDAO();
        pessoa = dao.getPessoaByID(id);
        txId.setText(Integer.toString(pessoa.getId()));
        txNome.setText(pessoa.getNome());
        txCpf.setText(pessoa.getCpf());
        txEmail.setText(pessoa.getEmail());
        txTelefone.setText(pessoa.getTelefone());
        linhaselecionada = linha;
    }
    
    public void criarJanela(){
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbId = new JLabel("Id:");
        lbNome = new JLabel("Nome:");
        lbTelefone = new JLabel("Telefone:");
        lbEmail = new JLabel("Email");
        lbCpf = new JLabel("CPF");
        txCpf = new JTextField();
        txId = new JTextField();
        txId.setEditable(false);
        txNome = new JTextField();
        txTelefone = new JTextField();
        txEmail = new JTextField();
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(6, 2, 2, 5));
        painelFundo.add(lbId);
        painelFundo.add(txId);
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
        setSize(380,180);
        
        btSalvar.addActionListener(new PessoaAtualizar.BtSalvarListener());
        btLimpar.addActionListener(new PessoaAtualizar.BtLimparListener());
    }
    
    private class BtSalvarListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            Pessoa p = new Pessoa();
            p.setId(Integer.parseInt(txId.getText()));
            p.setNome(txNome.getText());
            p.setCpf(txCpf.getText());
            p.setEmail(txEmail.getText());
            p.setTelefone(txTelefone.getText());
            
            PessoaDAO dao = new PessoaDAO();
            dao.atualizar(p);
            modelo.removeRow(linhaselecionada);
            modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getCpf(), p.getEmail(), 
            p.getTelefone()});
            setVisible(false);
        }
    }
    
    private class BtLimparListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            txNome.setText("");
            txCpf.setText("");
            txTelefone.setText("");
            txEmail.setText("");
        }
        
    }
}
