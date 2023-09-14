package pojoExample.workspacePojo;

import com.fasterxml.jackson.annotation.JsonInclude;

//this annotation will not include the fields whose value is Null so that we can ignore null values
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)// here it will exclude non utilized fields in the constructor, here we are using this at the class level
public class WorkspacePojo {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)// we are using at the field level
    private int i;
    private String name;
    private String type;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)// we are using at the field level
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WorkspacePojo(){

    }


    public WorkspacePojo(String name, String type, String description){
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }





}
