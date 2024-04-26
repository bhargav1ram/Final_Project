/*
 * classes will implement this interface when the have to pull and push
 * dta from the databse
 */
public interface AdminObserver {
    /*
     * update method of observer which admin or others call so that
     * admin or other entities call to get latest from database
     */
    public void getUpdateFromAdmin();
}
