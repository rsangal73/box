package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class dash_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<title>Box Content Explorer</title>\n");
      out.write("  <!-- polyfill.io only loads the polyfills your browser needs -->\n");
      out.write("    <script src=\"https://cdn.polyfill.io/v2/polyfill.min.js?features=es6,Intl\"></script>\n");
      out.write("    <!-- Alternatively, use polyfill hosted on the Box CDN\n");
      out.write("    <script src=\"https://cdn01.boxcdn.net/polyfills/core-js/2.5.3/core.min.js\"></script>\n");
      out.write("    -->\n");
      out.write("\n");
      out.write("    <!-- Latest version of the explorer css for your locale -->\n");
      out.write("    <link\n");
      out.write("      rel=\"stylesheet\"\n");
      out.write("      href=\"https://cdn01.boxcdn.net/platform/elements/11.0.2/en-US/explorer.css\"\n");
      out.write("    />\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write(" <div class=\"container\" style=\"height:600px\"></div>\n");
      out.write("    <!-- Latest version of the explorer js for your locale -->\n");
      out.write("    <script src=\"https://cdn01.boxcdn.net/platform/elements/11.0.2/en-US/explorer.js\"></script>\n");
      out.write("    \n");
      out.write("    \n");
      out.write("    <script>\n");
      out.write("      var folderId = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${rootFolder.getID()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\";\n");
      out.write("      var accessToken = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${accessToken}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\";\n");
      out.write("      var contentExplorer = new Box.ContentExplorer();\n");
      out.write("      contentExplorer.show(folderId, accessToken, {\n");
      out.write("        container: \".container\"\n");
      out.write("      });\n");
      out.write("    </script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
      out.write("  \n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
