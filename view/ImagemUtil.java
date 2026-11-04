package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ImagemUtil {

    public static ImageIcon carregarImagem(String caminhoRecurso, int largura, int altura) {
        if (caminhoRecurso == null || !caminhoRecurso.startsWith("/")) {
            System.err.println("❌ Caminho de recurso inválido. Deve começar com '/': " + caminhoRecurso);
            return criarPlaceholder(largura, altura, "Inválido");
        }

        URL url = ImagemUtil.class.getResource(caminhoRecurso);

        if (url == null) {
            System.err.println("❌ Recurso não encontrado no classpath: " + caminhoRecurso);
            return criarPlaceholder(largura, altura, caminhoRecurso);
        }

        try {
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            System.err.println("🚨 Erro ao carregar a imagem: " + caminhoRecurso);
            e.printStackTrace();
            return criarPlaceholder(largura, altura, "Erro");
        }
    }

    /**
     * Converte um ID de carta para o caminho de recurso correto (ex: "/img/cartas/A/A1.png").
     * Funciona tanto se o ID for "1A" quanto "A1".
     */
    public static String getCaminhoCartaPorId(String idCarta) {
        if (idCarta == null || idCarta.isEmpty()) {
            return null;
        }

        String letra = "";
        String numero = "";

        // Procura a letra e o número separadamente no ID
        for (char c : idCarta.toCharArray()) {
            if (Character.isLetter(c)) {
                letra = String.valueOf(c).toUpperCase();
            } else if (Character.isDigit(c)) {
                numero += c;
            }
        }

        // Se faltar a letra ou o número, não tem como montar o caminho
        if (letra.isEmpty() || numero.isEmpty()) {
            return null;
        }

        // Os arquivos nas suas pastas são nomeados como A1.png, A2.png, etc.
        String nomeArquivo = letra + numero + ".png";

        // As pastas são A, B, C, D, E.
        return "/img/cartas/" + letra + "/" + nomeArquivo;
    }

    private static ImageIcon criarPlaceholder(int largura, int altura, String texto) {
        BufferedImage img = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(new Color(40, 40, 70));
        g.fillRect(0, 0, largura, altura);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        String exibir = texto.contains("/") ? texto.substring(texto.lastIndexOf('/') + 1) : texto;
        g.drawString(exibir, 10, altura / 2);
        g.dispose();
        return new ImageIcon(img);
    }
}