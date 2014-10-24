
package edu;

import java.util.Properties;

public class SystemProperties extends Object {

    private SystemProperties() {
        super();
    }

    public static void main(String[] args) {
        Properties p = System.getProperties();
        p.list(System.out);
    }

}
