/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools.Database;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Takamaru Senshi
 */
public class DBConsultas {//Consulta equipos

    public DBConectar cn;

    public DBConsultas() {
        this.cn = new DBConectar();
    }

    /**
     * Consulta que obtiene los datos asociados a un equipo (buscado por su
     * nombre)
     *
     * @param name: nombre del equipo
     * @return ResultSet con los datos del equipo
     * @throws SQLException
     */
    public ResultSet getTeamByString(String name) throws SQLException {
        PreparedStatement p = cn.getConexion().prepareStatement("select team_def from lvlteams where team_name = ?");
        p.setString(1, name);
        ResultSet r = p.executeQuery();
        return r;
    }

    /**
     * Consulta que obtiene los datos de todos los equipos en la BD
     *
     * @return ResultSet con los datos de los equipos
     * @throws SQLException
     */
    public ResultSet getTeams() throws SQLException {
        PreparedStatement pg = cn.getConexion().prepareStatement("SELECT *"
                + "FROM lvlteams ORDER BY team_name");

        ResultSet rg = pg.executeQuery();
        cn.getConexion().close();
        cn.desconectar();
        return rg;
    }

    /**
     * Consulta que obtiene los datos de todos los equipos en la BD
     *
     * @return ResultSet con los datos de los equipos
     * @throws SQLException
     */
    public ResultSet getTeamsFem() throws SQLException {
        PreparedStatement pg = cn.getConexion().prepareStatement("SELECT *"
                + "FROM lvlteamsf ORDER BY team_name");

        ResultSet rg = pg.executeQuery();
        cn.getConexion().close();
        cn.desconectar();
        return rg;
    }

    /**
     * Consulta que obtiene los datos de todos los equipos en la BD
     *
     * @return ResultSet con los datos de los equipos
     * @throws SQLException
     */
    public ResultSet getCities() throws SQLException {
        PreparedStatement pg = cn.getConexion().prepareStatement("SELECT *"
                + "FROM lvlcities ORDER BY team_name");
        ResultSet rg = pg.executeQuery();
        cn.getConexion().close();
        cn.desconectar();
        return rg;
    }

    /**
     * Consulta que obtiene los datos de todos los equipos en la BD
     *
     * @return ResultSet con los datos de los equipos
     * @throws SQLException
     */
    public ResultSet getClubTeams() throws SQLException {
        PreparedStatement pg = cn.getConexion().prepareStatement("SELECT *"
                + "FROM lvlclubs ORDER BY team_name");
        ResultSet rg = pg.executeQuery();
        cn.getConexion().close();
        cn.desconectar();
        return rg;
    }

    /**
     * Consulta que obtiene los datos de todos los equipos en la BD por su liga
     *
     * @param league: Liga a consultar
     * @return ResultSet con los datos de los equipos
     * @throws SQLException
     */
    public ResultSet getClubTeamsByLeague(String league) throws SQLException {
        PreparedStatement pg = cn.getConexion().prepareStatement("SELECT *"
                + "FROM lvlclubs WHERE team_league = ? ORDER BY team_name");
        pg.setString(1, league);
        ResultSet rg = pg.executeQuery();
        cn.getConexion().close();
        cn.desconectar();
        return rg;
    }

    /**
     * Consulta que obtiene la liga a la que pertenece a un club en específico
     *
     * @param team_name: Club a consultar
     * @return ResultSet con los datos de los equipos
     * @throws SQLException
     */
    public ResultSet getClubLeague(String team_name) throws SQLException {
        PreparedStatement pg = cn.getConexion().prepareStatement("select team_league from lvlclubs where "
                + "team_name = ?");
        pg.setString(1, team_name);
        ResultSet rg = pg.executeQuery();
        cn.getConexion().close();
        cn.desconectar();
        return rg;
    }

    /**
     * Actualiza la liga de un club especificado
     *
     * @param league: Nueva liga
     * @param club_name: Nombre del club a modificar
     * @throws SQLException
     */
    public void updateClubLeague(String league, String club_name) throws SQLException {
        PreparedStatement pg = cn.getConexion().prepareStatement("UPDATE lvlclubs SET team_league = ? "
                + "WHERE team_name = ?");
        pg.setString(1, league);
        pg.setString(2, club_name);
        pg.executeUpdate();
    }

    public ResultSet getClubsLand() throws SQLException {
        PreparedStatement pg = cn.getConexion().prepareStatement("select team_league, "
                + "team_region from lvlclubs group by team_league, team_region order by team_region, team_league asc");
        ResultSet rg = pg.executeQuery();
        cn.getConexion().close();
        cn.desconectar();
        return rg;
    }

    /**
     * Consulta que obtiene los datos de todos los equipos en la BD
     *
     * @return ResultSet con los datos de los equipos
     * @throws SQLException
     */
    public ResultSet getLeagues() throws SQLException {
        PreparedStatement pg = cn.getConexion().prepareStatement("SELECT team_league FROM lvlclubs GROUP BY team_league ORDER BY team_league");
        System.out.println("Clubes?");
        ResultSet rg = pg.executeQuery();
        return rg;

    }

    /**
     * Consulta que obtiene la ruta de la BANDERA de un equipo específico
     *
     * @param country: nombre del equipo
     * @return ResultSet con la ruta de la BANDERA del equipo
     * @throws SQLException
     */
    public ResultSet getRuta(String country) throws SQLException {
        PreparedStatement pg = cn.getConexion().prepareStatement("SELECT ruta "
                + "FROM lvlteams WHERE team_name = ?");
        pg.setString(1, country);
        ResultSet rg = pg.executeQuery();
        return rg;
    }

    /**
     * Consulta que obtiene la ruta del ESCUDO de un equipo específico
     *
     * @param country: nombre del equipo
     * @return ResultSet con la ruta del ESCUDO del equipo
     * @throws SQLException
     */
    public ResultSet getRutaEsc(String country) throws SQLException {
        PreparedStatement pg = cn.getConexion().prepareStatement("SELECT ruta_esc "
                + "FROM lvlteams WHERE team_name = ?");
        pg.setString(1, country);
        ResultSet rg = pg.executeQuery();
        return rg;
    }

    /**
     * Borra TODOS los datos de la tabla de los equipos
     */
    public void DeleteAllPart() {
        try {
            PreparedStatement pda = cn.getConexion().prepareStatement("TRUNCATE TABLE"
                    + " lvlteams");

            pda.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed at: Delete all teams data action", "Warning", 0);
        }
    }

}
