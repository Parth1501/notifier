package com.job.scrapper.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Scrapper {
    public List<List<String>> scrapArogyaSathi() {
        String url="https://arogyasathi.gujarat.gov.in/CurrentOpenings.aspx";
        List<List<String>> res=new ArrayList<>();
        try {
            // Fetch the HTML document from the given URL
            Document document = Jsoup.connect(url).get();

            // Example: Get the title of the page

            // Example: Select specific elements using CSS selectors
            var table = document.getElementById("dynamic-table"); // Select all <p> elements
            var tableData=new ArrayList<List<String>>();
            if (table != null) {
                // Get the table rows
                Elements rows = table.select("tr");

                // Loop through the rows and extract the data
                for (Element row : rows) {
                    List<String> rowData = new ArrayList<>();
                    Elements columns = row.select("th, td"); // Select both <th> and <td> for headers and data

                    for (Element column : columns) {
                        rowData.add(column.text());
                    }

                    tableData.add(rowData);
                }
            }

            // Loop through the paragraphs and extract the text
            for(var row: tableData) {
                var postName=row.get(6);
                if(postName.toLowerCase().contains("physio")) {
                    res.add(row);
                }
            }
//            log.info("Data from scrapping \n{}", tableData);
        } catch (IOException e) {
            log.error("Unable to fetch the data ",e);
        }
        return res;
    }
}
