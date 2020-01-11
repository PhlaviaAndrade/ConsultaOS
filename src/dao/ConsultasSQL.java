/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TabelaOS;

/**
 *
 * @author User
 */
public class ConsultasSQL {
    
          
     public List<TabelaOS> atualizarTabela(String dtInicial, String dtFinal, String UF) throws Throwable{
       
          String sql = "SELECT OS, AnoOS, NumeroOS, NumeroBem, Status, Prefixo, SB, NomeDependencia, ProblemaApresentado, DataHoraAbertura, UF, NumeroContrato,"
                  + " Fornecedor, NrSOL, TipoManutencao from controleosgarantia.controleosgarantia "
                  + "where DataHoraAbertura between'" + dtInicial + "' and '" + dtFinal+ "' and "
                  + "TipoManutencao='corretiva' and status not like'cancelad%' and status not like'PEND.DE FECHAMENTO PELA INTEG.' ";
        List<TabelaOS> consultaOS = new ArrayList<>();
       
        try {
           
            Conector con = new Conector();
            PreparedStatement stmt = con.conectar().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
               TabelaOS to = new TabelaOS();
               
               to.setOS(resultado.getString("OS"));
               to.setAnoOS(resultado.getInt("AnoOS"));
               to.setNumeroOS(resultado.getInt("NumeroOS"));
               to.setNumeroBem(resultado.getString("NumeroBem"));
               to.setStatus(resultado.getString("Status"));
               to.setPrefixo(resultado.getInt("Prefixo"));
              to.setSB(resultado.getInt("SB"));
               to.setNomeDependencia(resultado.getString("NomeDependencia"));
               to.setProblemaApresentado(resultado.getString("ProblemaApresentado"));
               to.setDataHoraAbertura(resultado.getDate("DataHoraAbertura"));
               to.setUF(resultado.getString("UF"));
               to.setNumeroContrato(resultado.getString("NumeroContrato"));
               to.setFornecedor(resultado.getString("Fornecedor"));
               to.setNrSOL(resultado.getString("NrSOL"));
               to.setTipoManutencao(resultado.getString("TipoManutencao"));
               
               
               
                consultaOS.add(to);
            }
           
            con.fechar();
           
        } catch (SQLException ex) {
           
           
            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consultaOS;
    }
}
