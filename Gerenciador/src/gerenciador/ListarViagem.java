package gerenciador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class ListarViagem extends JFrame {

    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btInserir;
    private JButton btAlterar;
    private JButton btExcluir;
    private final DefaultTableModel modelo = new DefaultTableModel();

    public ListarViagem() {
        super("Viagens");
        criarJTable();
        criarJanela();

    }

    public void criarJanela() {
        btInserir = new JButton("Inserir");
        btAlterar = new JButton("Alterar");
        btExcluir = new JButton("Excluir");
        painelBotoes = new JPanel();
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel();
        painelFundo.setLayout(new BorderLayout());
        painelFundo.add(BorderLayout.CENTER, barraRolagem);
        painelBotoes.add(btInserir);
        painelBotoes.add(btAlterar);
        painelBotoes.add(btExcluir);
        painelFundo.add(BorderLayout.SOUTH, painelBotoes);
        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 320);
        setLocationRelativeTo(null);
        btInserir.addActionListener(new btInserirListener());
        btAlterar.addActionListener(new btAlterarListener());
        btExcluir.addActionListener(new btExcluirListener());
    }

    private class btInserirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            ViagemInserir vi = new ViagemInserir(modelo);
            vi.setVisible(true);
        }
    }

    private class btAlterarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int linhaselecionada = -1;
            linhaselecionada = tabela.getSelectedRow();
            if (linhaselecionada >= 0) {
                int idviagem = (int) tabela.getValueAt(linhaselecionada, 0);
                ViagemAtualizar va = new ViagemAtualizar(modelo, idviagem, linhaselecionada);
                va.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha!");
            }
        }
    }

    private class btExcluirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int linhaselecionada = -1;
            linhaselecionada = tabela.getSelectedRow();
            if (linhaselecionada >= 0) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro?", "Atenção", dialogButton);
                System.out.println("Resultado do dialogo" + dialogResult);
                if (dialogResult == 0) {
                    int idviagem = (int) tabela.getValueAt(linhaselecionada, 0);
                    ViagemDAO dao = new ViagemDAO();
                    dao.remover(idviagem);
                    modelo.removeRow(linhaselecionada);
                }
            } else {
                JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha!");
            }

        }

    }

    public void criarJTable() {
        tabela = new JTable(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Pessoa");
        modelo.addColumn("Transporte");
        modelo.addColumn("Hospedagem");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(20);
        tabela.getTableHeader().setReorderingAllowed(false);
        pesquisar(modelo);
    }

    public static void pesquisar(DefaultTableModel model) {
        model.setNumRows(0);
        ViagemDAO dao = new ViagemDAO();
        for (Viagem v : dao.getViagens()) {
            model.addRow(new Object[]{v.getId(), v.getPessoa(), v.getTransporte(), 
                v.getHospedagem()});
        }
    }
}

