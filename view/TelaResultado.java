package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaResultado extends JFrame {
    private int vitoriasJogador;
    private int vitoriasMaquina;

    public TelaResultado(int vitoriasJogador, int vitoriasMaquina) {
        this.vitoriasJogador = vitoriasJogador;
        this.vitoriasMaquina = vitoriasMaquina;

        setTitle("Super Trunfo - Resultado Final");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(30, 30, 30));

        // Painel superior com resultado
        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new BorderLayout());
        painelSuperior.setBackground(new Color(30, 30, 30));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));

        JLabel lblResultado = new JLabel();
        lblResultado.setFont(new Font("Arial", Font.BOLD, 48));
        lblResultado.setHorizontalAlignment(JLabel.CENTER);

        if (vitoriasJogador > vitoriasMaquina) {
            lblResultado.setText("🎉 VOCÊ VENCEU! 🎉");
            lblResultado.setForeground(new Color(0, 200, 0));
        } else if (vitoriasMaquina > vitoriasJogador) {
            lblResultado.setText("😢 DERROTA 😢");
            lblResultado.setForeground(new Color(200, 0, 0));
        } else {
            lblResultado.setText("🤝 EMPATE 🤝");
            lblResultado.setForeground(new Color(255, 200, 0));
        }

        painelSuperior.add(lblResultado, BorderLayout.CENTER);

        // Painel central com placar
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new GridLayout(2, 1, 0, 20));
        painelCentral.setBackground(new Color(30, 30, 30));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel lblPlacarJogador = new JLabel("Suas Vitórias: " + vitoriasJogador);
        lblPlacarJogador.setFont(new Font("Arial", Font.BOLD, 24));
        lblPlacarJogador.setForeground(new Color(0, 200, 0));
        lblPlacarJogador.setHorizontalAlignment(JLabel.CENTER);
        lblPlacarJogador.setBorder(BorderFactory.createLineBorder(new Color(0, 200, 0), 2));

        JLabel lblPlacarMaquina = new JLabel("Vitórias da Máquina: " + vitoriasMaquina);
        lblPlacarMaquina.setFont(new Font("Arial", Font.BOLD, 24));
        lblPlacarMaquina.setForeground(new Color(200, 0, 0));
        lblPlacarMaquina.setHorizontalAlignment(JLabel.CENTER);
        lblPlacarMaquina.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0), 2));

        painelCentral.add(lblPlacarJogador);
        painelCentral.add(lblPlacarMaquina);

        // Painel inferior com botões
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        painelInferior.setBackground(new Color(50, 50, 50));

        JButton btnNovaPartida = new JButton("NOVA PARTIDA");
        btnNovaPartida.setFont(new Font("Arial", Font.BOLD, 14));
        btnNovaPartida.setPreferredSize(new Dimension(150, 40));
        btnNovaPartida.setBackground(new Color(0, 150, 0));
        btnNovaPartida.setForeground(Color.WHITE);
        btnNovaPartida.setFocusPainted(false);
        btnNovaPartida.setBorder(BorderFactory.createRaisedBevelBorder());
        btnNovaPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaPartida();
            }
        });

        JButton btnMenu = new JButton("MENU PRINCIPAL");
        btnMenu.setFont(new Font("Arial", Font.BOLD, 14));
        btnMenu.setPreferredSize(new Dimension(150, 40));
        btnMenu.setBackground(new Color(0, 100, 200));
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFocusPainted(false);
        btnMenu.setBorder(BorderFactory.createRaisedBevelBorder());
        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarAoMenu();
            }
        });

        JButton btnSair = new JButton("SAIR");
        btnSair.setFont(new Font("Arial", Font.BOLD, 14));
        btnSair.setPreferredSize(new Dimension(150, 40));
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

        painelInferior.add(btnNovaPartida);
        painelInferior.add(btnMenu);
        painelInferior.add(btnSair);

        painelPrincipal.add(painelSuperior, BorderLayout.NORTH);
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        add(painelPrincipal);
        setVisible(true);
    }

    private void novaPartida() {
        this.dispose();
        new TelaJogo();
    }

    private void voltarAoMenu() {
        this.dispose();
        new TelaMenu();
    }
}
