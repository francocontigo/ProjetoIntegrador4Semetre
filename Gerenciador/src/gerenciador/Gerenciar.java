package gerenciador;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

class Gerenciar extends JFrame{

    private JButton btVoltar;
    private JButton btPessoa;
    private JButton btTransporte;
    private JButton btHospedagem;
    private JButton btViagem;
    
    public Gerenciar(){
        super("Gerenciar");
        criarJanela();
    }
    
    public void criarJanela(){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(500, 320);
            setLocationRelativeTo(null);
            setResizable(false);

            JPanel backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g.create();

                    // Cores do gradiente
                    Color startColor = new Color(194,246,131);
                    Color endColor = new Color(119,170,60);

                    // Ponto inicial e final do gradiente
                    Point2D startPoint = new Point2D.Float(0, 0);
                    Point2D endPoint = new Point2D.Float(0, getHeight());

                    // Criação e preenchimento do gradiente
                    GradientPaint gradientPaint = new GradientPaint(startPoint, startColor, endPoint, endColor);
                    g2d.setPaint(gradientPaint);
                    g2d.fillRect(0, 0, getWidth(), getHeight());

                    g2d.dispose();
                }
            };
            setContentPane(backgroundPanel);
            backgroundPanel.setLayout(null);    
            
            
            //Botões
            btVoltar = new JButton("Voltar");
            btVoltar.setBounds(10,10,80,40);
            btVoltar.setFont(new Font("Arial", Font.BOLD, 13));
            btVoltar.addActionListener(new btVoltarListener());
            backgroundPanel.add(btVoltar);
            
            btPessoa = new JButton("Pessoa");
            btPessoa.setBounds(180,45,120,40);
            btPessoa.setFont(new Font("Arial", Font.BOLD, 13));
            btPessoa.addActionListener(new btPessoaListener());
            backgroundPanel.add(btPessoa);
            
            btTransporte = new JButton("Transporte");
            btTransporte.setBounds(180,95,120,40);
            btTransporte.setFont(new Font("Arial", Font.BOLD, 13));
            btTransporte.addActionListener(new btTransporteListener());
            backgroundPanel.add(btTransporte);
            
            btHospedagem = new JButton("Hospedagem");
            btHospedagem.setBounds(180,145,120,40);
            btHospedagem.setFont(new Font("Arial", Font.BOLD, 13));
            btHospedagem.addActionListener(new btHospedagemListener());
            backgroundPanel.add(btHospedagem);
            
            btViagem = new JButton("Viagem");
            btViagem.setBounds(180,195,120,40);
            btViagem.setFont(new Font("Arial", Font.BOLD, 13));
            btViagem.addActionListener(new btViagemListener());
            backgroundPanel.add(btViagem);
    }

    private class btPessoaListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            ListarPessoa lp;
            try{
                lp = new ListarPessoa();
                lp.setVisible(true);
            } catch(Exception ex) {
                System.out.println("Não foi possível fechar o ResultSet " + ex.getMessage());
            }
        }
    }

    private class btTransporteListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            ListarTransporte lt;
            try{
                lt = new ListarTransporte();
                lt.setVisible(true);
            } catch(Exception ex) {
                System.out.println("Não foi possível fechar o ResultSet " + ex.getMessage());
            }
        }
    }

    private class btHospedagemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ListarHospedagem lh;
            try{
                lh = new ListarHospedagem();
                lh.setVisible(true);
            } catch(Exception ex) {
                System.out.println("Não foi possível fechar o ResultSet " + ex.getMessage());
            }
        }
    }

    private class btViagemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ListarViagem lv;
            try{
                lv = new ListarViagem();
                lv.setVisible(true);
            } catch(Exception ex) {
                System.out.println("Não foi possível fechar o ResultSet " + ex.getMessage());
            }
        }
    }

    private class btVoltarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            MenuPrincipal mp;
            try{
                mp = new MenuPrincipal();
                mp.setVisible(true);
            } catch(Exception ex) {
                System.out.println("Não foi possível fechar o ResultSet " + ex.getMessage());
            }
        }
    }

    
    
    
}