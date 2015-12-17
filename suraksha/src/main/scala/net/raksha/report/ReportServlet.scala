package net.raksha.report

import org.scalatra._
import java.sql.Date
import scalate.ScalateSupport

import slick.driver.JdbcDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global


class ReportServlet extends SurakshaStack with ScalatraBase with FutureSupport {

	protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

	def db: Database

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

	val prtString = "ID : "+id+"\nlat : "+lat+"\nlng : "+lng+"\nmode : "+mode



        println(prtString)
	Tables.insertAssault(id,lat,lng,new java.sql.Date,mode)

    <html>
      <body>
        <h1> {prtString}</h1>
      </body>
    </html>



  }




object Tables {

 	 // Definition of the ASSAULTS table
  class ASSAULTS(tag: Tag) extends Table[(String, String, String,DATE, Int)](tag, "ASSAULTS") {
    def id = column[String]("ID") // This is the primary key column
    def lat = column[String]("LAT")
    def lng = column[String]("LNG")
    def timestamp = column[String]("TIMESTAMP")
    def mode = column[String]("MODE")

    // Every table needs a * projection with the same type as the table's type parameter
    def * = (id, lat, lng, timestamp, mode)
  }
	def insertAssault(id:String, lat:String, lng:String, timestamp:Date, mode:Int) = DBIO.seq(
	  Tables.ASSAULTS += (id, lat, lng, timestamp, mode);
  )
)
}
