package bean.onetoone;

/**
 * @Description:人--一对一映射中基于外键的映射
 * @Author:BigRedCaps
 */
public class People
{
    private int peopleId;
    private String peopleName;
    // 用户与身份证信息， 一对一关系
    private IdCard idCard;


    public IdCard getIdCard() {
        return idCard;
    }
    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }
    public int getPeopleId() {
        return peopleId;
    }
    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }
    public String getPeopleName() {
        return peopleName;
    }
    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }
}
