package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class TweetMap_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Tweet Map</title>\n");
      out.write("         <style>\n");
      out.write("      html, body, #map-canvas {\n");
      out.write("        height: 100%;\n");
      out.write("        margin: 0px;\n");
      out.write("        padding: 0px\n");
      out.write("      }\n");
      out.write("    </style>\n");
      out.write("    <script src=\"https://maps.googleapis.com/maps/api/js?sensor=false\"></script>\n");
      out.write("    <script>\n");
      out.write("   \n");
      out.write("    var map;\n");
      out.write("    var lat= -25.363882;\n");
      out.write("    var long= 131.044922;\n");
      out.write("    var myLatlng;\n");
      out.write("    function initialize() {\n");
      out.write("    myLatlng = new google.maps.LatLng(lat,long);\n");
      out.write("    var mapOptions = {\n");
      out.write("    zoom: 4,\n");
      out.write("    center: myLatlng\n");
      out.write("    }\n");
      out.write("    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);\n");
      out.write("    marker(lat,long);\n");
      out.write("    marker(-10,-20);\n");
      out.write("    }\n");
      out.write("function marker(lat,long){\n");
      out.write("    var point = new google.maps.LatLng(lat,long);\n");
      out.write("var marker = new google.maps.Marker({\n");
      out.write("      position: point,\n");
      out.write("      map: map,\n");
      out.write("      title: 'Hi !'\n");
      out.write("  });\n");
      out.write("  }\n");
      out.write("   google.maps.event.addDomListener(window, 'load', initialize);\n");
      out.write("   \n");
      out.write("    </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        <div id=\"map-canvas\"/>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
