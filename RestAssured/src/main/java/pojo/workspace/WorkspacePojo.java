package pojo.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;

//this annotation will not include the fields whose value is Null so that we can ignore null values
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)// here it will exclude non utilized fields in the constructor, here we are using this at the class level

@JsonIgnoreProperties(value = "id", allowSetters = true)
public class WorkspacePojo {
    //@JsonInclude(JsonInclude.Include.NON_DEFAULT)// we are using at the field level
    @JsonIgnore// it will ignore entire property from both serialization and deserialization
    private int i;
    private String name;
    private String type;
    private String description;
    //@JsonInclude(JsonInclude.Include.NON_EMPTY)// we are using at the field level
    private String id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)// it will remove empty value from the request body,
    // it also works for Null values, basically it is a super set
    HashMap<String, String> hashMap;

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

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
