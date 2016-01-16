package ReflectionAPIexample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main{

    private String comp = "javax.swing.JFrame";

    public Main() {
        try {
            Class cls = Class.forName(comp);
            Object obj = cls.newInstance();
            Method meth = cls.getMethod("setVisible", Boolean.TYPE);
            Method meth2 = cls.getMethod("setDefaultCloseOperation", Integer.TYPE);
            meth.invoke(obj, true);
            meth2.invoke(obj, EXIT_ON_CLOSE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public static void main(String[] args) {
        new Main();
          
    }

}
