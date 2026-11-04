package view;

import controller.SuperTrunfoController;
import model.Carta;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TelaJogo extends JFrame {
    private SuperTrunfoController controller;
    private JPanel painelCartasJogador;
    private JPanel painelCartasMaquina;
    private JPanel painelAtributos;
    private JLabel lblPlacarJogador;
    private JLabel lblPlacarMaquina;
    private JLabel lblResultado;

    private Carta cartaJogadorAtual;
    private Carta cartaMaquinaAtual;
    private int vitoriasJogador = 0;
    private int vitoriasMaquina = 0;
    private boolean aguardandoComparacao = true;

    public TelaJogo() {
        try {
            controller = new SuperTrunfoController();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao iniciar o jogo: " + e.getMessage());
            System.exit(1);
        }

        setTitle("⚡ Super Trunfo - Marvel ⚡");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(30, 30, 30));
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        painelPrincipal.add(criarPainelPlacar(), BorderLayout.NORTH);
        painelPrincipal.add(criarPainelCartas(), BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new BorderLayout(0, 10));
        painelInferior.setOpaque(false);
        painelAtributos = criarPainelAtributos();
        painelInferior.add(painelAtributos, BorderLayout.NORTH);
        painelInferior.add(criarPainelControles(), BorderLayout.SOUTH);

        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        add(painelPrincipal);
        carregarNovaRodada();
        setVisible(true);
    }

    private JPanel criarPainelPlacar() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 10));
        painel.setBackground(new Color(50, 50, 50));
        painel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        lblPlacarJogador = new JLabel("VOCÊ: 0");
        lblPlacarJogador.setFont(new Font("Arial", Font.BOLD, 22));
        lblPlacarJogador.setForeground(new Color(0, 220, 0));

        lblPlacarMaquina = new JLabel("MÁQUINA: 0");
        lblPlacarMaquina.setFont(new Font("Arial", Font.BOLD, 22));
        lblPlacarMaquina.setForeground(new Color(220, 0, 0));

        painel.add(lblPlacarJogador);
        painel.add(lblPlacarMaquina);
        return painel;
    }

    private JPanel criarPainelCartas() {
        JPanel painel = new JPanel(new GridLayout(1, 3, 20, 0));
        painel.setOpaque(false);
        painel.setBorder(new EmptyBorder(10, 0, 10, 0));

        painelCartasJogador = new JPanel();
        painelCartasJogador.setOpaque(false);
        painelCartasJogador.setBorder(BorderFactory.createLineBorder(new Color(0, 220, 0), 3));

        JPanel painelVersus = new JPanel(new GridBagLayout());
        painelVersus.setOpaque(false);
        JLabel lblVersus = new JLabel("VS");
        lblVersus.setFont(new Font("Impact", Font.BOLD, 50));
        lblVersus.setForeground(Color.WHITE);
        painelVersus.add(lblVersus);

        painelCartasMaquina = new JPanel();
        painelCartasMaquina.setOpaque(false);
        painelCartasMaquina.setBorder(BorderFactory.createLineBorder(new Color(220, 0, 0), 3));

        painel.add(painelCartasJogador);
        painel.add(painelVersus);
        painel.add(painelCartasMaquina);
        return painel;
    }

    private JPanel criarPainelAtributos() {
        JPanel painel = new JPanel(new GridLayout(1, 5, 10, 0));
        painel.setOpaque(false);
        painel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // Valores enviados ao motor do jogo (sem acentos)
        String[] valoresAtributos = {"forca", "velocidade", "habilidade", "equipamento", "inteligencia"};
        // Textos que aparecem nos botões
        String[] nomesBotoes = {"FORÇA", "VELOCIDADE", "HABILID.", "EQUIP.", "INTEL."};

        for (int i = 0; i < valoresAtributos.length; i++) {
            final String atributoInterno = valoresAtributos[i];
            JButton btn = new JButton(nomesBotoes[i]);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setBackground(new Color(0, 100, 200));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createRaisedBevelBorder());
            btn.addActionListener(e -> compararAtributo(atributoInterno));
            painel.add(btn);
        }
        return painel;
    }

    private JPanel criarPainelControles() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painel.setBackground(new Color(50, 50, 50));
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));

        lblResultado = new JLabel("Escolha um atributo para comparar!");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 16));
        lblResultado.setForeground(new Color(255, 200, 0));

        JButton btnProxima = new JButton("PRÓXIMA RODADA");
        btnProxima.setFont(new Font("Arial", Font.BOLD, 14));
        btnProxima.setBackground(new Color(0, 150, 0));
        btnProxima.setForeground(Color.WHITE);
        btnProxima.addActionListener(e -> {
            if (!aguardandoComparacao) {
                carregarNovaRodada();
            } else {
                lblResultado.setText("Compare os atributos primeiro!");
            }
        });

        JButton btnVoltar = new JButton("VOLTAR AO MENU");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        btnVoltar.setBackground(new Color(200, 0, 0));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.addActionListener(e -> voltarAoMenu());

        painel.add(lblResultado);
        painel.add(Box.createHorizontalStrut(30));
        painel.add(btnProxima);
        painel.add(btnVoltar);
        return painel;
    }

    private void carregarNovaRodada() {
        if (controller.getCartasDisponiveis().size() < 2) {
            finalizarJogo();
            return;
        }

        cartaJogadorAtual = controller.getCartasDisponiveis().get(0);
        cartaMaquinaAtual = controller.getCartasDisponiveis().get(1);

        lblResultado.setText("Escolha um atributo para comparar!");
        lblResultado.setForeground(new Color(255, 200, 0));
        aguardandoComparacao = true;

        atualizarExibicaoCartas(true); // Ocultar carta da máquina
        for (Component comp : painelAtributos.getComponents()) {
            comp.setEnabled(true);
        }
    }

    private void atualizarExibicaoCartas(boolean ocultarMaquina) {
        exibirCarta(painelCartasJogador, cartaJogadorAtual, "SUA CARTA", new Color(0, 220, 0), false);
        exibirCarta(painelCartasMaquina, cartaMaquinaAtual, "CARTA DA MÁQUINA", new Color(220, 0, 0), ocultarMaquina);
    }

    private void exibirCarta(JPanel painel, Carta carta, String titulo, Color cor, boolean oculto) {
        painel.removeAll();
        painel.setLayout(new BorderLayout(0, 5));
        painel.setBackground(new Color(40, 40, 40));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(cor);
        lblTitulo.setBorder(new EmptyBorder(5, 0, 5, 0));
        painel.add(lblTitulo, BorderLayout.NORTH);

        if (oculto) {
            JPanel placeholderPanel = new JPanel(new GridBagLayout());
            placeholderPanel.setBackground(new Color(25, 25, 25));
            JLabel questionMarks = new JLabel("???");
            questionMarks.setFont(new Font("Impact", Font.BOLD, 80));
            questionMarks.setForeground(new Color(60, 60, 60));
            placeholderPanel.add(questionMarks);
            painel.add(placeholderPanel, BorderLayout.CENTER);
        } else {
            String caminhoImagem = ImagemUtil.getCaminhoCartaPorId(carta.getId());
            ImageIcon icon = ImagemUtil.carregarImagem(caminhoImagem, 200, 280);
            JLabel lblImagem = new JLabel(icon);
            lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
            painel.add(lblImagem, BorderLayout.CENTER);

            JPanel painelInfo = new JPanel();
            painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
            painelInfo.setOpaque(false);
            painelInfo.setBorder(new EmptyBorder(5, 10, 5, 10));

            JLabel nomeCarta = new JLabel(carta.getNome().toUpperCase(), SwingConstants.CENTER);
            nomeCarta.setFont(new Font("Impact", Font.PLAIN, 20));
            nomeCarta.setForeground(Color.WHITE);
            nomeCarta.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelInfo.add(nomeCarta);
            painelInfo.add(Box.createVerticalStrut(10));

            painelInfo.add(criarLinhaAtributo("💪 Força:", carta.getForca()));
            painelInfo.add(criarLinhaAtributo("🏃 Velocidade:", carta.getVelocidade()));
            painelInfo.add(criarLinhaAtributo("🎯 Habilidade:", carta.getHabilidade()));
            painelInfo.add(criarLinhaAtributo("🛠️ Equipamento:", carta.getEquipamento()));
            painelInfo.add(criarLinhaAtributo("🧠 Inteligência:", carta.getInteligencia()));
            painel.add(painelInfo, BorderLayout.SOUTH);
        }

        painel.revalidate();
        painel.repaint();
    }

    private JPanel criarLinhaAtributo(String nome, int valor) {
        JPanel linha = new JPanel(new BorderLayout());
        linha.setOpaque(false);
        linha.setMaximumSize(new Dimension(300, 25));

        JLabel lblNome = new JLabel(nome);
        lblNome.setFont(new Font("Arial", Font.BOLD, 14));
        lblNome.setForeground(Color.LIGHT_GRAY);
        linha.add(lblNome, BorderLayout.WEST);

        JLabel lblValor = new JLabel(String.valueOf(valor));
        lblValor.setFont(new Font("Arial", Font.BOLD, 14));
        lblValor.setForeground(Color.WHITE);
        linha.add(lblValor, BorderLayout.EAST);

        return linha;
    }

    private void compararAtributo(String atributo) {
        if (!aguardandoComparacao) {
            lblResultado.setText("Clique em 'PRÓXIMA RODADA'.");
            return;
        }

        try {
            SuperTrunfoController.ResultadoRodada resultado = controller.jogarRodada(cartaJogadorAtual, atributo);
            atualizarExibicaoCartas(false); // Revela a carta da máquina

            if (resultado.vencedor == 1) {
                vitoriasJogador++;
                lblResultado.setText("✨ VOCÊ GANHOU A RODADA! ✨");
                lblResultado.setForeground(new Color(0, 220, 0));
            } else if (resultado.vencedor == 2) {
                vitoriasMaquina++;
                lblResultado.setText("💀 VOCÊ PERDEU A RODADA! 💀");
                lblResultado.setForeground(new Color(220, 0, 0));
            } else {
                lblResultado.setText("🤝 EMPATE! 🤝");
                lblResultado.setForeground(new Color(255, 200, 0));
            }

            atualizarPlacar();
            aguardandoComparacao = false;

            for (Component comp : painelAtributos.getComponents()) {
                comp.setEnabled(false);
            }

            if (resultado.fimJogo) {
                Timer timer = new Timer(2000, e -> finalizarJogo());
                timer.setRepeats(false);
                timer.start();
            }
        } catch (Exception ex) {
            lblResultado.setText("Erro na jogada: " + ex.getMessage());
            lblResultado.setForeground(Color.RED);
            ex.printStackTrace();
        }
    }

    private void atualizarPlacar() {
        lblPlacarJogador.setText("VOCÊ: " + vitoriasJogador);
        lblPlacarMaquina.setText("MÁQUINA: " + vitoriasMaquina);
    }

    private void finalizarJogo() {
        this.dispose();
        new TelaResultado(vitoriasJogador, vitoriasMaquina);
    }

    private void voltarAoMenu() {
        this.dispose();
        new TelaMenu();
    }
}