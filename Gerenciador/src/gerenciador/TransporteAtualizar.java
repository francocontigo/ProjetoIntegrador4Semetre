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

public class TransporteAtualizar extends JFrame{
    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    private JLabel lbId;
    private JLabel lbNome;
    private JLabel lbTipo;
    private JLabel lbDiaViagem;
    private JLabel lbLocal;
    private JTextField txId;
    private JTextField txNome;
    private JTextField txTipo;
    private JTextField txDiaViagem;
    private JTextField txLocal;
    Transporte transporte;
    private int linhaselecionada;
    
    public TransporteAtualizar(DefaultTableModel md, int id, int linha){
        super("Transportes");
        criarJanela();
        modelo = md;
        TransporteDAO dao = new TransporteDAO();
        transporte = dao.getTransporteByID(id);
        txId.setText(Integer.toString(transporte.getId()));
        txNome.setText(transporte.getNome());
        txTipo.setText(transporte.getTipo());
        txLocal.setText(transporte.getLocal());
        txDiaViagem.setText(transporte.getDiaViagem());
        linhaselecionada = linha;
    }
    
    public void criarJanela(){
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbId = new JLabel("Id:");
        lbNome = new JLabel("Nome:");
        lbDiaViagem = new JLabel("DiaViagem:");
        lbLocal = new JLabel("Local:");
        lbTipo = new JLabel("Tipo:");
        txTipo = new JTextField();
        txId = new JTextField();
        txId.setEditable(false);
        txNome = new JTextField();
        txDiaViagem = new JTextField();
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
        painelFundo.add(lbDiaViagem);
        painelFundo.add(txDiaViagem);
        painelFundo.add(btLimpar);
        painelFundo.add(btSalvar);

        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(380,180);
        
        btSalvar.addActionListener(new TransporteAtualizar.BtSalvarListener());
        btLimpar.addActionListener(new TransporteAtualizar.BtLimparListener());
    }
    
    private class BtSalvarListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            Transporte t = new Transporte();
            t.setId(Integer.parseInt(txId.getText()));
            t.setNome(txNome.getText());
            t.setTipo(txTipo.getText());
            t.setLocal(txLocal.getText());
            t.setDiaViagem(txDiaViagem.getText());
            
            TransporteDAO dao = new TransporteDAO();
            dao.atualizar(t);
            modelo.removeRow(linhaselecionada);
            modelo.addRow(new Object[]{t.getId(), t.getNome(), t.getTipo(), t.getLocal(), 
            t.getDiaViagem()});
            setVisible(false);
        }
    }
    
    private class BtLimparListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            txNome.setText("");
            txTipo.setText("");
            txDiaViagem.setText("");
            txLocal.setText("");
        }
        
    }
}
