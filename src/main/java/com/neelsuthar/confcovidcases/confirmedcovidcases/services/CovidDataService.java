package com.neelsuthar.confcovidcases.confirmedcovidcases.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import com.neelsuthar.confcovidcases.confirmedcovidcases.models.CovidStats;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CovidDataService {

  private static String SOURCE_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

  private List<CovidStats> currentStats = new ArrayList<>();

  @PostConstruct
  @Scheduled(cron = "* * 1 * * *")
  public void getCovidData() {
    List<CovidStats> updatedStats = new ArrayList<>();
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(SOURCE_URL)).build();

    try {
      HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
      StringReader csvReader = new StringReader(httpResponse.body());
      Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
      for (CSVRecord record : records) {
        CovidStats covidStat = new CovidStats();
        covidStat.setState(record.get(0));
        covidStat.setCountry(record.get(1));
        int currCases = Integer.parseInt(record.get(record.size() - 1));
        int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
        covidStat.setCurrentCases(currCases);
        covidStat.setDifFromPrevDay(currCases - prevDayCases);
        // System.out.println(covidStat);
        updatedStats.add(covidStat);
      }
      this.currentStats = updatedStats;
      this.currentStats.sort(Comparator.comparing(CovidStats::getCurrentCasesInt).reversed());
      System.out.println(currentStats);

    } catch (IOException e) {
      e.printStackTrace();
      System.out.println(e);
    } catch (InterruptedException e) {
      e.printStackTrace();
      System.out.println(e);
    }

  }

  public List<CovidStats> getCurrentStats() {
    return currentStats;
  }

  public void setCurrentStats(List<CovidStats> currentStats) {
    this.currentStats = currentStats;
  }
}
