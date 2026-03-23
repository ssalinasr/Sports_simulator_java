/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Takamaru Senshi
 */
public class DBConsultasOL {//Consulta equipos

    public DBConectar cn;

    public DBConsultasOL() {
        this.cn = new DBConectar();
    }

    /**
     * Consulta que obtiene las categorías de la tabla Londres
     *
     * @return ResultSet con los datos del equipo
     * @throws SQLException
     */
    public ResultSet getCategoriesLondon() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select class_par from par_londres group by class_par");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getCategoriesWinter() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select class_par from par_invierno group by class_par");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getCategoriesTokyo() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select class_par from par_tokyo group by class_par");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getCategoriesEbike() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select class_par from par_excitebike group by class_par");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getCategoriesForm() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select class_par from par_form1 group by class_par");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getCategoriesFzero() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select class_par from par_fzero group by class_par");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getCategoriesGeye() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select class_par from par_goldeneyeps group by class_par");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getCategoriesMkart() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select class_par from par_mkart group by class_par");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getCategoriesSsb() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select class_par from par_ssb group by class_par");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getCategoriesMun() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select class_par from par_muns group by class_par");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getCountriesMun() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select country from muns_ol where country !='no' group by country");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getSportLondon(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select name_par from par_londres where class_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getSportWinter(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select name_par from par_invierno where class_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getSportTokyo(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select name_par from par_tokyo where class_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getSportEbike(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select name_par from par_excitebike where class_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getSportForm(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select name_par from par_form1 where class_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getSportFzero(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select name_par from par_fzero where class_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getSportGeye(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select name_par from par_goldeneyeps where class_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getSportMkart(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select name_par from par_mkart where class_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getSportSsb(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select name_par from par_ssb where class_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getSportMun(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select name_par from par_muns where class_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getTeamsLondon() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select * from teams_ol where is_london = false order by team_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getTeamsTokyo() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select * from teams_ol where is_tokyo = true order by team_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getTeamsOL() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select * from teams_ol order by team_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getTeamsWinterVan() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select * from teams_ol order by team_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getPlayersEbike() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select player_name from player_ol where is_excitebike = true order by player_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getPlayersForm() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select player_name from player_ol where is_form1 = true order by player_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getPlayersFzero() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select player_name from player_ol where is_fzero = true order by player_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getPlayersGeye() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select player_name, ruta from player_ol where is_goldeneye_ps = true order by player_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getPlayersGeyeInd() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select player_name, ruta from player_ol where is_goldeneye = true order by player_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getPlayersMkart() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select player_name, ruta from player_ol where is_kart = true order by player_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getPlayersSsb() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select player_name, ruta from player_ol where is_ssb = true order by player_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getPlayersMun() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select mun_name, value, country from muns_ol order by mun_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getPlayersMunCountries() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select mun_name, value, country from muns_ol where country = 'no' order by mun_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getPlayersMunByCountry(String country) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select mun_name from muns_ol where country = ?");
        p.setString(1, country);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getTeamsAll() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select * from teams_ol order by team_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getPlayersAll() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select * from player_ol order by player_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getTeamsWinterNag() throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select team_name, promedio_dep from teams_ol where team_name = 'Belgica' or team_name = 'Dinamarca' or team_name = 'República Checa' or team_name = 'Polonia' or team_name = 'Portugal' or team_name = 'Corea del Sur' or team_name = 'China' or team_name = 'Eslovenia' union select team_name, promedio_dep from teams_ol where is_winter = false order by team_name");
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getValuesLondon(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select best_res, last_res from par_londres where name_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getValuesWinter(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select best_res, last_res from par_invierno where name_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getValuesTokyo(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select best_res, last_res from par_tokyo where name_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getValuesEbike(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select best_res, last_res from par_excitebike where name_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getValuesForm(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select best_res, last_res from par_form1 where name_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getValuesFzero(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select best_res, last_res from par_fzero where name_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getValuesGeye(String sport) throws SQLException {
        sport = sport + "%";
        PreparedStatement p = cn.getConexion().prepareStatement("select best_res, last_res from par_goldeneyeps where name_par like ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getValuesMkart(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select best_res, last_res from par_mkart where name_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getValuesSsb(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select best_res, last_res from par_ssb where name_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

    public ResultSet getValuesMun(String sport) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select best_res, last_res from par_muns where name_par = ?");
        p.setString(1, sport);
        ResultSet r = p.executeQuery();
        return r;
    }

}
