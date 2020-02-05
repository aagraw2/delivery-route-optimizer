package main.java.com.codingcon.model;

import com.google.gson.Gson;

public class Destination extends MapObject {
    private String destinationId;
    public Destination(int r, int c) {
        this.setPosition(r,c);
    }

    public Destination(String destinationId) {
        String[] coordinates = destinationId.split("_");
        int r = Integer.parseInt(coordinates[0]);
        int c = Integer.parseInt(coordinates[1]);
        this.setPosition(r,c);
    }

    @Override
    public void setPosition(int r, int c) {
        super.setPosition(r, c);
        this.setDestinationId(r + "_" + c);
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
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

        if (!(o instanceof Destination)) {
            return false;
        }

        Destination d = (Destination) o;

        return destinationId.equals(d.getDestinationId());
    }
}
