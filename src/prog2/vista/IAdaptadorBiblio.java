package prog2.vista;

import java.io.File;
import java.util.List;

public interface IAdaptadorBiblio {
    List<String> llistaUsuaris() throws Exception;
    void afegirUsuari(String nom, String email, boolean estudiant) throws Exception;

    List<String> llistaExemplars() throws Exception;
    void afegirExemplar(String id, String titol, boolean prestecLlarg) throws Exception;

    List<String> llistaPrestecs(boolean nomesNoRetornats) throws Exception;
    void afegirPrestec(String usuariStr, String exemplarStr, boolean prestecLlarg) throws Exception;
    void retornarPrestec(int indexPrestec) throws Exception;

    void guardar(String nomFitxer) throws Exception;
    void carregar(File fitxer) throws Exception;
}
