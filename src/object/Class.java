package object;

public class Class {

    private String classname;
    private int request_cases;
    private int confirmed_cases;

    public Class(String classname){
        this.classname = classname;
    }

    public String getClassname(){
        return classname;
    }

    public int getRequest_cases() {
        return request_cases;
    }

    public int request_cases(int request_cases) {
        this.request_cases = request_cases;
        return request_cases;
    }

    public int getConfirmed_cases() {
        return confirmed_cases;
    }

    public int confirmed_cases(int confirmed_cases) {
        this.confirmed_cases = confirmed_cases;
        return confirmed_cases;
    }
}
