import java.sql.Date;

class DAO {
    public DAO(){

    }

    public DAO(String first_name, String last_name, Date hire_date){
        super();
        this.first_name=first_name;
        this.last_name=last_name;
        this.hire_date=hire_date;
    }
    private String first_name;
    private String last_name;
    private Date hire_date;
    private int salary;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}