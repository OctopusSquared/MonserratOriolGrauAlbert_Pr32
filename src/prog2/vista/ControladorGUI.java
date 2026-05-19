package Vista;

import java.io.File;
import java.util.List;

public class ControladorGUI {

    private final IAdaptadorBiblio adapt;

    public ControladorGUI(IAdaptadorBiblio adapt) {
        this.adapt = adapt;
    }

    public List<String> usuaris() throws Exception { return adapt.llistaUsuaris(); }
    public void afegirUsuari(String nom, String email, boolean estudiant) throws Exception {
        adapt.afegirUsuari(nom, email, estudiant);
    }

    public List<String> exemplars() throws Exception { return adapt.llistaExemplars(); }
    public void afegirExemplar(String id, String titol, boolean prestecLlarg) throws Exception {
        adapt.afegirExemplar(id, titol, prestecLlarg);
    }

    public List<String> prestecs(boolean nomesNoRetornats) throws Exception { return adapt.llistaPrestecs(nomesNoRetornats); }
    public void afegirPrestec(String usuariStr, String exemplarStr, boolean prestecLlarg) throws Exception {
        adapt.afegirPrestec(usuariStr, exemplarStr, prestecLlarg);
    }
    public void retornarPrestec(int index) throws Exception { adapt.retornarPrestec(index); }

    public void guardar(String nomFitxer) throws Exception { adapt.guardar(nomFitxer); }
    public void carregar(File f) throws Exception { adapt.carregar(f); }
}
