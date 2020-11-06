package JGit;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
@Setter
@Getter

public class JgitControlador {
    private String localPath;
    private String Url;
    private UsernamePasswordCredentialsProvider cp;

    public void CrearRama(String NombreRama)throws IOException, GitAPIException {


        try (Git git = Git.init().setDirectory(new File(localPath)).call()) {
            //Listamos todas las ramas
            List<Ref> call = git.branchList().call();
            for (Ref ref : call) {
                System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
            }
            for (Ref ref : call) {
                System.out.println("Branch: " + ref + " " + ref.getName());
                if (ref.getName().equals(NombreRama)){
                    System.out.println("Eliminar rama");
                    git.branchDelete().setBranchNames(NombreRama).setForce(true).call();
                }else
                {
                    git.branchCreate().setName(NombreRama).call();
                }
                call= git.branchList().call();
                for (Ref ref1: call) {
                    System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
                }
            }





        }



    }
    public void DeleteBranch(String NombreRama)throws IOException, GitAPIException{



    }
    public void CommitAndPush(String commit,String usuario,String contrasena) {
        try {
            Repository loRepository = new FileRepository(localPath);
            try (Git git= Git.open(new File(localPath))) {
                AddCommand addCommand =git.add();
                addCommand.addFilepattern(".").call();
                git.commit().setMessage(commit).call();
                //Agregar al repositorio remoto
                RemoteAddCommand AddRepo = git.remoteAdd();
                AddRepo.setName("origen");
                AddRepo.setUri(new URIish(Url));
                //push
                PushCommand push = git.push();
                push.setCredentialsProvider(new UsernamePasswordCredentialsProvider(usuario,contrasena));


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
    public void initLocal(String mensaje) throws IOException, URISyntaxException, GitAPIException {
        int CantidadRamas= 4;
        try{
        Git git = Git.init().setDirectory(new File(localPath)).call();
        git.add().addFilepattern(".").call();
        git.commit().setMessage(mensaje).setAll(true).call();
            git.branchCreate().setName("Development").call();
            git.branchCreate().setName("Entidades").call();
            git.branchCreate().setName("Servicios").call();
            git.branchCreate().setName("Repositorios").call();
        git.commit().setMessage(mensaje).call();

        }catch (GitAPIException e){
            System.out.println(e.getMessage());
        }

    }
    public void initRemoto(String usuario,String Contra)throws IOException, URISyntaxException, GitAPIException{
        Git git = Git.init().setDirectory(new File(localPath)).call();
        try{
            List<Ref> call = git.branchList().call();
            for (Ref ref: call) {
                int i= 1;
                System.out.println("rama"+i);
                i++;
                git = Git.init().setDirectory(new File(localPath)).call();
                git.remoteAdd().setUri(new URIish(Url)).setName("origen").call();
            }   git.push().setRemote(Url).setCredentialsProvider(new UsernamePasswordCredentialsProvider(usuario,Contra)).setPushAll().add(".").call();
        }catch (GitAPIException|URISyntaxException e){
            System.out.println(e.getMessage());
        }

    }
}
