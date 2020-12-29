package design.structuralmodel.adapter.demo.passport.adapterv2;

public class Test {
    public static void main(String[] args) {
        IPassportForThird adapter = new PassportForThirdAdapter();
        adapter.loginForQQ("sdfasdfasfasfas");
    }
}
