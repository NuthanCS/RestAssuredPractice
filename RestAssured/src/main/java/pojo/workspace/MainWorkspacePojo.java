package pojo.workspace;

public class MainWorkspacePojo {

    WorkspacePojo workspace;

    public MainWorkspacePojo(){

    }

   public MainWorkspacePojo(WorkspacePojo workspace){
       this.workspace = workspace;
   }
    public WorkspacePojo getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkspacePojo workspace) {
        this.workspace = workspace;
    }


}
