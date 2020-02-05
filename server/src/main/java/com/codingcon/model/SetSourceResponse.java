package main.java.com.codingcon.model;

import com.google.gson.Gson;

public class SetSourceResponse {
    Source oldSource;
    Source newSource;

    public SetSourceResponse(Source oldSource, Source newSource){
        this.oldSource=oldSource;
        this.newSource=newSource;
    }

    public Source getOldSource() {
        return oldSource;
    }

    public void setOldSource(Source oldSource) {
        this.oldSource = oldSource;
    }

    public Source getNewSource() {
        return newSource;
    }

    public void setNewSource(Source newSource) {
        this.newSource = newSource;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
}
