package net.raksha.report

import org.scalatra._
import java.sql.Date
import scalate.ScalateSupport

import slick.driver.JdbcDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

import java.sql.Timestamp
import scala.concurrent.{Future, Await}


class ReportServlet(val db: Database) extends SurakshaStack with ScalatraBase with FutureSupport {

	protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

        println(db.getClass)
	db.run(DBIO.seq(Tables.assaults += ("id", "lat"," lng", new Timestamp(0), 1)));
        println(db.getClass)


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
        val mode = params("mode").toInt

	val prtString = "ID : "+id+"\nlat : "+lat+"\nlng : "+lng+"\nmode : "+mode



        println(prtString)
	val toRun  = Tables.insertAssault(id,lat,lng,new Timestamp(System.currentTimeMillis),mode);
        println(toRun)
	val ret = db.run(toRun)
	Await.result(ret, Duration.Inf)
	println(ret)
	Thread.sleep(1000);


    <html>
      <body>
        <h1> {prtString}</h1>
      </body>
    </html>



  }


}


object Tables {

 	 // Definition of the ASSAULTS table
  class ASSAULTS(tag:Tag) extends Table[(String, String, String,Timestamp, Int)](tag,Some("public"), "assaults") {
    def id = column[String]("id") // This is the primary key column
    def lat = column[String]("lat")
    def lng = column[String]("lng")
    def timestamp = column[Timestamp]("timestamp")
    def mode = column[Int]("mode")

    // Every table needs a * projection with the same type as the table's type parameter
    def * = (id, lat, lng, timestamp, mode)
  }

 val assaults = TableQuery[ASSAULTS]

  def insertAssault(id:String, lat:String, lng:String, timestamp:Timestamp, mode:Int) = {
	println("inserting intothe table")
	 DBIO.seq(
   	 Tables.assaults += (id, lat, lng, timestamp, mode)
 	 );
  }
}
