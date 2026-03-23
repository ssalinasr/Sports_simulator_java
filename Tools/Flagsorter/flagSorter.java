/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools.Flagsorter;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Takamaru Senshi
 */
public class flagSorter {

    private String ruta;

    public flagSorter(String r) {
        this.ruta = r;
    }

    /**
     * Función que asigna la BANDERA a un equipo específico
     *
     * @param country: nombre del equipo
     * @return ImageIcon con la BANDERA del equipo
     */
    public ImageIcon setFlags(String country) {
        ImageIcon f;
        f = new ImageIcon(getClass().getResource(this.ruta));
        return f;
    }

    /**
     * Función que asigna el ESCUDO a un equipo específico
     *
     * @param country: nombre del equipo
     * @return ImageIcon con el ESCUDO del equipo
     */
    public ImageIcon setShields(String country) {
        ImageIcon f;
        f = new ImageIcon(getClass().getResource(this.ruta));
        return f;
    }

}
