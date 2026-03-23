/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools.RRAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Flia_Salinas
 */
public class calendarioRR {

    public String runScript(String countries, boolean h) {
        String s = "";
        String fix = "";
        Process p = null;
        File file = new File("src/Tools/RRAlgorithm/RR_Tournament.py");
        File fileh = new File("src/Tools/RRAlgorithm/RR_Tournament_H.py");

        try {
            if (!h) {
                p = Runtime.getRuntime().exec(new String[]{"py", file.getAbsolutePath(),
                    countries});
            } else {
                p = Runtime.getRuntime().exec(new String[]{"py", fileh.getAbsolutePath(),
                    countries});

            }
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((s = in.readLine()) != null) {
                System.out.println(s);
                fix += s;
                fix += "\n";
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println(fix);
        return fix;

    }

}
