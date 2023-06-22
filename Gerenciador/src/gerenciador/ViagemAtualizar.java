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

class ViagemAtualizar extends JFrame{
    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    private JLabel lbId;
    private JLabel lbPessoa;
    private JLabel lbTransporte;
    private JLabel lbHospedagem;
    private JTextField txId;
    private JTextField txPessoa;
    private JTextField txTransporte;
    private JTextField txHospedagem;
    Viagem viagem;
    private int linhaselecionada;
    
    public ViagemAtualizar(DefaultTableModel md, int id, int linha){
        super("Viagens");
        criarJanela();
        modelo = md;
        ViagemDAO dao = new ViagemDAO();
        viagem = dao.getViagemByID(id);
        txId.setText(Integer.toString(viagem.getId()));
        txPessoa.setText(Integer.toString(viagem.getPessoa()));
        txTransporte.setText(Integer.toString(viagem.getTransporte()));
        txHospedagem.setText(Integer.toString(viagem.getHospedagem()));
        linhaselecionada = linha;
    }
    
    public void criarJanela(){
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbId = new JLabel("Id:");
        lbPessoa = new JLabel("Pessoa:");
        lbHospedagem = new JLabel("Hospedagem:");
        lbTransporte = new JLabel("Transporte:");
        txTransporte = new JTextField();
        txId = new JTextField();
        txId.setEditable(false);
        txPessoa = new JTextField();
        txHospedagem = new JTextField();
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(5, 2, 2, 4));
        painelFundo.add(lbId);
        painelFundo.add(txId);
        painelFundo.add(lbPessoa);
        painelFundo.add(txPessoa);
        painelFundo.add(lbTransporte);
        painelFundo.add(txTransporte);
        painelFundo.add(lbHospedagem);
        painelFundo.add(txHospedagem);
        painelFundo.add(btLimpar);
        painelFundo.add(btSalvar);

        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300,150);
        
        btSalvar.addActionListener(new ViagemAtualizar.BtSalvarListener());
        btLimpar.addActionListener(new ViagemAtualizar.BtLimparListener());
    }
    
    private class BtSalvarListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            Viagem v = new Viagem();
            v.setId(Integer.parseInt(txId.getText()));
            v.setPessoa(Integer.parseInt(txPessoa.getText()));
            v.setTransporte(Integer.parseInt(txTransporte.getText()));
            v.setHospedagem(Integer.parseInt(txHospedagem.getText()));
            
            ViagemDAO dao = new ViagemDAO();
            dao.atualizar(v);
            modelo.removeRow(linhaselecionada);
            modelo.addRow(new Object[]{v.getId(), v.getPessoa(), v.getTransporte(), v.getHospedagem()});
            setVisible(false);
        }
    }
    
    private class BtLimparListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            txPessoa.setText("");
            txTransporte.setText("");
            txHospedagem.setText("");
        }
        
    }
}

