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

class HospedagemAtualizar extends JFrame{
    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    private JLabel lbId;
    private JLabel lbNome;
    private JLabel lbTipo;
    private JLabel lbCheckin;
    private JLabel lbLocal;
    private JTextField txId;
    private JTextField txNome;
    private JTextField txTipo;
    private JTextField txCheckin;
    private JTextField txLocal;
    Hospedagem hospedagem;
    private int linhaselecionada;
    
    public HospedagemAtualizar(DefaultTableModel md, int id, int linha){
        super("Hospedagens");
        criarJanela();
        modelo = md;
        HospedagemDAO dao = new HospedagemDAO();
        hospedagem = dao.getHospedagemByID(id);
        txId.setText(Integer.toString(hospedagem.getId()));
        txNome.setText(hospedagem.getNome());
        txTipo.setText(hospedagem.getTipo());
        txLocal.setText(hospedagem.getLocal());
        txCheckin.setText(hospedagem.getCheckin());
        linhaselecionada = linha;
    }
    
    public void criarJanela(){
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbId = new JLabel("Id:");
        lbNome = new JLabel("Nome:");
        lbCheckin = new JLabel("Check-in:");
        lbLocal = new JLabel("Local:");
        lbTipo = new JLabel("Tipo:");
        txTipo = new JTextField();
        txId = new JTextField();
        txId.setEditable(false);
        txNome = new JTextField();
        txCheckin = new JTextField();
        txLocal = new JTextField();
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(6, 2, 2, 5));
        painelFundo.add(lbId);
        painelFundo.add(txId);
        painelFundo.add(lbNome);
        painelFundo.add(txNome);
        painelFundo.add(lbTipo);
        painelFundo.add(txTipo);
        painelFundo.add(lbLocal);
        painelFundo.add(txLocal);
        painelFundo.add(lbCheckin);
        painelFundo.add(txCheckin);
        painelFundo.add(btLimpar);
        painelFundo.add(btSalvar);

        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(380,180);
        
        btSalvar.addActionListener(new HospedagemAtualizar.BtSalvarListener());
        btLimpar.addActionListener(new HospedagemAtualizar.BtLimparListener());
    }
    
    private class BtSalvarListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            Hospedagem h = new Hospedagem();
            h.setId(Integer.parseInt(txId.getText()));
            h.setNome(txNome.getText());
            h.setTipo(txTipo.getText());
            h.setLocal(txLocal.getText());
            h.setCheckin(txCheckin.getText());
            
            HospedagemDAO dao = new HospedagemDAO();
            dao.atualizar(h);
            modelo.removeRow(linhaselecionada);
            modelo.addRow(new Object[]{h.getId(), h.getNome(), h.getTipo(), h.getLocal(), 
            h.getCheckin()});
            setVisible(false);
        }
    }
    
    private class BtLimparListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            txNome.setText("");
            txTipo.setText("");
            txCheckin.setText("");
            txLocal.setText("");
        }
        
    }
}
