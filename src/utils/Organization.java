package utils;

import java.io.Serializable;

public class Organization implements Serializable {
  private String name;

    public Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
