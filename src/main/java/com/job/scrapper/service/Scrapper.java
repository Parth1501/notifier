package com.job.scrapper.service;

import com.job.scrapper.model.JobData;
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

    public List<JobData> scrapGoogleJob() {
        String url="https://www.google.com/search?client=ubuntu-sn&sca_esv=37afbf05fd6f097e&sca_upv=1&channel=fs&biw=1680&bih=427&q=physiotherapy+job+posting+surat+since+yesterday&uds=ADvngMjcH0KdF7qGWtwTBrP0nt7daSGmSjLTAH-oRzMvJ5-IJgVxBISlWgvCsGRrRw3-Tg0T_JypLLFpTbSoOihNAQFySVbmthZ9GVT3Piu-mTgV5n1gsDnhN5eFo5C52ojzBoJnSjZfJunrrJHFwAy3RcxZZOury3ErVWwsSdhjQJ2-ZISa8-a7x7KSFlv5kVjito9XItGFGH-ahDuC06EVifFBPu8z7Z6hdlafsUNeMJawVkROOPZuO0eex-CSu4FQ2IRcsimK3YWt3n-IOxf937wJTN2na0JSMYZH6H19aJKxGaKPhnTl9MnxjcYBhe-INpgkHXUYPZP7dfj4uSKv-Qvf_xSamg&udm=8&sa=X&ved=2ahUKEwig9MatmdSIAxUxhq8BHcXeB7kQkbEKegQICxAE";
        List<JobData> res=new ArrayList<>();
        try {
            // Fetch the HTML document from the given URL
            Document document = Jsoup.connect(url).get();

            // Example: Get the title of the page

            // Example: Select specific elements using CSS selectors
            var data = document.getElementsByAttributeValue("data-id","jobs-detail-viewer"); // Select all <p> elements

            var jobNames=document.select(".tNxQIb.PUpOsf");
            var institute=document.select(".wHYlTd.MKCbgd.a3jPc");
            var locations=document.select(".wHYlTd.FqK3wc.MKCbgd");

            var size=jobNames.size();
            for(int i=0;i<size;i++) {
                JobData newData=new JobData();
                newData.setGoogleUrl(url);
                newData.setJobName(jobNames.get(i).text());
                newData.setInstitute(institute.get(i).text());
                newData.setCity(locations.get(i).text());
                res.add(newData);
            }
        } catch (IOException e) {
            log.error("Unable to fetch the data ",e);
        }
        return res;
    }
}
