package main.java.com.codingcon.model;

import com.google.gson.Gson;

public class Source extends MapObject {
    private String sourceId;
    public Source(int r, int c) {
        this.setPosition(r,c);
    }

    public Source(String sourceId) {
        String[] coordinates = sourceId.split("_");
        int r = Integer.parseInt(coordinates[0]);
        int c = Integer.parseInt(coordinates[1]);
        this.setPosition(r,c);
    }

    @Override
    public void setPosition(int r, int c) {
        super.setPosition(r, c);
        this.setSourceId(r + "_" + c);
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Source)) {
            return false;
        }

        Source s = (Source) o;

        return sourceId.equals(s.getSourceId());
    }

}
