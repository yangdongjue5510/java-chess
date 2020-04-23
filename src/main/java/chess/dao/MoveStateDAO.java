package chess.dao;

import chess.dto.MoveStateDTO;

import java.sql.*;

public class MoveStateDAO {

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "chess_db"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void addMoveState(MoveStateDTO moveStateDTO) throws SQLException {
        String query = "INSERT INTO moveState VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, moveStateDTO.getPlayer().getPlayerId());
        pstmt.setString(2, moveStateDTO.getSource());
        pstmt.setString(3, moveStateDTO.getTarget());
        pstmt.setInt(4, moveStateDTO.getMoveCount());
        pstmt.executeUpdate();
    }

    public ResultSet findByPlayerId(String playerId) throws SQLException {
        String query = "SELECT * FROM moveState WHERE id = ? ORDER BY moveCount ASC";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, playerId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;
        return rs;
    }

    public void deleteMoveStateById(String playerId) throws SQLException {
        String query = "DELETE from moveState WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, playerId);
        pstmt.executeUpdate();
    }
}