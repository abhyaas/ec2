import urllib
import random

_lat = 28.609731
_lng = 77.229671

lat = _lat
lng = _lng

for x in range(0,100):
        factor=1000
        if(random.random > 0.5):
                factor=factor*10
        if(random.random < 0.3):
                lat = _lat
                lng = _lng

        lat = lat*(1 + random.uniform(-1,1)/factor)
        lng = lng*(1 + random.uniform(-1,1)/(2*factor))
        params = urllib.urlencode({'lat':lat,'lng':lng,'mode':1})
        f = urllib.urlopen("http://localhost:8080/report/10?%s" % params)
        print "loaded 1 assault #%s" % x


