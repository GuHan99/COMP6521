package project1;

/**
 * Created by ERIC_LAI on 2017-01-20.
 */
public class Employee {

    private int empId;
    private String name;
    private int dept;
    private int sin;
    private String addr;

    public Employee(int empId) {
        this.empId = empId;
    }

    public Employee(int empId, String name, int dept, int sin, String addr) {
        this.empId = empId;
        this.name = name;
        this.dept = dept;
        this.sin = sin;
        this.addr = addr;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public int getSin() {
        return sin;
    }

    public void setSin(int sin) {
        this.sin = sin;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
