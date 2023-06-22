package gerenciador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

class HospedagemDAO {
    private final String INSERT = "INSERT INTO HOSPEDAGEM (NOME, TIPO, LOCAL, CHECKIN) VALUES(?,?,?,?)";
    private final String UPDATE = "UPDATE HOSPEDAGEM SET NOME=?, TIPO=?, LOCAL=?, DIAVIAGEM=? WHERE IDHOSPEDAGEM=?";
    private final String DELETE = "DELETE FROM HOSPEDAGEM WHERE IDHOSPEDAGEM=?";
    private final String LIST = "SELECT * FROM HOSPEDAGEM";
    private final String LISTBYID = "SELECT * FROM HOSPEDAGEM WHERE IDHOSPEDAGEM=?";
    
    public ArrayList<Hospedagem> getHospedagens(){
      Connection con = null;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      ArrayList<Hospedagem> hospedagens = new ArrayList<>();
      try{
      con = FabricaConexao.getConexao();
      pstm = con.prepareStatement(LIST);
      rs = pstm.executeQuery();
      while(rs.next()){
          Hospedagem hospedagem = new Hospedagem();
          hospedagem.setId(rs.getInt("idHospedagem"));
          hospedagem.setNome(rs.getString("nome"));
          hospedagem.setTipo(rs.getString("tipo"));
          hospedagem.setLocal(rs.getString("local"));
          hospedagem.setCheckin(rs.getString("checkin"));
          hospedagens.add(hospedagem);
      }
      
      FabricaConexao.fechaConexao(con, pstm, rs);
      } catch(Exception e){
          JOptionPane.showMessageDialog(null, "Erro ao listar hospedagens!" + e.getMessage());
      }
      return hospedagens;
    }
    
    public void inserirHospedagem(Hospedagem hospedagem){
        if(hospedagem != null){
            Connection con = null;
            try{
                con = FabricaConexao.getConexao();
                PreparedStatement pstm;
                pstm = con.prepareStatement(INSERT);
                pstm.setString(1, hospedagem.getNome());
                pstm.setString(2, hospedagem.getTipo());
                pstm.setString(3, hospedagem.getLocal());
                pstm.setString(4, hospedagem.getCheckin());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Hospedagem cadastrada com sucesso!");
                FabricaConexao.fechaConexao(con, pstm);
                
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Erro ao inserir hospedagem no banco de dados "
                        + e.getMessage());
            }
        } else{
            JOptionPane.showMessageDialog(null, "Dados inválidos para hospedagem!");
        }
    }
    
    public void atualizar(Hospedagem hospedagem){
        if (hospedagem != null){
            Connection con = null;
            try{
                con = FabricaConexao.getConexao();
                PreparedStatement pstm;
                pstm = con.prepareStatement(UPDATE);
                pstm.setString(1, hospedagem.getNome());
                pstm.setString(2, hospedagem.getTipo());
                pstm.setString(3, hospedagem.getLocal());
                pstm.setString(4, hospedagem.getCheckin());
                pstm.setInt(5, hospedagem.getId());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Hospedagem atualizado com sucesso!");
                FabricaConexao.fechaConexao(con, pstm);
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, 
                        "Erro ao atualizar hospedagem!"+e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "A hospedagem está vazia!");
        }
    }
    
    public Hospedagem getHospedagemByID(int id){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Hospedagem hospedagem = new Hospedagem();
        try{
            con = FabricaConexao.getConexao();
            pstm = con.prepareStatement(LISTBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while(rs.next()){
                hospedagem.setId(rs.getInt("idhospedagem"));
                hospedagem.setNome(rs.getString("nome"));
                hospedagem.setTipo(rs.getString("tipo"));
                hospedagem.setLocal(rs.getString("local"));
                hospedagem.setCheckin(rs.getString("checkin"));
            }
            FabricaConexao.fechaConexao(con, pstm, rs);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao listar hospedagens!" +
                    e.getMessage());
        }
        
        return hospedagem;
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
            JOptionPane.showMessageDialog(null, "Erro ao excluir hospedagem"+
                    e.getMessage());
        }
    }
}

