package JGit;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, GitAPIException {
        JgitControlador JgitC= new JgitControlador();
        // String Local="D:\\AlumnoApiJgit";
        // String Url="https://github.com/arkanjel/AlumnoApiJgit.git";
        String usuario="arkajel";
        String contrasena="ivo.1997";
        String Mensaje = "Hola soy un commit";

        JgitC.setLocalPath("D:/AlumnoApiJgit/");
        JgitC.setUrl("https://github.com/arkanjel/ParcialAlumno.git");
        try {
            //creamos las ramas
           // JgitC.CrearRama("Development");
           // JgitC.CrearRama("Controladores");
           // JgitC.CrearRama("Servicios");
           // JgitC.CrearRama("Entidades");

            //JgitC.CommitAndPush("hola","arkanjel","ivo.1997");
          //  JgitC.pushToRepo(usuario,contrasena);
            JgitC.initLocal(Mensaje);
            JgitC.initRemoto(usuario,contrasena);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
