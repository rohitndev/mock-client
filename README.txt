This is a maven project


TO START APPLICATION:

IN IDE:
Run net.upstox.analytics.ohlcclient.OhlcClientApplication
In VM Options of Run Configuration: -Dstock.subscribe=XXBTZUSD

AS JAR:

A.Build
1. mvn clean install

B. RUN
1. In CMD: java -Dstock.subscribe=XXBTZUSD -jar ohlc-client-0.0.1-SNAPSHOT.jar

