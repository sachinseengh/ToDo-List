package application;

public class GetList {
    
    private String nums; // Keeping 'nums' as property name
    private String sNo;
    private String work;
    private String work_date;

    public GetList(String nums, String sNo, String work, String work_date) {
        this.nums = nums;
        this.sNo = sNo;
        this.work = work;
        this.work_date = work_date;
    }

    //Function should be in camelcase
    public String getNums() { // Keeping 'getNums' as method name
        return this.nums;
    }

    public String getSNo() {
        return this.sNo;
    }

    public String getWork() {
        return this.work;
    }

    public String getWork_Date() {
        return this.work_date;
    }
}


