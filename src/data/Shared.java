package data;

import object.Class;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Shared {

    private ArrayList<Class> classes = new ArrayList<>();
    private ArrayList<PrintWriter> outs = new ArrayList<>();

    public void addClient(PrintWriter pr){
        outs.add(pr);
    }

    public void removeClient(PrintWriter pr){
        for (int i = 0; i < outs.size(); i++){
            if(outs.get(i).equals(pr)){
                outs.remove(i);
            }
        }
    }

    public String addRequests(String classname,int cases){
        for (Class c: classes) {
            if(c.getClassname().equals(classname)) {
                int tmp = c.getRequest_cases();
                tmp = tmp + cases;
                c.request_cases(tmp);
                return c.getClassname()+" "+Integer.toString(tmp);
            }
        }
        Class oc = new Class(classname);
        oc.request_cases(cases);
        classes.add(oc);
        return oc.getClassname()+" "+oc.getRequest_cases();
    }

    public String getAllRequest() {
        String out = "";
        for (Class c : classes) {
            out = out + c.getClassname() + " " + c.getRequest_cases() + "#";
        }
        return out;
    }

    public String addConfirmations(String classname,int conf_cases){
        for (Class c: classes) {
            if(c.getClassname().equals(classname)) {
                int tmp = c.getRequest_cases();
                tmp = tmp - conf_cases;
                if(tmp < 0){
                    return "ERROR";
                }
                c.request_cases(tmp);
                c.confirmed_cases(conf_cases);
                return c.getClassname()+" "+Integer.toString(conf_cases)+ " (" +Integer.toString(c.getRequest_cases())+")";
            }
        }
        return "ERROR";
    }

    public String getAllConfirmations() {
        String out = "";
        for (Class c : classes) {
            out = out + c.getClassname() + " " + c.getConfirmed_cases() + "#";
        }
        return out;
    }

}
