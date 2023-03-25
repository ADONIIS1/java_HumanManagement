package lth.com.hrm.HumanManagement.Config;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, AccessDeniedException e)
            throws IOException, ServletException {

        httpServletResponse.setStatus(FORBIDDEN.value());
        Map<String, String> error = new HashMap<>();

        error.put("message", "Bạn không có quyền truy cập !");
        error.put("status", "403");
        httpServletResponse.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(httpServletResponse.getOutputStream(), error);
    }
}
