package design.structuralmodel.adapter.demo.passport.adapterv1;


public class Test {
    public static void main(String[] args) {
        PassportForThirdAdapter adapter = new PassportForThirdAdapter();
        adapter.login("tom","123456");
        adapter.loginForQQ("sjooguwoersdfjhasjfsa");
        adapter.loginForWechat("slfsjoljsdo8234ssdfs");
    }
}
