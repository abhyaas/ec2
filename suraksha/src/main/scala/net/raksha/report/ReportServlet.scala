package net.raksha.report

import org.scalatra._
import scalate.ScalateSupport

class ReportServlet extends SurakshaStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }


  get("/report/:id"){
        val id = params("id");
        val lat = params("lat")
        val lng = params("lng")
        val mode = params("mode")

        println("ID : "+id+"\nlat : "+lat+"\nlng : "+lng+"\nmode : "+mode)


  }


}
