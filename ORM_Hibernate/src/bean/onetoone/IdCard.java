package bean.onetoone;

/**
 * @Description:身份证实体--一对一映射中基于外键的映射
 * @Author:BigRedCaps
 */
public class IdCard
{
    // 身份证号(主键)
    private String cardNum;// 对象唯一标示
    private String place; //  身份证地址
    // 身份证与用户，一对一的关系
    private People people;


    public String getCardNum() {
        return cardNum;
    }
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public People getPeople() {
        return people;
    }
    public void setPeople(People user) {
        this.people = user;
    }
}
