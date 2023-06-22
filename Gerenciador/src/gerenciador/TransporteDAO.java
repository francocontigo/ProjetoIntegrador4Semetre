package gerenciador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

class TransporteDAO {
    private final String INSERT = "INSERT INTO TRANSPORTE (NOME, TIPO, LOCAL, DIAVIAGEM) VALUES(?,?,?,?)";
    private final String UPDATE = "UPDATE TRANSPORTE SET NOME=?, TIPO=?, LOCAL=?, DIAVIAGEM=? WHERE IDTRANSPORTE=?";
    private final String DELETE = "DELETE FROM TRANSPORTE WHERE IDTRANSPORTE=?";
    private final String LIST = "SELECT * FROM TRANSPORTE";
    private final String LISTBYID = "SELECT * FROM TRANSPORTE WHERE IDTRANSPORTE=?";
    
    public ArrayList<Transporte> getTransportes(){
      Connection con = null;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      ArrayList<Transporte> transportes = new ArrayList<>();
      try{
      con = FabricaConexao.getConexao();
      pstm = con.prepareStatement(LIST);
      rs = pstm.executeQuery();
      while(rs.next()){
          Transporte transporte = new Transporte();
          transporte.setId(rs.getInt("idTransporte"));
          transporte.setNome(rs.getString("nome"));
          transporte.setTipo(rs.getString("tipo"));
          transporte.setLocal(rs.getString("local"));
          transporte.setDiaViagem(rs.getString("diaviagem"));
          transportes.add(transporte);
      }
      
      FabricaConexao.fechaConexao(con, pstm, rs);
      } catch(Exception e){
          JOptionPane.showMessageDialog(null, "Erro ao listar transportes!" + e.getMessage());
      }
      return transportes;
    }
    
    public void inserirTransporte(Transporte transporte){
        if(transporte != null){
            Connection con = null;
            try{
                con = FabricaConexao.getConexao();
                PreparedStatement pstm;
                pstm = con.prepareStatement(INSERT);
                pstm.setString(1, transporte.getNome());
                pstm.setString(2, transporte.getTipo());
                pstm.setString(3, transporte.getLocal());
                pstm.setString(4, transporte.getDiaViagem());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Transporte cadastrado com sucesso!");
                FabricaConexao.fechaConexao(con, pstm);
                
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Erro ao inserir transporte no banco de dados "
                        + e.getMessage());
            }
        } else{
            JOptionPane.showMessageDialog(null, "Dados inválidos para transporte!");
        }
    }
    
    public void atualizar(Transporte transporte){
        if (transporte != null){
            Connection con = null;
            try{
                con = FabricaConexao.getConexao();
                PreparedStatement pstm;
                pstm = con.prepareStatement(UPDATE);
                pstm.setString(1, transporte.getNome());
                pstm.setString(2, transporte.getTipo());
                pstm.setString(3, transporte.getLocal());
                pstm.setString(4, transporte.getDiaViagem());
                pstm.setInt(5, transporte.getId());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Transporte atualizado com sucesso!");
                FabricaConexao.fechaConexao(con, pstm);
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, 
                        "Erro ao atualizar transporte!"+e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "O transporte está vazio!");
        }
    }
    
    public Transporte getTransporteByID(int id){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Transporte transporte = new Transporte();
        try{
            con = FabricaConexao.getConexao();
            pstm = con.prepareStatement(LISTBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while(rs.next()){
                transporte.setId(rs.getInt("idtransporte"));
                transporte.setNome(rs.getString("nome"));
                transporte.setTipo(rs.getString("tipo"));
                transporte.setLocal(rs.getString("local"));
                transporte.setDiaViagem(rs.getString("diaviagem"));
            }
            FabricaConexao.fechaConexao(con, pstm, rs);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao listar transportes!" +
                    e.getMessage());
        }
        
        return transporte;
    }
    
    public void remover(int id){
        Connection con = null;
        try{
            con = FabricaConexao.getConexao();
            PreparedStatement pstm;
            pstm = con.prepareStatement(DELETE);
            pstm.setInt(1, id);
            pstm.execute();
            FabricaConexao.fechaConexao(con, pstm);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao excluir transporte"+
                    e.getMessage());
        }
    }
}

