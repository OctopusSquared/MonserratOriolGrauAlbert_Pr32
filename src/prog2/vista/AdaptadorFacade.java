package Vista;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorFacade implements IAdaptadorBiblio {

    // CANVIA AQUEST FQCN PEL TEU ADAPTADOR REAL:
    private static final String ADAPTADOR_CLASS = "prog2.adaptador.Adaptador";

    private final Object adaptador;

    public AdaptadorFacade() {
        try {
            Class<?> c = Class.forName(ADAPTADOR_CLASS);
            this.adaptador = c.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("No he pogut crear l'adaptador. Revisa ADAPTADOR_CLASS.", e);
        }
    }

    // --- Helpers ---
    @SuppressWarnings("unchecked")
    private List<String> invokeListString(String methodName) throws Exception {
        Method m = adaptador.getClass().getMethod(methodName);
        Object res = m.invoke(adaptador);
        if (res instanceof List) return (List<String>) res;
        // si retorna array o una altra cosa, adapta aquí
        return new ArrayList<>();
    }

    private void invokeVoid(String methodName, Class<?>[] params, Object[] args) throws Exception {
        Method m = adaptador.getClass().getMethod(methodName, params);
        m.invoke(adaptador, args);
    }

    // --- Usuaris ---
    @Override
    public List<String> llistaUsuaris() throws Exception {
        // CANVIA "recuperaUsuaris" si el teu mètode es diu diferent
        return invokeListString("recuperaUsuaris");
    }

    @Override
    public void afegirUsuari(String nom, String email, boolean estudiant) throws Exception {
        // CANVIA "afegirUsuari" i paràmetres si cal
        invokeVoid("afegirUsuari",
                new Class<?>[]{String.class, String.class, boolean.class},
                new Object[]{nom, email, estudiant});
    }

    // --- Exemplars ---
    @Override
    public List<String> llistaExemplars() throws Exception {
        // L’enunciat menciona explícitament recuperaExemplars [1](https://github.com/OctopusSquared/MonserratOriolGrauAlbert)
        return invokeListString("recuperaExemplars");
    }

    @Override
    public void afegirExemplar(String id, String titol, boolean prestecLlarg) throws Exception {
        invokeVoid("afegirExemplar",
                new Class<?>[]{String.class, String.class, boolean.class},
                new Object[]{id, titol, prestecLlarg});
    }

    // --- Préstecs ---
    @Override
    public List<String> llistaPrestecs(boolean nomesNoRetornats) throws Exception {
        // Si tens dos mètodes: recuperaPrestecs() i recuperaPrestecsNoRetornats()
        if (nomesNoRetornats) return invokeListString("recuperaPrestecsNoRetornats");
        return invokeListString("recuperaPrestecs");
    }

    @Override
    public void afegirPrestec(String usuariStr, String exemplarStr, boolean prestecLlarg) throws Exception {
        // Aquí passem els Strings seleccionats; si el teu adaptador demana ID/email,
        // parseja usuariStr/exemplarStr o canvia la signatura.
        invokeVoid("afegirPrestec",
                new Class<?>[]{String.class, String.class, boolean.class},
                new Object[]{usuariStr, exemplarStr, prestecLlarg});
    }

    @Override
    public void retornarPrestec(int indexPrestec) throws Exception {
        // alternativa: si el teu adaptador retorna per objecte o id, adapta-ho
        invokeVoid("retornarPrestec",
                new Class<?>[]{int.class},
                new Object[]{indexPrestec});
    }

    // --- Guardar / Carregar ---
    @Override
    public void guardar(String nomFitxer) throws Exception {
        // l’enunciat diu demanar nom fitxer per guardar [1](https://github.com/OctopusSquared/MonserratOriolGrauAlbert)
        invokeVoid("guardarDades",
                new Class<?>[]{String.class},
                new Object[]{nomFitxer});
    }

    @Override
    public void carregar(File fitxer) throws Exception {
        // l’enunciat diu usar JFileChooser per carregar [1](https://github.com/OctopusSquared/MonserratOriolGrauAlbert)
        invokeVoid("carregarDades",
                new Class<?>[]{String.class},
                new Object[]{fitxer.getAbsolutePath()});
    }
}
