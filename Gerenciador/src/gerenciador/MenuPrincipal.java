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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuPrincipal extends JFrame{
    
    private JButton btGerenciar;
    private JButton btRelatorio;
    
    public MenuPrincipal() {
        super("Menu Principal");
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
        
        btGerenciar = new JButton("Gerenciar");
        btGerenciar.setBounds(180,87,120,40);
        btGerenciar.setFont(new Font("Arial", Font.BOLD, 14));
        btGerenciar.addActionListener(new btGerenciarListener());
        backgroundPanel.add(btGerenciar);
        
        btRelatorio = new JButton("Relatório");
        btRelatorio.setBounds(180,140,120,40);
        btRelatorio.setFont(new Font("Arial", Font.BOLD, 14));
        btRelatorio.addActionListener(new btRelatoriorListener());
        backgroundPanel.add(btRelatorio);
    }

    private static class btRelatoriorListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Relatório não implementado nesta versão!");
        }
    }

    private class btGerenciarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Gerenciar gr;
            try{
                gr = new Gerenciar();
                gr.setVisible(true);
            } catch(Exception ex) {
                System.out.println("Não foi possível fechar o ResultSet " + ex.getMessage());
            }
        }
    }
}
