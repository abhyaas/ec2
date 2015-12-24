package net.raksha.report

import org.scalatra._
import java.sql.Date
import scalate.ScalateSupport

import slick.driver.JdbcDriver.api._

//import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

import java.sql.Timestamp
import scala.concurrent.{Future, Await}


class ReportServlet(val db: Database) extends SurakshaStack with ScalatraBase with FutureSupport {

	protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

	
	val heatmap = scala.io.Source.fromFile("./src/main/html/sample-heatmap.html").mkString

	

  get("/") {
    <html>
      <body>
        <h1>aHello, world!</h1>
	
	        Say <a href="hello-scalate">hello to Scalate </a>.
	<h2>{ System.getProperty("user.dir")}</h2>
      </body>
    </html>
  }

  get("/heatmap/"){


	contentType="text/html"

/**
	val latLongs = db.run(Tables.getGoogleLatLongs.result) map { xs =>
		xs map { case(lat,lng) => f"new google.maps.LatLng($lat,$lng)" } mkString ","
	};
  	println(latLongs)
	latLongs.map(x => println(x) )
	heatmap.replace("LAT_LONG_LIST_HERE",latLongs.value.get.get);

       heatmap.replace("LAT_LONG_LIST_HERE",db.run(Tables.getGoogleLatLongs.result) map { xs =>
                xs map { case(lat,lng) => f"new google.maps.LatLng($lat,$lng)" } mkString ","
        });

*/

db.run(Tables.getGoogleLatLongs.result) map { xs =>
      contentType = "text/html"

               heatmap.replace("LAT_LONG_LIST_HERE", xs map { case(lat,lng) => f"new google.maps.LatLng($lat,$lng)" } mkString ",")
        }

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

     contentType = "text/html"
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


  val getGoogleLatLongs = {
	for {
	    c <- assaults
	  } yield (c.lat, c.lng)
  }


}
