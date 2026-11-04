package dao;

import model.Carta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaDAO {

    public List<Carta> listarTodas() throws SQLException {
        List<Carta> cartas = new ArrayList<>();
        String sql = "SELECT id, nome, forca, velocidade, habilidade, equipamento, inteligencia FROM cartas_marvel";
        try (Connection conn = ConfiguracaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cartas.add(new Carta(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getInt("forca"),
                        rs.getInt("velocidade"),
                        rs.getInt("habilidade"),
                        rs.getInt("equipamento"),
                        rs.getInt("inteligencia")
                ));
            }
        }
        return cartas;
    }
}