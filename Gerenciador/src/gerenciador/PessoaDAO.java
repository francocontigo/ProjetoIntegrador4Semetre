package gerenciador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PessoaDAO {
    private final String INSERT = "INSERT INTO PESSOA (NOME, CPF, EMAIL, TELEFONE) VALUES(?,?,?,?)";
    private final String UPDATE = "UPDATE PESSOA SET NOME=?, CPF=?, EMAIL=?, TELEFONE=? WHERE IDPESSOA=?";
    private final String DELETE = "DELETE FROM PESSOA WHERE IDPESSOA=?";
    private final String LIST = "SELECT * FROM PESSOA";
    private final String LISTBYID = "SELECT * FROM PESSOA WHERE IDPESSOA=?";
    
    public List<Pessoa> getPessoas(){
      Connection con = null;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      ArrayList<Pessoa> pessoas = new ArrayList<>();
      try{
      con = FabricaConexao.getConexao();
      pstm = con.prepareStatement(LIST);
      rs = pstm.executeQuery();
      while(rs.next()){
          Pessoa pessoa = new Pessoa();
          pessoa.setId(rs.getInt("idPessoa"));
          pessoa.setNome(rs.getString("nome"));
          pessoa.setCpf(rs.getString("cpf"));
          pessoa.setEmail(rs.getString("email"));
          pessoa.setTelefone(rs.getString("telefone"));
          pessoas.add(pessoa);
      }
      
      FabricaConexao.fechaConexao(con, pstm, rs);
      } catch(Exception e){
          JOptionPane.showMessageDialog(null, "Erro ao listar contatos!" + e.getMessage());
      }
      return pessoas;
    }
    
    public void inserirPessoa(Pessoa pessoa){
        if(pessoa != null){
            Connection con = null;
            try{
                con = FabricaConexao.getConexao();
                PreparedStatement pstm;
                pstm = con.prepareStatement(INSERT);
                pstm.setString(1, pessoa.getNome());
                pstm.setString(2, pessoa.getCpf());
                pstm.setString(3, pessoa.getEmail());
                pstm.setString(4, pessoa.getTelefone());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!");
                FabricaConexao.fechaConexao(con, pstm);
                
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Erro ao inserir pessoa no banco de dados "
                        + e.getMessage());
            }
        } else{
            JOptionPane.showMessageDialog(null, "Dados inválidos para pessoa!");
        }
    }
    
    public void atualizar(Pessoa pessoa){
        if (pessoa != null){
            Connection con = null;
            try{
                con = FabricaConexao.getConexao();
                PreparedStatement pstm;
                pstm = con.prepareStatement(UPDATE);
                pstm.setString(1, pessoa.getNome());
                pstm.setString(2, pessoa.getCpf());
                pstm.setString(3, pessoa.getEmail());
                pstm.setString(4, pessoa.getTelefone());
                pstm.setInt(5, pessoa.getId());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Pessoa atualizado com sucesso!");
                FabricaConexao.fechaConexao(con, pstm);
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, 
                        "Erro ao atualizar pessoa!"+e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "A pessoa está vazia!");
        }
    }
    
    public Pessoa getPessoaByID(int id){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Pessoa pessoa = new Pessoa();
        try{
            con = FabricaConexao.getConexao();
            pstm = con.prepareStatement(LISTBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while(rs.next()){
                pessoa.setId(rs.getInt("idpessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setTelefone(rs.getString("telefone"));
            }
            FabricaConexao.fechaConexao(con, pstm, rs);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao listar pessoas!" +
                    e.getMessage());
        }
        
        return pessoa;
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
            JOptionPane.showMessageDialog(null, "Erro ao excluir pessoa"+
                    e.getMessage());
        }
    }
}
