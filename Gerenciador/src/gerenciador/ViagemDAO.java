package gerenciador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ViagemDAO {
    private final String INSERT = "INSERT INTO VIAGEM (PESSOA, TRANSPORTE, HOSPEDAGEM) VALUES(?,?,?)";
    private final String UPDATE = "UPDATE VIAGEM SET PESSOA=?, TRANSPORTE=?, HOSPEDAGEM=? WHERE IDVIAGEM=?";
    private final String DELETE = "DELETE FROM VIAGEM WHERE IDVIAGEM=?";
    private final String LIST = "SELECT * FROM VIAGEM";
    private final String LISTBYID = "SELECT * FROM VIAGEM WHERE IDVIAGEM=?";
    
    public ArrayList<Viagem> getViagens(){
      Connection con = null;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      ArrayList<Viagem> viagens = new ArrayList<>();
      try{
      con = FabricaConexao.getConexao();
      pstm = con.prepareStatement(LIST);
      rs = pstm.executeQuery();
      while(rs.next()){
          Viagem viagem = new Viagem();
          viagem.setId(rs.getInt("idViagem"));
          viagem.setPessoa(rs.getInt("pessoa"));
          viagem.setTransporte(rs.getInt("transporte"));
          viagem.setHospedagem(rs.getInt("hospedagem"));
          viagens.add(viagem);
      }
      
      FabricaConexao.fechaConexao(con, pstm, rs);
      } catch(Exception e){
          JOptionPane.showMessageDialog(null, "Erro ao listar viagens!" + e.getMessage());
      }
      return viagens;
    }
    
    public void inserirViagem(Viagem viagem){
        if(viagem != null){
            Connection con = null;
            try{
                con = FabricaConexao.getConexao();
                PreparedStatement pstm;
                pstm = con.prepareStatement(INSERT);
                pstm.setInt(1, viagem.getPessoa());
                pstm.setInt(2, viagem.getTransporte());
                pstm.setInt(3, viagem.getHospedagem());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Viagem cadastrada com sucesso!");
                FabricaConexao.fechaConexao(con, pstm);
                
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Erro ao inserir viagem no banco de dados "
                        + e.getMessage());
            }
        } else{
            JOptionPane.showMessageDialog(null, "Dados inválidos para viagem!");
        }
    }
    
    public void atualizar(Viagem viagem){
        if (viagem != null){
            Connection con = null;
            try{
                con = FabricaConexao.getConexao();
                PreparedStatement pstm;
                pstm = con.prepareStatement(UPDATE);
                pstm.setInt(1, viagem.getPessoa());
                pstm.setInt(2, viagem.getTransporte());
                pstm.setInt(3, viagem.getHospedagem());
                pstm.setInt(4, viagem.getId());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Viagem atualizada com sucesso!");
                FabricaConexao.fechaConexao(con, pstm);
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, 
                        "Erro ao atualizar viagem!"+e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "A viagem está vazio!");
        }
    }
    
    public Viagem getViagemByID(int id){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Viagem viagem = new Viagem();
        try{
            con = FabricaConexao.getConexao();
            pstm = con.prepareStatement(LISTBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while(rs.next()){
                viagem.setId(rs.getInt("idtransporte"));
                viagem.setPessoa(rs.getInt("pessoa"));
                viagem.setTransporte(rs.getInt("transporte"));
                viagem.setHospedagem(rs.getInt("hospedagem"));
            }
            FabricaConexao.fechaConexao(con, pstm, rs);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao listar viagens!" +
                    e.getMessage());
        }
        
        return viagem;
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
            JOptionPane.showMessageDialog(null, "Erro ao excluir viagem"+
                    e.getMessage());
        }
    }
}

