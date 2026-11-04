package view;
import view.TelaJogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenu extends JFrame {
    private JButton btnJogar;
    private JButton btnSair;
    private JLabel lblLogo;
    private JLabel lblTitulo;

    public TelaMenu() {
        setTitle("Super Trunfo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(30, 30, 30));

        // Painel superior com logo
        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new BorderLayout());
        painelSuperior.setBackground(new Color(30, 30, 30));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Adicionar logo
        ImageIcon icon = ImagemUtil.carregarImagem("/img/logo/super-trunfo.jpg", 200, 150);
        lblLogo = new JLabel(icon);
        lblLogo.setHorizontalAlignment(JLabel.CENTER);
        painelSuperior.add(lblLogo, BorderLayout.CENTER);

        // Painel central com descricão
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBackground(new Color(30, 30, 30));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblDescricao = new JLabel("<html><div style='text-align: center;'>Bem-vindo ao Super Trunfo!<br><br>Desafie a máquina em uma partida<br>de cartas épicas!</div></html>", SwingConstants.CENTER);
        lblDescricao.setFont(new Font("Arial", Font.PLAIN, 16));
        lblDescricao.setForeground(Color.WHITE);
        lblDescricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelCentral.add(Box.createVerticalGlue());
        painelCentral.add(lblDescricao);
        painelCentral.add(Box.createVerticalGlue());

        // Painel inferior com botões
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        painelInferior.setBackground(new Color(30, 30, 30));

        btnJogar = new JButton("JOGAR");
        btnJogar.setFont(new Font("Arial", Font.BOLD, 16));
        btnJogar.setPreferredSize(new Dimension(150, 50));
        btnJogar.setBackground(new Color(0, 150, 0));
        btnJogar.setForeground(Color.WHITE);
        btnJogar.setFocusPainted(false);
        btnJogar.setBorder(BorderFactory.createRaisedBevelBorder());
        btnJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });

        btnSair = new JButton("SAIR");
        btnSair.setFont(new Font("Arial", Font.BOLD, 16));
        btnSair.setPreferredSize(new Dimension(150, 50));
        btnSair.setBackground(new Color(200, 0, 0));
        btnSair.setForeground(Color.WHITE);
        btnSair.setFocusPainted(false);
        btnSair.setBorder(BorderFactory.createRaisedBevelBorder());
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        painelInferior.add(btnJogar);
        painelInferior.add(btnSair);

        // Adicionar painéis à janela
        painelPrincipal.add(painelSuperior, BorderLayout.NORTH);
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        add(painelPrincipal);
        setVisible(true);
    }

    private void iniciarJogo() {
        // Fechar menu
        this.dispose();

        // Abrir tela de jogo
        TelaJogo telaJogo = new TelaJogo();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaMenu();
            }
        });
    }
}