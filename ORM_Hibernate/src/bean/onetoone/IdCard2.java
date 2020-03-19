package bean.onetoone;

import java.io.Serializable;

/**
 * @Description:身份证--一对一映射中基于主键的映射
 * @Author:BigRedCaps
 */
public class IdCard2
{
    // 身份证号(主键)
    private String cardNum;// 对象唯一标示
    private String place; //  身份证地址
    // 身份证与用户，一对一的关系
    private People2 people;
    private int people_id;

    public int getPeople_id ()
    {
        return people_id;
    }

    public void setPeople_id (int people_id)
    {
        this.people_id = people_id;
    }


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
    public People2 getPeople() {
        return people;
    }
    public void setPeople(People2 user) {
        this.people = user;
    }
}
