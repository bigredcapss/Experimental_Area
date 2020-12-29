package design.behavioral.delegate.mvc;

import design.behavioral.delegate.mvc.controllers.MemberController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 委派类
 * 注：这里仅仅模仿Spring DispatcherServelet，由于缺少web.xml文件，无法加载该DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {

    private Map<String,Method> handlerMapping = new HashMap<>();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req,resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        String url = req.getRequestURI();
        Method method = handlerMapping.get(url);
//        method.invoke();
    }

    @Override
    public void init() throws ServletException {
        try {
            handlerMapping.put("/web/getMemeberById.json", MemberController.class.getMethod("getMemberById",
                    new Class[]{String.class}));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
