package gerenciador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListarPessoa extends JFrame {

    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btInserir;
    private JButton btAlterar;
    private JButton btExcluir;
    private final DefaultTableModel modelo = new DefaultTableModel();

    public ListarPessoa() {
        super("Pessoas");
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
            PessoaInserir pi = new PessoaInserir(modelo);
            pi.setVisible(true);
        }
    }

    private class btAlterarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int linhaselecionada = -1;
            linhaselecionada = tabela.getSelectedRow();
            if (linhaselecionada >= 0) {
                int idpessoa = (int) tabela.getValueAt(linhaselecionada, 0);
                PessoaAtualizar pa = new PessoaAtualizar(modelo, idpessoa, linhaselecionada);
                pa.setVisible(true);
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
                    int idpessoa = (int) tabela.getValueAt(linhaselecionada, 0);
                    PessoaDAO dao = new PessoaDAO();
                    dao.remover(idpessoa);
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
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("E-mail");
        modelo.addColumn("Telefone");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(80);
        tabela.getTableHeader().setReorderingAllowed(false);
        pesquisar(modelo);
    }

    public static void pesquisar(DefaultTableModel model) {
        model.setNumRows(0);
        PessoaDAO dao = new PessoaDAO();
        for (Pessoa p : dao.getPessoas()) {
            model.addRow(new Object[]{p.getId(), p.getNome(), p.getCpf(), 
                p.getEmail(), p.getTelefone()});
        }
    }
}
