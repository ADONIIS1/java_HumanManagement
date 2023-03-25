package lth.com.hrm.HumanManagement.Config;

public class AuthenticateEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            AuthenticationException e) throws IOException, ServletException {

        String a = e.getMessage();
        httpServletResponse.sendError(401, "Unauthoried rồi trời ạ !");
    }
}
