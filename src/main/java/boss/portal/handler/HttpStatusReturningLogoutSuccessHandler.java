//package boss.portal.handler;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @Auther: christ
// * @Date: 2018/9/20 15:02
// * @Description:
// */
//public class HttpStatusReturningLogoutSuccessHandler implements LogoutSuccessHandler {
//
//    private final HttpStatus httpStatusToReturn;
//
//    public HttpStatusReturningLogoutSuccessHandler() {
//        this.httpStatusToReturn = HttpStatus.OK;
//    }
//
//    @Override
//    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        response.setStatus(httpStatusToReturn.value());
//        response.getWriter().flush();
//    }
//}
