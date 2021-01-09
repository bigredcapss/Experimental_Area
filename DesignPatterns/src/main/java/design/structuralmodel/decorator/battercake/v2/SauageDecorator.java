package design.structuralmodel.decorator.battercake.v2;


public class SauageDecorator extends BattercakeDecorator{

    public SauageDecorator(Battercake battercake) {
        super(battercake);
    }

    protected String getMsg(){ return super.getMsg() + "1根香肠";}

    public int getPrice(){ return super.getPrice() + 2;}

}
